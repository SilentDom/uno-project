import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Game {
    private Deck deck;
    private UnoController unoController;
    private List<Player> players = new ArrayList<Player>();
    private List<Card> discardPile = new ArrayList<Card>();
    private Image lastDiscard;
    private int playerTurn = 0;

    // Constructor for the Game, accepts the UnoController as a variable for turn
    // checking within the controller class itself.
    // Instantiates 2 players (a user and a computer), sets the game direction,
    // creates a new deck and shuffles it, and deals 7 cards to each player:
    public Game(UnoController controller) {
        this.unoController = controller;
        for (int i = 0; i < 2; i++) {
            players.add(new Player("Player " + i));
        }
        players.get(1).setComputerBool(true);
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

    // Gets the image of the last card that was played:
    public Image getLastCardPlayed() {
        return lastDiscard;
    }

    // Gets the current player based on whose turn it is:
    public Player getCurrentPlayer(Player player) {
        return player.getPlayers().get(players.indexOf(player)); // TODO: fix whatever this is
    }

    // Gets the discard pile:
    public List<Card> getDiscardPile() {
        return discardPile;
    }

    // Checks if the game is over by checking if any of the players have zero cards left in their hand.
    // Returns a boolean value:
    public boolean isGameOver() {
        Player thisPlayer = getActingPlayer(playerTurn);
        if (thisPlayer.getPlayerHand().size() == 0) {
            return true;
        }
        return false;
    }

    // Method for giving a specific player a card:
    public void givePlayerCard(Player player) {
        Card card = deck.getDeck().remove(0);
        player.givePlayerCard(card);
    }

    // Adds whatever card was played to the discard pile, updating the image to match
    // the card that was played:
    public void addToDiscardPile(Card card) {
        getDiscardPile().add(card);
        lastDiscard = new Image(card.getImage(card.getCardColor(), card.getCardType()));
    }

    // Gets the current acting player based on their turn "number":
    public Player getActingPlayer(int playerIndex) {
        return players.get(playerIndex); // TODO: replace this with getCurrentPlayer()
    }

    // Boolean method for playing a card, it takes in the player, and the card chosen and tries
    // to play the card, if it can the card is played and added to the discard pile. If the card is
    // not playable the method returns false and the card is not played.
    // The purpose of this being a boolean method is to allow the computer to tell when they can play
    // a card:
    public boolean playCard(int playerNum, int cardNumber) {
        Player currentPlayer = getActingPlayer(playerNum);
        Card currentCard = currentPlayer.getPlayerCard(cardNumber);
        try {
            if (isPlayable(currentCard)) {
                currentPlayer.getPlayerHand().remove(cardNumber);
                addToDiscardPile(currentCard);
                return true;
            }
        } catch (Exception e) {
            System.out.println("We caught an exception: " + e.toString());
        }
        return false;
    }

    // Takes the computer "player" and plays a card from their hand, it does not have any logic checking
    // beyond what card can be played based on Uno's rules.
    // It loops through the computer's hand and plays the first playable card it finds:
    public void playComputerCard() {
        Player thisPlayer = getActingPlayer(1);
        boolean isCardPlayed = false;
        int pos = 0;
        for (Card card : thisPlayer.getPlayerHand()) {
            isCardPlayed = playCard(1, pos);
            if (isCardPlayed) {
                break;
            } else {
                pos++;
                continue;
            }
        }
        while (!isCardPlayed) {
            givePlayerCard(thisPlayer);
            isCardPlayed = playCard(1, thisPlayer.getPlayerHand().size() - 1);
        }
        endTurn();
    }


    // Turn tracking method, using the player's index in the players ArrayList:
    public void endTurn() {
        // Set the last player to true
        players.get(playerTurn).setCardPlayed(true);
        unoController.endTurnUpdates();
        playerTurn++;
        // Set the next player to false (hasn't played a card yet)
        if (playerTurn >= players.size()) {
            playerTurn = 0;
        }
        players.get(playerTurn).setCardPlayed(false);
        if (players.get(playerTurn).getComputerBool()) {
            playComputerCard();
        }
    }

    // Gets whose turn it is:
    public int getPlayerTurn() {
        return playerTurn;
    }

    // Checks if the card played is playable based on Uno's default rules:
    // (Card color or type must match for the card to be played excluding wilds)
    public boolean isPlayable(Card currentPlayerCard) {
        Card discardCard = getDiscardPile().get(getDiscardPile().size() - 1);
        if (currentPlayerCard.getCardType() == discardCard.getCardType()
            || currentPlayerCard.getCardColor() == discardCard.getCardColor()
            || currentPlayerCard.getCardType() == CardType.Wild
            || currentPlayerCard.getCardType() == CardType.WildDrawFour
            || discardCard.getCardType() == CardType.Wild    // temporary until wild color choosing
            || discardCard.getCardType() == CardType.WildDrawFour) {
            return true;
        } else {
            return false;
        }
    }
}