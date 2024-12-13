/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Chien
 */
public class Blackjack extends Game {

    GameView view = new GameView();
    ArrayList<BlackjackPlayer> winners = new ArrayList<>();

    int deckSize = 6;
    BlackjackDeck deck = new BlackjackDeck(6 * 52);

    BlackjackPlayer dealer = new BlackjackPlayer("Dealer");

    public Blackjack() {
        super("Blackjack");
    }

    // A dealer is basically a BlackjackPlayer, but their actions are automatic
    public BlackjackPlayer initializeDealer() {
        return new BlackjackPlayer("Dealer");
    }

    @Override
    public void declareWinner() {
        String s = "Winner(s): ";
        for (int i = 0; i < this.winners.size(); i++) {
            s += this.winners.get(i).getName() + ", ";
        }
        System.out.println(s);
    }

    @Override
    public void play() {
        Scanner scanner = new Scanner(System.in);
        int numberOfPlayers = view.promptPlayerCount();

        ArrayList<BlackjackPlayer> players = new ArrayList<>();

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Please enter the name of Player " + (i + 1) + ": ");
            String name = scanner.nextLine();
            players.add(new BlackjackPlayer(name));
        }

        System.out.println("Thank you. Registering players for this game...");
        this.setBlackjackPlayers(players);

        System.out.println("Setting up a deck for " + numberOfPlayers + " players...");
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

        boolean continueGame = true;

        // Game begins and resets here
        while (continueGame) {
            this.startGame();

            while (true) {
                boolean allPlayersStood = true;
                boolean allPlayersLost = true;

                // player hit/stand loop
                for (BlackjackPlayer player : players) {
                    PlayerAction playerLastAction = player.getLastAction();
                    if (PlayerAction.Win.equals(playerLastAction)) {
                        this.winners.add(player);
                        break;
                    }

                    if (PlayerAction.Stand.equals(playerLastAction) || PlayerAction.Lose.equals(playerLastAction)) {
                        continue; // Skip players who have already stood or lost
                    }
                    System.out.println(player.getName() + "'s turn");
                    System.out.printf("Your hand: %s\n", player.getHand().toString());
                    System.out.printf("Do you want to (1) Hit or (2) Stand? ");
                    int choice = scanner.nextInt();
                    if (choice == 1) {
                        BlackjackCard card = deck.dealOneCard();
                        player.hit(card);
                        System.out.printf("%s draws: %s\n\n",player.getName(), card.toString());
                        if (player.getHand().gethandValue() > 21) {
                            System.out.printf("%s busts! Their turn will be skipped for the rest of this round.\n\n", player.getName());
                            player.lose();
                        }
                    } else {
                        player.stand();
                        System.out.printf("%s stands! Their turn will be skipped for the rest of this round.\n\n", player.getName());
                    }

                    // Check if all players have stood or lost
                    allPlayersStood = allPlayersStood && player.getLastAction() == PlayerAction.Stand;
                }

                if (!this.winners.isEmpty()) {
                    System.out.println("Blackjack!!!");
                    this.declareWinner();
                    break;
                }

                if (allPlayersStood) {
                    System.out.println("Dealer's turn to play.");
                    System.out.println("Dealer reveals hand: " + dealer.getHand().toString());
                    if (dealer.getScore() == 21) {
                        System.out.println("Dealer blackjack!!! You all lost.");
                        break;
                    }

                    while (dealer.getHand().gethandValue() < 17) {
                        BlackjackCard card = deck.dealOneCard();
                        dealer.getHand().hit(card);
                        System.out.printf("Dealer draws %s, total %d\n", card.toString(), dealer.getScore());
                    }

                    if (dealer.getHand().gethandValue() > 21) {
                        System.out.printf("Dealer busts!\n\n");
                        for (BlackjackPlayer player : players) {
                            if (!player.getLastAction().equals(PlayerAction.Lose)) {
                                this.winners.add(player);
                            }
                        }
                        this.declareWinner();
                        break;
                    } else {
                        for (BlackjackPlayer player : players) {
                            if ((player.getScore() > dealer.getScore()) && player.getScore() <= 21) {
                                this.winners.add(player);
                            }
                        }
                        if (this.winners.isEmpty()) {
                            System.out.println("Dealer wins!\n");
                            break;
                        } else {
                            this.declareWinner();
                        }
                    }
                    break;
                }
            }
            if (!view.promptReplay()) {
                continueGame = false;
            }
        }
    }

    public void dealStartingHands(BlackjackDeck deck) {
        System.out.println("Deck initialized and shuffled. Dealing cards...");
        for (BlackjackPlayer player : this.getBlackjackPlayers()) {
            player.getHand().clear();
            player.clearLastAction();

            player.getHand().receiveStartingHand(deck.dealStartingHand());
            if (player.getScore() == 21) {
                player.win();
            }
        }
    }

    public void startGame() {
        // Clear all previous games
        this.winners = new ArrayList<>();

        dealer = initializeDealer();
        dealer.getHand().clear();

        deck = new BlackjackDeck(deckSize * 52);
        deck.initializeDeck();
        deck.shuffle();
        this.dealStartingHands(deck);

        dealer.getHand().receiveStartingHand(deck.dealStartingHand());
        // Reveal dealer's hand, then let all players play first
        System.out.printf("Dealer's Hand: [Hidden], %s\n\n", dealer.getHand().getCards().get(1));
    }

    // There has to be a better way than this
    // TODO: Workaround to allow BlackjackPlayer typing
    @Override
    public void setPlayers(ArrayList<Player> players) {
        if (players instanceof ArrayList<?>) {
            super.setPlayers(players);
        } else {
            throw new IllegalArgumentException("Players must be of type ArrayList<BlackjackPlayer>");
        }
    }

    // TODO: Overloading setPlayers to accept ArrayList<BlackjackPlayer> 
    public void setBlackjackPlayers(ArrayList<BlackjackPlayer> players) {
        super.setPlayers(new ArrayList<>(players));
    }

    // TODO: workaround for getPlayers to return ArrayList<BlackjackPlayer> 
    public ArrayList<BlackjackPlayer> getBlackjackPlayers() {
        return (ArrayList<BlackjackPlayer>) (ArrayList<?>) super.getPlayers();
    }
}
