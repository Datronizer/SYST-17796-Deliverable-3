/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author datronizer
 */
public class BlackjackHand extends GroupOfCards {

    private int handValue;

    public BlackjackHand() {
        super(7); // max hand size is 7 cards
    }

    public void receiveStartingHand(ArrayList<BlackjackCard> cards) {
        for (BlackjackCard card : cards) {
            this.addCard(card);
        }
        this.recalculateHandValue();
    }

    public void hit(BlackjackCard card) {
        this.addCard(card);

        int value = BlackjackCard.getValue(card.getFaceValue());
        if (value == 11 && this.handValue + 11 > 21) {
            this.handValue += 1;
        } else {
            this.handValue += value;
        }
    }

    public void clear() {
        this.cards = new ArrayList<>();
    }

    /**
     * This is done because the value of Ace changes based on other cards, this
     * should be run every time a card is added to the player's hand
     */
    public void recalculateHandValue() {
        // Reset hand value
        this.handValue = 0;

        this.sortHand();
        ArrayList<BlackjackCard> blackjackCards = this.getBlackjackHand();

        for (BlackjackCard card : blackjackCards) {
            // Sum cards
            int value = BlackjackCard.getValue(card.getFaceValue());
            if (value == 11 && this.handValue + 11 > 21) {
                this.handValue += 1;
            } else {
                this.handValue += value;
            }
//            System.out.println("Current card: " + card + ". Current score: " + this.handValue);
        }
    }

    public void sortHand() {
        Collections.sort(cards, (Card c1, Card c2) -> {
            int value1 = BlackjackCard.getValue(c1.getFaceValue());
            int value2 = BlackjackCard.getValue(c2.getFaceValue());
            return Integer.compare(value1, value2);
        });
    }

    public int gethandValue() {
        return this.handValue;
    }

    public ArrayList<BlackjackCard> getBlackjackHand() {
        ArrayList<Card> rawCards = this.getCards();
        ArrayList<BlackjackCard> blackjackCards = new ArrayList<>();
        for (Card card : rawCards) {
            blackjackCards.add(new BlackjackCard(card.getType(), card.getFaceValue()));
        }
        return blackjackCards;
    }

    @Override
    public String toString() {
        return "Hand: " + this.cards.toString() + " Score: " + handValue;
    }
}
