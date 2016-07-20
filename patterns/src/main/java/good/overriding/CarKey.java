package good.overriding;

/**
 * For example of equals.
 */
public class CarKey {
    private int number;
    private String color;

    public CarKey(int number, String color) {
        this.number = number;
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (obj == this)
            return true;

        if (!(obj instanceof CarKey))
            return false;

        CarKey tKey = (CarKey) obj;

        return !(tKey.number != number || tKey.color.equals(color));
    }
}
