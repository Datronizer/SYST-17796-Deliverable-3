/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author datronizer
 */
public class BlackjackCard extends Card {

    private int value;

    public BlackjackCard(String suit, String rank) {
        super(suit, rank);
        this.value = this.getValue(rank);
    }

    public static BlackjackCard from(Card card) {
        return new BlackjackCard(
                card.getType(),
                card.getFaceValue()
        );
    }

    public static int getValue(String rank) {
        switch (rank) {
            case "King":
                return 10;
            case "Queen":
                return 10;
            case "Jack":
                return 10;
            case "Ace":
                return 11;
            default:
                return Integer.parseInt(rank);
        }
    }

    /**
     * * @return a String representation of a Blackjack card, including its
     * rank and suit
     */
    @Override
    public String toString() {
        return this.getFaceValue() + " of " + this.getType()
                + "(" + this.getValue(getFaceValue()) + ")";
    }
}
