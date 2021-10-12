
public class SpecialCard extends Card {

	public SpecialCard(String cardColor, String cardType) {
		super(cardColor, cardType);
		
	}

	@Override
	public String toString() {
		return String.format("%s", super.toString());
	}
}
