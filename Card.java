
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

	@Override
	public String toString() {
		return String.format("Card Color:%s%nCard Type:%s%n", color, type);
	}
}
