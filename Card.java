public class Card {

	public enum Color {
		Green,
		Blue,
		Red,
		Yellow,
		Wild;

		private static Color[] colors = Color.values();
		public static Color getColor(int i) {
			return Color.colors[i];		
		}
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
		Skip,
		Reverse,
		DrawTwo,
		Wild,
		WildDrawFour;

		private static Type[] types = Type.values();
		public static Type getType(int i) {
			return Type.types[i];
		}
	}

	private Color color;
	private Type type;
	public Card(Color color, Type type) {
		this.color = color;
		this.type = type;
	}

	public Color getColor() {
		return color;
	}

	public Type getType() {
		return type;
	}

	/**
	 * 
	 * @param color
	 * @param type
	 * @return The image url for each unique card
	 */
	public String getImage(Color color, Type type) {
		if (color == Color.Blue) {
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
			default: return null;
			}
		}
		else if (color == Color.Green) {
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
			default: return null;
			}
		}
		else if (color == Color.Red) {
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
			default: return null;
			}
		}
		else if (color == Color.Yellow) {
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
			default: return null;
			}
		}
		else if (color == Color.Wild) {
			switch(type) {
			case Wild: return "/images/Wild_Wild.png";
			case WildDrawFour: return "/images/Wild_WildDrawFour.png";
			default: return null;
			}
		}
		else return null;
	}
	
	
	public void setColor(Color color) {
		this.color = color;
	}

	public void setType(Type type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return String.format("Card Color:%s%nCard Type:%s%n", color, type);
	}
}
