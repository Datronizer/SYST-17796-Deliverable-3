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
public class BlackjackDeck extends GroupOfCards {

    private int numOfDecks;

    // For blackjack, size will be 6 - 8 decks of 52 cards
    public BlackjackDeck(int numOfDecks) {
        super(numOfDecks * 52);
        this.numOfDecks = numOfDecks;
    }

    public void initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (int j = 0; j < numOfDecks; j++) {
            for (String suit : suits) {
                for (int i = 0; i < ranks.length; i++) {
                    this.addCard(new BlackjackCard(suit, ranks[i]));
                }
            };
        };
        this.shuffle();
    }

    public BlackjackCard dealOneCard() {
        Card card = this.dealCard();
        return BlackjackCard.from(card);
    }

    public ArrayList<BlackjackCard> dealStartingHand() {
        ArrayList<BlackjackCard> cards = new ArrayList<>();
        cards.add(dealOneCard());
        cards.add(dealOneCard());
        return cards;
    }
}
