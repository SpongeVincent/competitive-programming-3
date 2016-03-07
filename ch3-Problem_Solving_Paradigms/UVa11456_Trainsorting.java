import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Main {
    
    /* idea: for a specific point/train we want to choose, the LHS of the point 
    should be decreasing till the point and the RHS of the point should be also 
    decreasing but starts from the point. Therefore, we need to find out both of
    the LIS and LDS starting from the back. Then, iterate all the trains to find
    out maximum value of LIS[i] + LDS[i] - 1, which is the answer.
     */

    public static void main (String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int caseNum = in.nextInt();
        while (caseNum-- > 0) {
            int n = in.nextInt();
            int[] valuesP = new int[n];
            int[] valuesN = new int[n];
            while (n-- > 0) {
                int temp = in.nextInt();
                valuesP[n] = temp;
                valuesN[n] = -temp;
            }
            int max = 0;
            int[] lis = lis(valuesN);
            int[] lds = lis(valuesP);
            for (int i = 0; i < lis.length; i++) {
                max = Math.max(max, lis[i] + lds[i] - 1);
            }
            out.println(max);
        }
        out.close();
    }

    static int[] lis(int[] values) {
        ArrayList<Integer> temp = new ArrayList<>();
        int[] lis = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            int pos = Collections.binarySearch(temp, values[i]);
            if (pos < 0) {
                pos = -(pos + 1);
                if (pos == temp.size()) {
                    temp.add(values[i]);
                } else {
                    temp.set(pos, values[i]);
                }
            }
            lis[i] = pos + 1;
        }
        return lis;
    }
}
