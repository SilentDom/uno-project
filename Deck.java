import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private Card[] cards;
	private int deckSize;
	private int deckCounter;

	public Deck(int deck) {
		this.deckSize = deck;
		cards = new Card[deck];
	}

	public void createDeck() {
		Card.Color[] colors = Card.Color.values();
		deckCounter = 0;

		// Create our single use zero cards
		for (int i = 0; i < colors.length - 1; i++) {
			Card.Color color = colors[i];
			cards[deckCounter++] = new Card(color, Card.Type.getType(0));
		}

		// Create two of each colored number and function card
		for (int i = 0; i < colors.length - 1; i++) {
			Card.Color color = colors[i];
			for (int j = 1; j < 13; j++) {
				cards[deckCounter++] = new Card(color, Card.Type.getType(j));
				cards[deckCounter++] = new Card(color, Card.Type.getType(j));
			}
		}

		// Create our wild cards
		for (int i = 13; i < 15; i++) {
			for (int j = 0; j < 4; j++) {
				cards[deckCounter++] = new Card(Card.Color.Wild, Card.Type.getType(i));
			}
		}
	}

	// In case the deck runs out of cards, this method changes the discard pile to the new deck. 
	public void replaceDeck(ArrayList<Card> cards) {
		this.cards = cards.toArray(new Card[(cards.size())]);
		this.deckSize = this.cards.length;
	}

	// Checks if the deck is empty. 
	public boolean isDeckEmpty() {
		return deckCounter == 0;
	}

	// Shuffles the deck by generating random values and places the card at that spot in the array. 
	public void shuffleDeck() {
		int newDeck = cards.length;
		Random rand = new Random();

		for (int i = 0; i < cards.length; i++) {
			int x = i + rand.nextInt(newDeck - i);
			Card randCard = cards[x];
			cards[x] = cards[i];
			cards[i] = randCard;
		}
	}

	//TODO fix drawCard method
	public Card drawCard() {
		if (isDeckEmpty()) {
			throw new IllegalArgumentException("Cannot draw a card, the deck is empty.");
		}
		Card newCard = cards[--deckCounter];
		return newCard;
	}

	public Card[] drawMultipleCards(int x) {
		if (x > deckSize) {
			throw new IllegalArgumentException("Cannot draw that many cards, not enough cards are in the deck.");
		}
		else if (x < 0) {
			throw new IllegalArgumentException("Cannot draw a negative amount of cards.");
		}

		Card[] draw = new Card[x];

		for (int i = 0; i < 0; i ++) {
			draw[i] = cards[--deckCounter];
		}
		return draw;
	}
}