package it.unimi.di.sweng.briscola;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player implements Comparable<Player>, Iterable<Card> {

    private final @NotNull String name;

    private final @NotNull List<Card> cards = new ArrayList<>();
    private final @NotNull List<Card> personalDeck = new ArrayList<>();

    private @NotNull Strategy firstStrategy;
    private @NotNull Strategy secondStrategy;

    private @Nullable Card chosenCard;

    public Player(@NotNull String name) {
        this.name = name;
    }

    public void setFirstStrategy(@NotNull Strategy firstStrategy) {
        this.firstStrategy = firstStrategy;
    }

    public void setSecondStrategy(@NotNull Strategy secondStrategy) {
        this.secondStrategy = secondStrategy;
    }

    void giveCard(@NotNull Card card) {
        assert cards.size() < 3;
        cards.add(card);
    }

    public void addWonCardsToPersonalDeck(@NotNull Card first, @NotNull Card second) {
        personalDeck.add(first);
        personalDeck.add(second);
    }

    @NotNull
    public Card chooseFirstCard(@NotNull Briscola game) {
        chosenCard = firstStrategy.chooseCard(this, game.otherPlayer(this), game.getBriscola());
        cards.remove(chosenCard);
        return chosenCard;
    }

    @NotNull
    public Card chooseSecondCard(@NotNull Briscola game) {
        Card card = secondStrategy.chooseCard(this, game.otherPlayer(this), game.getBriscola());
        cards.remove(card);
        return card;
    }

    @Nullable
    public Card playedCard() {
        return chosenCard;
    }

    private int getPoints() {
        int points = 0;
        for (Card card : personalDeck) {
            points += card.getRank().points();
        }
        return points;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @Override
    @NotNull
    public String toString() {
        return name +
                " <" + getPoints() + ">" +
                " " + cards;
    }

    public void shoutResult() {
        System.out.printf("Sono %s e ho vinto con %d punti%n", getName(), getPoints());
    }

    @Override
    public int compareTo(@NotNull Player other) {
        return Integer.compare(this.getPoints(), other.getPoints());
    }

    @NotNull
    @Override
    public Iterator<Card> iterator() {
        return new ArrayList<>(cards).iterator();
    }
}
