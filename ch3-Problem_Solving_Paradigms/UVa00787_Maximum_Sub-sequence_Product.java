import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(System.out)));
        while (in.hasNextInt()) {
            ArrayList<Integer> arr = new ArrayList<>();
            int n = in.nextInt();
            while (n != -999999) {
                arr.add(n);
                n = in.nextInt();
            }
            BigInteger max = BigInteger.valueOf(arr.get(0));
            for (int i = 0; i < arr.size(); i++) {
                BigInteger p = BigInteger.valueOf(1);
                for (int j = i; j < arr.size(); j++) {
                    p = p.multiply(BigInteger.valueOf(arr.get(j)));
                    max = p.compareTo(max) == 1 ? p : max;
                }
            }
            out.println(max.toString());
        }
        out.flush();
        out.close();
    }
}
