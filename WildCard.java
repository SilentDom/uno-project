
public class WildCard extends Card {

	public WildCard(String cardColor, String cardType) {
		super(cardColor, cardType);
		
	}
	
	@Override
	public String toString() {
		return String.format("%s", super.toString());
	}
}
