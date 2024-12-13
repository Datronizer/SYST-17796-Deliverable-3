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

    private BlackjackHand hand = new BlackjackHand();
    private PlayerAction lastAction = PlayerAction.Hit;
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

    public PlayerAction getLastAction() {
        return this.lastAction;
    }
    
    public void clearLastAction() {
        this.lastAction = PlayerAction.Hit;
    }

    public void hit(BlackjackCard card) {
        this.lastAction = PlayerAction.Hit;
        this.hand.hit(card);
    }
    
    public void stand() {
        this.lastAction = PlayerAction.Stand;
    }
    
    public void lose() {
        this.lastAction = PlayerAction.Lose;
    }
    
    public void win() {
        this.lastAction = PlayerAction.Win;
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
