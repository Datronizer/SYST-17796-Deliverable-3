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
public class Hand extends GroupOfCards {
    public Hand() {
        super(7); // max hand size is 7 cards
    }
    
    public void hit() {    
        super.getCards() this.getCards() ;
        score += card.value;
        if (score > 21) {
            for (Card c : hand) {
                if (c.rank.equals("Ace") && score > 21) {
                    score -= 10;
                    break;
                }
            }
        }
    }
    
    public void stand() {
        
    }
    
    @Override
    public void toString()
    {
        ArrayList<Card> cards = this.getCards();
        String s = "";
        
        for (Card card : cards) {
            String c = card.getRank();
        }
    }
}
