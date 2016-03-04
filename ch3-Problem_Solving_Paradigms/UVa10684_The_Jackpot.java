import java.io.*;
import java.util.Scanner;

class UVa10684{

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int caseNum = in.nextInt();
        while (caseNum != 0) {
            long currSum = 0;
            long max = -999999999;
            while (caseNum-- > 0) {
                long current = in.nextLong();
                if (current + currSum > 0) {
                    currSum += current;
                } else {
                    currSum = 0;
                }
                max = currSum > max ? currSum : max;
            }
            if (max > 0) {
                out.printf("The maximum winning streak is %d.\n", max);
            } else out.println("Losing streak.");
            caseNum = in.nextInt();
        }
        out.flush();
        out.close();
    }
}
