/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author datronizer
 */
public class Deck extends GroupOfCards {
    public Deck() {
        super(52 * 6);
    }
    
    public Card dealOneCard() {
        return this.getCards().removeFirst();
    }
}
