package it.unimi.di.sweng.briscola;

import org.jetbrains.annotations.NotNull;

public class Briscola {
  private final Deck deck;
  private final Card briscola;

  private boolean availableBriscolaCard = true;
  private final Player[] players = new Player[2];

  @NotNull
  private Player firstCardPlayer;

  public Briscola(@NotNull Player p1, @NotNull Player p2, @NotNull Deck d) {
    deck = d;

    players[0] = p1;
    players[1] = p2;
    firstCardPlayer = p1;

    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 2; j++)
        players[j].giveCard(deck.draw());

    briscola = deck.draw();
  }

  public void playTurn() {

    System.out.println("\n\n---\nricordo che briscola è " + getBriscola());

    System.out.println(firstCardPlayer);
    Card firstCard = firstCardPlayer.chooseFirstCard(this);
    System.out.println("ha scelto: " + firstCard);

    System.out.println(otherPlayer(firstCardPlayer));
    Card secondCard = otherPlayer(firstCardPlayer).chooseSecondCard(this);
    System.out.println("ha scelto: " + secondCard);

    firstCardPlayer = establishTurnWinner(firstCard, secondCard);
    System.out.println("vince questa mano: " + firstCardPlayer.getName());
    firstCardPlayer.addWonCardsToPersonalDeck(firstCard, secondCard);
  }


  @NotNull Player establishTurnWinner(@NotNull Card first, @NotNull Card second) {
      int punti1 = first.getRank().ordinal();
      int punti2 = second.getRank().ordinal();

      if (first.getSuit() == second.getSuit())
          return punti1 > punti2 ? firstCardPlayer : otherPlayer(firstCardPlayer);
      else if (first.getSuit() == getBriscola())
          return firstCardPlayer;
      else if (second.getSuit() == getBriscola())
          return otherPlayer(firstCardPlayer);
      else
          return firstCardPlayer;
  }

  public void giveEachPlayerOneCard() {
    firstCardPlayer.giveCard(draw());
    otherPlayer(firstCardPlayer).giveCard(draw());
  }

  @NotNull
  public Suit getBriscola() {
    return briscola.getSuit();
  }


  public boolean isBriscola(@NotNull Card c) {
    return c.getSuit() == getBriscola();
  }

  @NotNull
  private Card draw() {
    assert availableBriscolaCard;
    if (deck.isEmpty()) {
      availableBriscolaCard = false;
      return briscola;
    }
    return deck.draw();
  }

  public boolean availableCards() {
    return availableBriscolaCard;
  }

  @NotNull
  public Player establishGameWinner() {
      int comparation = players[0].compareTo(players[1]);
      if (comparation > 0) return  players[0];
      if (comparation < 0) return  players[1];
      return PAREGGIO();
  }

  @NotNull
  public Player otherPlayer(@NotNull Player player) {
    if (players[0] == player)
      return players[1];
    return players[0];
  }

  @NotNull
  public static Player PAREGGIO() {
      return new Player("pareggio");
  }

}
