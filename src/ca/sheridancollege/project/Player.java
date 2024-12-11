/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public abstract class Player {

    private String name; //the unique name for this player
    private List<Card> hand;
    private int score;

    /**
     * A constructor that allows you to set the player's unique ID
     *
     * @param name the unique ID to assign to this player.
     */
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.score = 0;
    }

    /**
     * @return the player name
     */
    public String getName() {
        return name;
    }
    
    public int getScore() {
        return score;
    }

    public List<Card> getHand() {
        return hand;
    }

    /**
     * Ensure that the playerID is unique
     *
     * @param name the player name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    public void setHand(List<Card> hand) {
        this.hand = hand;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public void resetHand() {
        hand.clear();
        score = 0;
    }
    
//    public void addCard(Card card) {
//        hand.add(card);
//        score += card.value;
//        if (score > 21) {
//            for (Card c : hand) {
//                if (c.rank.equals("Ace") && score > 21) {
//                    score -= 10;
//                    break;
//                }
//            }
//        }
//    }

    @Override
    public String toString() {
        return "Hand: " + hand + " Score: " + score;
    }

    /**
     * The method to be overridden when you subclass the Player class with your specific type of Player and filled in
     * with logic to play your game.
     */
    public abstract void play();

}
