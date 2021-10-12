
public class Card {
	
	public enum Color {
		Green,
		Blue,
		Red,
		Yellow;
	}
	
	public enum Type {
		Zero,
		One,
		Two,
		Three,
		Four,
		Five,
		Six,
		Seven,
		Eight,
		Nine,
		Wild,
		Skip,
		Reverse,
		DrawTwo,
		WildDrawFour;
	}
	
	private String cardNumber;
	private String cardType;
	private String cardColor;
	private Color[] colors = Color.values();
	private Type[] types = Type.values();
	
	public Card(Color[] colors, Type[] types) {
		this.colors = colors;
		this.types = types;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getCardColor() {
		return cardColor;
	}

	public void setCardColor(String cardColor) {
		this.cardColor = cardColor;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardType() {
		return cardType;
	}
	
	@Override
	public String toString() {
		return String.format("Card Color: %s%nCard Number: %s%nCard Type: %s%n", cardColor, cardNumber, cardType);
	}
}
