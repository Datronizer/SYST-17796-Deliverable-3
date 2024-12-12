/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author datronizer
 */
public class GameView {
    public String drawPlayerHand(Hand hand, int score) {
        return "Hand: " + hand.toString() + " Score: " + score;
    }
}
