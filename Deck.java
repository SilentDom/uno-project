import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.ImageView;

public class Deck {
	private Card[] cards;
	private int deck;
	
	public Deck(Card[] cards, int deck) {
		this.cards = cards;
		this.deck = deck;
	}
	
	public void createDeck() {
		Card.Color[] colors = Card.Color.values();
		deck = 0;
		
		for (int i = 0; i < colors.length - 1; i++) {
			Card.Color color = colors[i];
			
			// Creates the 10 number cards of each color.
			cards[deck++] = new Card(color, Card.Type.getType(0));
			
			for (int j = i; j < 9; j++) {
				cards[deck++] = new Card(color, Card.Type.getType(j));
				cards[deck++] = new Card(color, Card.Type.getType(j));
			}
			
			// Creates the 2 of each action card for each color.
			// 8 action cards are created in total for each color. 
			Card.Type[] types = new Card.Type[] {
					Card.Type.DrawTwo, Card.Type.Skip, Card.Type.Reverse
			};
			
			for (Card.Type e : types) {
				cards[deck++] = new Card(color, e);
				cards[deck++] = new Card(color, e);
			}
		}
			// Creates the 4 Wild & 4 Wild Draw 4 cards in the deck.
			Card.Type[] wild = new Card.Type[] {
					Card.Type.Wild, Card.Type.WildDrawFour
			};
			for (Card.Type e : wild) {
				for (int k = 0; k < 4; k++) {
					cards[deck++] = new Card(Card.Color.Wild, e);
				}
			}
	}
	
	// In case the deck runs out of cards, this method changes the discard pile to the new deck. 
	public void replaceDeck(ArrayList<Card> cards) {
		this.cards = cards.toArray(new Card[(cards.size())]);
		this.deck = this.cards.length;
	}
	
	// Checks if the deck is empty. 
	public boolean isDeckEmpty() {
		return deck == 0;
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
		return cards[--deck];
	}
	
	public Card[] drawMultipleCards(int x) {
		if (x > deck) {
			throw new IllegalArgumentException("Cannot draw that many cards, not enough cards are in the deck.");
		}
		else if (x < 0) {
			throw new IllegalArgumentException("Cannot draw a negative amount of cards.");
		}
		
		Card[] draw = new Card[x];
		
		for (int i = 0; i < 0; i ++) {
			draw[i] = cards[--deck];
		}
		return draw;
	}
	
	public ImageView cardImage() {
		if (isDeckEmpty()) {
			throw new IllegalArgumentException("Cannot draw a card, the deck is empty.");
		}
		return new ImageView(cards[--deck].toString() + ".png");
	}
	
}
