package Main;

public enum Colors {

    WHITE (6),
    BLACK (7),
    BLUE (2),
    GREEN (1),
    RED (0) ,
    YELLOW (3);

    public final int colorValue;


    private Colors(int colorValue) {
        this.colorValue = colorValue;
    }

    public int getValue() {
        return colorValue;
    }
}
