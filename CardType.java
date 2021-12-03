public enum CardType {
	Zero(1),
	One(2),
	Two(3),
	Three(4),
	Four(5),
	Five(6),
	Six(7),
	Seven(8),
	Eight(9),
	Nine(10),
	Skip(11),
	Reverse(12),
	DrawTwo(13),
	Wild(14),
	WildDrawFour(15);

	private int typeValue;

	CardType(int typeValue) {
		this.typeValue = typeValue;
	}
}
