import java.util.ArrayList;

public class Codeword {
    private ArrayList<Bit> bits;
    private String dataWord;
    private boolean isEvenParity;

    public Codeword(String dataWord, boolean isEvenParity) {
        bits = new ArrayList<>();
        this.dataWord = dataWord;
        this.isEvenParity = isEvenParity;
        initialize();
        setDataBits();
        setParityBits();
    }

    private void initialize() {
        int dataBitsToPlace = dataWord.length();
        int position = 1;
        while (dataBitsToPlace > 0) {
            if (isParityBit(position)) {
                addBit(new Bit(position, true));
            } else {
                addBit(new Bit(position, false));
                dataBitsToPlace--;
            }
            position++;
        }
    }

    private void setDataBits() {
        int indexOfDataWord = dataWord.length() - 1;
        for (int i = bits.size() - 1; i >= 0; i--) {
            Bit bit = bits.get(i);
            if (!(bit.isParity())) {
                if (indexOfDataWord >= 0) {
                    String bitAsString = dataWord.substring(indexOfDataWord, indexOfDataWord + 1);
                    int bitAsInt = Integer.parseInt(bitAsString);
                    bit.setValue(bitAsInt);
                    indexOfDataWord--;
                } else {
                    bit.setValue(0);
                }
            }
        }
    }

    private void setParityBits() {
        for (int i = bits.size() - 1; i >= 0; i--) {
            Bit bit = bits.get(i);
            if (bit.isParity()) {
                if (sumOfBitsAffectingThisParity(bit.getPosition()) % 2 == 0) {
                    if (isEvenParity) {
                        bit.setValue(0);
                    } else {
                        bit.setValue(1);
                    }
                } else {
                    if (isEvenParity) {
                        bit.setValue(1);
                    } else {
                        bit.setValue(0);
                    }
                }
            }
        }
    }

    private int sumOfBitsAffectingThisParity(int position) {
        int sum = 0;
        for (Bit b : bits) {
            if (b.getAffectedBits().contains(position)) {
                sum += b.getValue();
            }
        }
        return sum;
    }

    private boolean isParityBit(int a) {
        return a > 0 && ((a & (a - 1)) == 0);
    }

    public void addBit(Bit b) {
        if (!(b.isParity())) {
            b.calculateAffectedBits();
        }
        bits.add(b);
    }

    public String getDataWord() {
        return dataWord;
    }

    public String toString() {
        String out = "";
        for (Bit b : bits) {
            out += b.getValue();
        }
        return out;
    }
}
