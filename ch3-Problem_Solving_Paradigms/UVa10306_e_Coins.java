import java.io.*;
import java.util.*;

/* Idea: Complete knapsack problem. Using the nested iteration run through all the possible "pack capacity" to
find the minimun coins amount to reach the e-value. 
Transition function: dp[c][i] = min(dp[c][i], dp[c - cValues[n]][i - iValues[n]] + 1) (this slot contains the number
of coins.) The function means that for current c and i value, whether current c and i value or add one more current
coin(c -= cValues[n], i -= iValues[n]) returns the minimum answer.
*/


class Main {

    static int[] cValues, iValues, eValues;
    static int m, s;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int caseNum = in.nextInt();
        while (caseNum-- > 0) {
            m = in.nextInt();
            s = in.nextInt();
            cValues = new int[m]; iValues = new int[m]; eValues = new int[m];
            for (int i = 0; i < m; i++) {
                cValues[i] = in.nextInt();
                iValues[i] = in.nextInt();
            }
            int[][] dp = new int[301][301];
            for (int i = 0; i < 301; i++) Arrays.fill(dp[i], 999999999);
            dp[0][0] = 0;
            int rst = 999999999;
            for (int n = 0; n < m; n++) {
                for (int c = cValues[n]; c <= s; c++) {
                    for (int i = iValues[n]; i <= s; i++) {
                        dp[c][i] = Math.min(dp[c][i], dp[c - cValues[n]][i - iValues[n]] + 1);
                        if (c * c + i * i == s * s) rst = Math.min(dp[c][i], rst);
                    }
                }
            }
            out.println(rst == 999999999 ? "not possible" : rst);
        }
        out.close();
    }
}
