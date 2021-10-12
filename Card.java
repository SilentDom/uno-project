
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
	
	private Color[] colors = Color.values();
	private Type[] types = Type.values();
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
