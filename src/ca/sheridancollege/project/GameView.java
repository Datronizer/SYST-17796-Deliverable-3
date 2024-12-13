/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author datronizer
 */
public class GameView {

    Scanner scanner = new Scanner(System.in);

    public void drawPlayerHand(BlackjackHand hand, int score) {
        String s = "Hand: " + hand.toString() + " Score: " + score;
        System.out.println(s);
    }

    public int promptPlayerCount() {
        System.out.println("Welcome to Blackjack!");
        System.out.print("Please enter the number of players (1-6): ");
        int numberOfPlayers = scanner.nextInt();
        return numberOfPlayers;
    }
    
    public boolean promptReplay() {
        System.out.print("Do you want to play again? (yes/no): ");
        String playAgain = scanner.next();
        return playAgain.equalsIgnoreCase("yes");
    }
    
    /**
     * @param players List of winners (do not include Dealer)
     */
    public void drawWinners(ArrayList<Player> players) {
        String s = "Winner(s): ";
        for (int i = 0; i < players.size(); i++) {
            s += players.get(i).getName() + ", "; 
        }
        System.out.println(s);
    }

//    public 
//            
//System.out.println("Final Score - Player: " + playerWins + ", Dealer: " + dealerWins);
//        scanner.close();
//    }
}
