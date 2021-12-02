import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Deck {
	private List<Card> cards = new ArrayList<Card>();

	public Deck() {
		CardColor[] colors = CardColor.values();
		CardType[] types = CardType.values();

		// Create our single use zero cards
		for (int i = 0; i < colors.length - 1; i++) {
			CardColor color = colors[i];
			CardType type = types[0];
			cards.add(new Card(color, type));
		}

		// Create two of each colored number and function card
		for (int i = 0; i < colors.length - 1; i++) {
			CardColor color = colors[i];
			for (int j = 1; j < 13; j++) {
				CardType type = types[j];
				cards.add(new Card(color, type));
				cards.add(new Card(color, type));
			}
		}

		// Create our wild cards
		for (int i = 13; i < 15; i++) {
			CardType type = types[i];
			for (int j = 0; j < 4; j++) {
				cards.add(new Card(CardColor.Wild, type));
			}
		}
	}

	// Shuffles the deck by utilizing the Collections Java utility:
	public void shuffleDeck() { Collections.shuffle(cards); }
	
	public List<Card> getDeck() {
		return cards;
	}
}