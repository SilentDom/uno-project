import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javafx.scene.image.Image;

public class Game {
    private Deck deck;
    private UnoController unoController;
    private List<Player> players = new ArrayList<Player>();
    private List<Card> discardPile = new ArrayList<Card>();
    private boolean gameDirection;
    private Image lastDiscard;
    private int playerTurn = 0;
    private int playerCount = 4;

    // Constructor for the Game, accepts the UnoController as a variable for turn
    // checking within the controller class itself.
    // Instantiates 2 players (a user and a computer), sets the game direction,
    // creates a new deck and shuffles it, and deals 7 cards to each player:
    public Game(UnoController controller) {
        this.unoController = controller;
        for (int i = 0; i < playerCount; i++) {
            players.add(new Player("Player " + i));
        }
        players.get(1).setComputerBool(true);
        players.get(3).setComputerBool(true);
        players.get(2).setComputerBool(false);
        deck = new Deck();
        deck.shuffleDeck();
        dealCards();
    }

    // Deals 7 cards to each of the players in the game and adds one card to the discard
    // pile to start the game. It performs checks on this card to make sure it is not a wild
    // card or a DrawTwo card and if it is, it re-draws from the deck until it finds one that
    // does not match that criteria:
    public void dealCards() {
        for (Player players : players) {
            List<Card> hand = new ArrayList<Card>();
            for (int i = 0; i < 7; i++) {
                hand.add(deck.getDeck().remove(0));
            }
            players.setPlayerHand(hand);
        }
        Card discardCard = deck.getDeck().remove(0);
        while (discardCard.getCardType() == CardType.Wild || discardCard.getCardType() == CardType.WildDrawFour || discardCard.getCardType() == CardType.DrawTwo) {
            this.addToDiscardPile(discardCard);
            discardCard = deck.getDeck().remove(0);
        }
        this.addToDiscardPile(discardCard);
    }

    // Getter for the game direction:
    public boolean getGameDirection() {
        return gameDirection;
    }

    // Setter for the game direction:
    public void setGameDirection(boolean direction) {
        this.gameDirection = direction;
    }

    // Gets the image of the last card that was played:
    public Image getLastCardPlayed() {
        return lastDiscard;
    }

    // Gets the discard pile:
    public List<Card> getDiscardPile() {
        return discardPile;
    }

    // In case the deck runs out of cards, this method changes the discard pile to the new deck.
    public void replaceDeck(List<Card> cards) {
        deck.getDeck().addAll(cards);
        deck.shuffleDeck();
    }

    // Checks if the game is over by checking if any of the players have zero cards left in their hand.
    // Returns a boolean value:
    public boolean isGameOver() {
        Player thisPlayer = getActingPlayer(getPlayerTurn());
        if (thisPlayer.getPlayerHand().size() == 0) {
            return true;
        }
        return false;
    }

    // Method for giving a specific player a card:
    public void givePlayerCard(Player player) {
        if (deck.getDeck().size() > 0) {
            Card card = deck.getDeck().remove(0);
            player.givePlayerCard(card);
        } else {
            replaceDeck(getDiscardPile());
        }
    }

    // Adds whatever card was played to the discard pile, updating the image to match
    // the card that was played:
    public void addToDiscardPile(Card card) {
        getDiscardPile().add(card);
        lastDiscard = new Image(card.getImage(card.getCardColor(), card.getCardType()));
    }

    // Gets the current acting player based on their turn "number":
    public Player getActingPlayer(int playerIndex) {
        return players.get(playerIndex);
    }

    // Boolean method for playing a card, it takes in the player, and the card chosen and tries
    // to play the card, if it can the card is played and added to the discard pile. If the card is
    // not playable the method returns false and the card is not played.
    // The purpose of this being a boolean method is to allow the computer to tell when they can play
    // a card:
    public boolean playCard(int playerNum, int cardNumber) {
        Player currentPlayer = getActingPlayer(getPlayerTurn());
        Card currentCard = currentPlayer.getPlayerCard(cardNumber);
        try {
            if (isPlayable(currentCard)) {
                // Choose wild card color
                if (currentCard.getCardType() == CardType.Wild || currentCard.getCardType() == CardType.WildDrawFour) {
                    currentPlayer.getPlayerHand().remove(cardNumber);
                    if (playerNum == 0 || playerNum == 2) {
                        CardColor choice = WildColor.display();
                        Card wildColor = new Card(choice, currentCard.getCardType());
                        currentCard = wildColor;
                    } else {
                        // Chooses the first non-wild color if the computer plays a wild card:
                        for (Card card : currentPlayer.getPlayerHand()) {
                            if (card.getCardColor() != CardColor.Wild) {
                                CardColor playerChoice = card.getCardColor();
                                currentCard.setWildColor(playerChoice);
                                break;
                            }
                        }
                    }
                    // Makes a player draw 4 if a wild draw 4 is played:
                    if (currentCard.getCardType() == CardType.WildDrawFour) {
                        cardAction(playerNum, currentCard);
                    }
                } else {
                    currentPlayer.getPlayerHand().remove(cardNumber);
                    cardAction(playerNum, currentCard);
                }
                addToDiscardPile(currentCard);
                return true;
            }
        } catch (Exception e) {
            System.out.println("We caught an exception in playCard(): " + e.toString());
        }
        return false;
    }

