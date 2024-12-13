/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author datronizer
 */
public class BlackjackPlayer extends Player {

    private BlackjackHand hand;
    private int score;

    public BlackjackPlayer(String name) {
        super(name);
    }
    
    public BlackjackHand getHand() {
        return this.hand;
    }
    
    public int getScore() {
        return this.hand.gethandValue();
    }

    @Override
    public void play() {
        
    }

//    /**
//     * A constructor that allows you to set the player's unique ID
//     *
//     * @param name the unique ID to assign to this player.
//    
//    public void setScore(int score) {
//        this.score = score;
//    }
}
