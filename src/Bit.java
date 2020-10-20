import java.util.ArrayList;

public class Bit {
    private int position;
    private int value;
    private boolean isParity;
    private ArrayList<Integer> affectedBits;

    public Bit(int position, boolean isParity) {
        this.position = position;
        this.isParity = isParity;
        affectedBits = new ArrayList<>();
    }

    public int getPosition() {
        return position;
    }

    public int getValue() {
        return value;
    }

    public boolean isParity() {
        return isParity;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ArrayList<Integer> getAffectedBits() {
        return affectedBits;
    }

    /**
     * _ _ _ _ _ _ _
     * 1 2 2 4 4 4 4
     *     1   1 2 2
     *             1
     * At each non-parity position, there are a series of powers
     * of twos that add up to the position number. When a bit at
     * one of these positions is changed, the parity positions
     * corresponding to the series of power of twos will also change.
     * This method determines the series of power of twos.
     */
    public void calculateAffectedBits() {
        int value = position;
        while (value > 1) {
            value--;
            if (isPowerOfTwo(value) && lessThanOrEqualToPosition(value)) {
                affectedBits.add(value);
            }
        }
    }

    /* False means you should try a lower value */
    private boolean lessThanOrEqualToPosition(int value) {
        int sum = value;
        for (int v : affectedBits) {
            sum += v;
        }
        if (sum <= position) {
            return true;
        }
        return false;
    }

    /* Uses bitwise operator to determine if a number is a power of 2 */
    private boolean isPowerOfTwo(int a) {
        return a > 0 && ((a & (a - 1)) == 0);
    }
}
