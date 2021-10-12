
public class NumberCard extends Card {

	private int cardNumber;
	
	public NumberCard(String cardColor, String cardType, int cardNumber) {
		super(cardColor, cardType);
		this.cardNumber = cardNumber;
	}

	@Override
	public String toString() {
		return String.format("%s%nCard Number: %s%n", super.toString(), cardNumber);
	}
}
