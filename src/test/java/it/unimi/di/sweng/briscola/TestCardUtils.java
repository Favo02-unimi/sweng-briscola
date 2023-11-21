package it.unimi.di.sweng.briscola;

import java.util.ArrayList;
import java.util.List;

public class TestCardUtils {

    public static List<Card> fromStringList(String cardsString) {
        String[] tokens = cardsString.split(" ");
        List<Card> cards = new ArrayList<>();
        for (String s : tokens)
            cards.add(toCard(s));
        return cards;
    }

    public static Rank toRank(char cRank) {
        return switch (cRank) {
            case '1', 'A' -> Rank.ASSO;
            case '2' -> Rank.DUE;
            case '3' -> Rank.TRE;
            case '4' -> Rank.QUATTRO;
            case '5' -> Rank.CINQUE;
            case '6' -> Rank.SEI;
            case '7' -> Rank.SETTE;
            case '8', 'F' -> Rank.FANTE;
            case '9', 'C' -> Rank.CAVALLO;
            case '0', 'R' -> Rank.RE;
            default -> throw new IllegalArgumentException("invalid rank");
        };
    }

    public static Suit toSuit(char cSuit) {
        return switch (cSuit) {
            case 'S' -> Suit.SPADE;
            case 'C' -> Suit.COPPE;
            case 'D' -> Suit.DENARI;
            case 'B' -> Suit.BASTONI;
            default -> throw new IllegalArgumentException("invalid suit");
        };
    }

    public static Card toCard(String cardString) {
        Rank rank = toRank(cardString.charAt(0));
        Suit suit = toSuit(cardString.charAt(1));
        return Card.get(rank, suit);
    }

}
