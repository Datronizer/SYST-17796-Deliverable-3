/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

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
        for (BlackjackCard card: cards) {
            this.addCard(card);
        }
    }

    public void hit(BlackjackCard card) {
        this.addCard(card);
        
        int value = card.getValue(card.getFaceValue());
        if (value == 11 && this.handValue + 11 > 21) {
            this.handValue += 1;
        }
        else {
            this.handValue += value;
        }
    }

    public void stand() {

    }
    
    public int gethandValue() {
        return this.handValue;
    }

    @Override
    public String toString() {
        ArrayList<Card> cards = this.getCards();
        return "Hand: " + cards + " Score: " + handValue;
    }
}
