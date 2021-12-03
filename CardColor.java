
public enum CardColor {
    Green(1),
    Blue(2),
    Red(3),
    Yellow(4),
    Wild(5);

    private int colorValue;

    CardColor(int colorValue) {
        this.colorValue = colorValue;
    }

    public int getCardColorValue() {
        return this.colorValue;
    }
}
