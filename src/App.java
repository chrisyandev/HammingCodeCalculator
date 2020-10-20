import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int inputChoice = requestInputType();

        if (inputChoice == 1) {
            System.out.println("Enter the number of bits:");
            int numOfDataBits = scan.nextInt();
            ArrayList<Codeword> codewords;
            codewords = generateAllCodewords(numOfDataBits, isEvenParity());
            for (Codeword c : codewords) {
                System.out.println(c);
            }
        } else if (inputChoice == 2) {
            System.out.println("Enter the data word:");
            String dataWord = scan.next();
            Codeword codeword = new Codeword(dataWord, isEvenParity());
            System.out.println(codeword);
        }
    }

    public static boolean isEvenParity() {
        System.out.println("Even or odd parity? (Enter 'even' or 'odd')");
        String reply = scan.next().toLowerCase();
        if (reply.equals("even")) {
            return true;
        }
        if (reply.equals("odd")) {
            return false;
        }
        System.out.println("Not a valid choice.");
        return isEvenParity();
    }

    public static int requestInputType() {
        System.out.println("What would you like to input? (choose a number):");
        System.out.println("1) Number of bits in data word");
        System.out.println("2) The data word");
        int inputChoice = scan.nextInt();
        if (inputChoice == 1) {
            return 1;
        }
        if (inputChoice == 2) {
            return 2;
        }
        System.out.println("Not a valid choice.");
        return requestInputType();
    }

    public static ArrayList<Codeword> generateAllCodewords(int numOfDataBits, boolean isEvenParity) {
        ArrayList<Codeword> codewords = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, numOfDataBits); i++) {
            String dataWord = Integer.toBinaryString(i);
            while (dataWord.length() < numOfDataBits) {
                dataWord = "0" + dataWord;
            }
            Codeword codeword = new Codeword(dataWord, isEvenParity);
            codewords.add(codeword);
        }
        return codewords;
    }
}
