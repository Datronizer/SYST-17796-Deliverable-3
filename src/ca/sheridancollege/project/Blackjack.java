/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Chien
 */
public class Blackjack extends Game {

    GameView view;
    ArrayList<BlackjackPlayer> winners;

    public Blackjack() {
        super("Blackjack");
    }

    // A dealer is basically a BlackjackPlayer, but their actions are automatic
    public BlackjackPlayer initializeDealer() {
        return new BlackjackPlayer("Dealer");
    }

    @Override
    public void declareWinner() {
//        view.drawWinners(players);
    }

    @Override
    public void play() {
        Scanner scanner = new Scanner(System.in);
        int playerWins = 0;
        int dealerWins = 0;

        int numberOfPlayers = view.promptPlayerCount();

        ArrayList<BlackjackPlayer> players = new ArrayList<>();
        BlackjackPlayer dealer = initializeDealer();

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Please enter the name of player " + i + ": ");
            String name = scanner.nextLine();
            players.add(new BlackjackPlayer(name));
        }

        System.out.println("Thank you. Registering players for this game...");
        this.setBlackjackPlayers(players);

        System.out.println("Setting up a deck for " + numberOfPlayers + " players...");
        int deckSize = 1;
        switch (numberOfPlayers) {
            case 1:
            case 2:
                deckSize = 6;
                break;
            case 3:
            case 4:
                deckSize = 7;
                break;
            case 5:
            case 6:
                deckSize = 8;
                break;
            default:
                deckSize = 6; // More than 6 players, use 4 decks 
        }

        BlackjackDeck deck = new BlackjackDeck(deckSize);
        deck.initializeDeck();
        deck.shuffle();
        this.dealStartingHands(deck);

        // This keeps track of players actions. If all stood, then dealer plays
        Map<BlackjackPlayer, PlayerActions> playerActions = new HashMap<>();

        // Game begins
        boolean continueGame = true;
        while (continueGame) {
            while (true) {
                boolean allPlayersStood = true;

                // Reveal dealer's hand, then let all players play first
                System.out.println("Dealer's Hand: [Hidden], "
                        + dealer.getHand().getCards().get(1));

                // player hit/stand loop
                for (BlackjackPlayer player : players) {
                    if ("Stand".equals(playerActions.get(player))) {
                        continue; // Skip players who have already stood 
                    }
                    System.out.println(player + "'s turn");
                    System.out.println("Your hand: " + player.getHand().toString());
                    System.out.println("Do you want to (1) Hit or (2) Stand?");
                    int choice = scanner.nextInt();
                    if (choice == 1) {
                        BlackjackCard card = deck.dealOneCard();
                        player.getHand().hit(card);
                        System.out.println("Your card: " + card.toString());
                        if (player.getHand().gethandValue() > 21) {
                            System.out.println("You bust! You will be removed for the rest of this round.");
                            players.remove(player);
                        }
                    } else {
                        playerActions.put(player, PlayerActions.Stand);
                    }
                }

                if (allPlayersStood) {
                    System.out.println("All players have stood. Dealer's turn to play.");
                    while (dealer.getHand().gethandValue() < 17) {
                        BlackjackCard card = deck.dealOneCard();
                        dealer.getHand().hit(card);
                        System.out.println("Dealer draws " + card.toString());
                    }

                    if (dealer.getHand().gethandValue() > 21) {
                        System.out.println("Dealer busts! You win!");
                        break;
                    } else {
                        for (BlackjackPlayer player : players) {
                            if (player.getHand().gethandValue() > dealer.getHand().gethandValue()) {
                                this.winners.add(player);
                            }
                        }
                    }
                    break;
                }

                if (!view.promptReplay()) {
                    continueGame = false;
                }
            }
        }
    }

    public void dealStartingHands(BlackjackDeck deck) {
        System.out.println("Deck initialized and shuffled");
        System.out.println("Dealing cards...");
        for (BlackjackPlayer player : this.getBlackjackPlayers()) {
            player.getHand().receiveStartingHand(deck.dealStartingHand());
        }
    }

    // Workaround to allow BlackjackPlayer typing
    @Override
    public void setPlayers(ArrayList<Player> players) {
        if (players instanceof ArrayList<?>) {
            super.setPlayers(players);
        } else {
            throw new IllegalArgumentException("Players must be of type ArrayList<BlackjackPlayer>");
        }
    }

    // Overloading setPlayers to accept ArrayList<BlackjackPlayer> 
    public void setBlackjackPlayers(ArrayList<BlackjackPlayer> players) {
        super.setPlayers(new ArrayList<>(players));
    }

    // Workaround for getPlayers to return ArrayList<BlackjackPlayer> 
    public ArrayList<BlackjackPlayer> getBlackjackPlayers() {
        return (ArrayList<BlackjackPlayer>) (ArrayList<?>) super.getPlayers();
    }
}

enum PlayerActions {
    Hit,
    Stand
}
