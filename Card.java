
public class Card {

	private CardColor color;
	private CardType type;

	// Controller for the Card class, accepts a Card color and type:
	public Card(CardColor color, CardType type) {
		this.color = color;
		this.type = type;
	}

	// Method for getting the image of the card:
	public String getImage(CardColor color, CardType type) {
		if (color == CardColor.Blue) {
			switch(type) {
				case Zero: return "/images/Blue_Zero.png";
				case One: return "/images/Blue_One.png";
				case Two: return "/images/Blue_Two.png";
				case Three: return "/images/Blue_Three.png";
				case Four: return "/images/Blue_Four.png";
				case Five: return "/images/Blue_Five.png";
				case Six: return "/images/Blue_Six.png";
				case Seven: return "/images/Blue_Seven.png";
				case Eight: return "/images/Blue_Eight.png";
				case Nine: return "/images/Blue_Nine.png";
				case DrawTwo: return "/images/Blue_DrawTwo.png";
				case Skip: return "/images/Blue_Skip.png";
				case Reverse: return "/images/Blue_Reverse.png";
				case Wild: return "/images/Blue_Wild.png";
				case WildDrawFour: return "/images/Blue_WildDrawFour.png";
				default: return null;
			}
		}
		else if (color == CardColor.Green) {
			switch(type) {
				case Zero: return "/images/Green_Zero.png";
				case One: return "/images/Green_One.png";
				case Two: return "/images/Green_Two.png";
				case Three: return "/images/Green_Three.png";
				case Four: return "/images/Green_Four.png";
				case Five: return "/images/Green_Five.png";
				case Six: return "/images/Green_Six.png";
				case Seven: return "/images/Green_Seven.png";
				case Eight: return "/images/Green_Eight.png";
				case Nine: return "/images/Green_Nine.png";
				case DrawTwo: return "/images/Green_DrawTwo.png";
				case Skip: return "/images/Green_Skip.png";
				case Reverse: return "/images/Green_Reverse.png";
				case Wild: return "/images/Green_Wild.png";
				case WildDrawFour: return "/images/Green_WildDrawFour.png";
				default: return null;
			}
		}
		else if (color == CardColor.Red) {
			switch(type) {
				case Zero: return "/images/Red_Zero.png";
				case One: return "/images/Red_One.png";
				case Two: return "/images/Red_Two.png";
				case Three: return "/images/Red_Three.png";
				case Four: return "/images/Red_Four.png";
				case Five: return "/images/Red_Five.png";
				case Six: return "/images/Red_Six.png";
				case Seven: return "/images/Red_Seven.png";
				case Eight: return "/images/Red_Eight.png";
				case Nine: return "/images/Red_Nine.png";
				case DrawTwo: return "/images/Red_DrawTwo.png";
				case Skip: return "/images/Red_Skip.png";
				case Reverse: return "/images/Red_Reverse.png";
				case Wild: return "/images/Red_Wild.png";
				case WildDrawFour: return "/images/Red_WildDrawFour.png";
				default: return null;
			}
		}
		else if (color == CardColor.Yellow) {
			switch(type) {
				case Zero: return "/images/Yellow_Zero.png";
				case One: return "/images/Yellow_One.png";
				case Two: return "/images/Yellow_Two.png";
				case Three: return "/images/Yellow_Three.png";
				case Four: return "/images/Yellow_Four.png";
				case Five: return "/images/Yellow_Five.png";
				case Six: return "/images/Yellow_Six.png";
				case Seven: return "/images/Yellow_Seven.png";
				case Eight: return "/images/Yellow_Eight.png";
				case Nine: return "/images/Yellow_Nine.png";
				case DrawTwo: return "/images/Yellow_DrawTwo.png";
				case Skip: return "/images/Yellow_Skip.png";
				case Reverse: return "/images/Yellow_Reverse.png";
				case Wild: return "/images/Yellow_Wild.png";
				case WildDrawFour: return "/images/Yellow_WildDrawFour.png";
				default: return null;
			}
		}
		else if (color == CardColor.Wild) {
			switch(type) {
				case Wild: return "/images/Wild_Wild.png";
				case WildDrawFour: return "/images/Wild_WildDrawFour.png";
				default: return null;
			}
		}
		else return null;
	}

	public void setWildColor(CardColor color) {
		this.color = color;
	}

	// Gets the color of the card:
	public CardColor getCardColor() {
		return color;
	}

	// Gets the type of the card (Zero, Draw Two, Reverse, etc.):
	public CardType getCardType() {
		return type;
	}

	@Override
	public String toString() {
		return String.format("Card Color:%s%nCard Type:%s%n", color, type);
	}
}