    // Takes the computer "player" and plays a card from their hand, it does not have any logic checking
    // beyond what card can be played based on Uno's rules.
    // It loops through the computer's hand and plays the first playable card it finds:
    public void playComputerCard() {
        Player thisPlayer = getActingPlayer(getPlayerTurn());
        boolean isCardPlayed = false;
        int pos = 0;
        for (Card card : thisPlayer.getPlayerHand()) {
            isCardPlayed = playCard(getPlayerTurn(), pos);
            if (isCardPlayed) {
                break;
            } else {
                pos++;
                continue;
            }
        }
        while (!isCardPlayed) {
            givePlayerCard(thisPlayer);
            isCardPlayed = playCard(getPlayerTurn(), thisPlayer.getPlayerHand().size() - 1);
        }
        endTurn();
    }

    // Turn tracking method, using the player's index in the players ArrayList:
    public void endTurn() {
        try {
            // Set the last player to true
            players.get(playerTurn).setCardPlayed(true);
            if (gameDirection) {
                playerTurn++;
            } else {
                playerTurn--;
            }
            // Set the next player to false (hasn't played a card yet)
            if (playerTurn >= players.size()) {
                playerTurn = 0;
            } else if (playerTurn < 0) {
                playerTurn = players.size() - 1;
            }

            unoController.endTurnUpdates();
            players.get(playerTurn).setCardPlayed(false);
            if (players.get(playerTurn).getComputerBool()) {
                playComputerCard();
            }
        } catch (Exception e) {
            System.out.println("Caught an exception in endTurn(): " + e.toString());
        }
    }

    // Gets whose turn it is:
    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    // Checks if the card played is playable based on Uno's default rules:
    // (Card color or type must match for the card to be played excluding wilds)
    public boolean isPlayable(Card currentPlayerCard) {
        Card discardCard = getDiscardPile().get(getDiscardPile().size() - 1);
        if (currentPlayerCard.getCardType() == discardCard.getCardType()
            || currentPlayerCard.getCardColor() == discardCard.getCardColor()
            || currentPlayerCard.getCardType() == CardType.Wild
            || currentPlayerCard.getCardType() == CardType.WildDrawFour
        ) {
            return true;
        } else {
            return false;
        }
    }

    public int nextPlayer(int turn) {
        if (gameDirection && turn < players.size() - 1)
            return turn + 1;
        else if (gameDirection)
            return 0;
        else if (!gameDirection && turn > 0)
            return turn - 1;
        else return players.size() - 1;
    }

    public void cardAction(int playerNum, Card card) {
        if (card.getCardType() == CardType.DrawTwo) {
            givePlayerCard(players.get(nextPlayer(playerNum)));
            givePlayerCard(players.get(nextPlayer(playerNum)));
        }
        if (card.getCardType() == CardType.WildDrawFour) {
            givePlayerCard(players.get(nextPlayer(playerNum)));
            givePlayerCard(players.get(nextPlayer(playerNum)));
            givePlayerCard(players.get(nextPlayer(playerNum)));
            givePlayerCard(players.get(nextPlayer(playerNum)));
        } else if (card.getCardType() == CardType.Reverse && players.size() > 2) {
            if (gameDirection) {
                gameDirection = false;
            } else {
                gameDirection = true;
            }
        } else if (card.getCardType() == CardType.Skip || card.getCardType() == CardType.Reverse && players.size() <= 2) {
            setPlayerTurn(nextPlayer(playerNum));
        } else if (card.getCardType() == CardType.Zero) {
            ArrayList<Card> temp = new ArrayList<Card>();
            temp.addAll(players.get(0).getPlayerHand());
            players.get(0).getPlayerHand().clear();
            players.get(0).getPlayerHand().addAll(players.get(playerCount - 1).getPlayerHand());
            players.get(playerCount - 1).getPlayerHand().clear();
            for (int i = playerCount - 1; i >= 2; i--) {
                players.get(i).getPlayerHand().addAll(players.get(i - 1).getPlayerHand());
                players.get(i - 1).getPlayerHand().clear();
            }
            players.get(1).getPlayerHand().addAll(temp);
        } else if (card.getCardType() == CardType.Seven) {
            ArrayList<Card> temp = new ArrayList<Card>();
            temp.addAll(players.get(playerNum).getPlayerHand());
            int min = nextPlayer(nextPlayer(playerNum));
            for (int i = 0; i < playerCount - 1; i++) {
                if (players.get(i + 1).getPlayerHand().size() >
                    players.get(i).getPlayerHand().size() && i != playerNum) {
                    min = i;
                }
            }
            players.get(playerNum).getPlayerHand().clear();
            players.get(playerNum).getPlayerHand().addAll(players.get(min).getPlayerHand());
            players.get(min).getPlayerHand().clear();
            players.get(min).getPlayerHand().addAll(temp);
        }
    }
}
