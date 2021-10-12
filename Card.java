
public abstract class Card {

	private String cardColor;
	private String cardType;
	
	public enum Color {
		GREEN,
		BLUE,
		RED,
		YELLOW;
	}
	
	public enum Type {
		NUMBER,
		WILD,
		SKIP,
		REVERSE,
		DRAWTWO,
	}
	
	public Card(String cardColor, String cardType) {
		this.cardColor = cardColor;
		this.cardType = cardType;
	}

	public String getCardColor() {
		return cardColor;
	}
	
	public String getCardType() {
		return cardType;
	}
	
	@Override
	public String toString() {
		return String.format("Card Color: %s%nCard Type: %s%n", cardColor, cardType);
	}
}
