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

    public void calculateAffectedBits() {
        int value = position;
        while (value > 1) {
            value--;
            if (isPowerOfTwo(value) && lessThanOrEqualToPosition(value)) {
                affectedBits.add(value);
            }
        }
    }

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

    private static boolean isPowerOfTwo(int a) {
        return a > 0 && ((a & (a - 1)) == 0);
    }
}
