import java.io.*;
import java.util.*;

class Main {

    static int m, n;
    static int[] prices, favours;
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        while (in.hasNextInt()) {
            m = in.nextInt();
            n = in.nextInt();
            prices = new int[n]; favours = new int[n];
            memo = new int[n][m + 300];
            for (int i = 0; i < n; i++) Arrays.fill(memo[i], -1);
            for (int i = 0; i < n; i++) {
                prices[i] = in.nextInt();
                favours[i] = in.nextInt();
            }
            out.println(dp(0, 0));
        }
        out.close();
    }

    static int dp(int spent, int p) {
        if (spent > m && (m < 1800 || spent > m + 200)) return -9999999;/* spend too much even including the refund */
        if (p == n && spent > m && spent <= 2000)       return -9999999; /* final check, can't trick the refund */
        if (p == n)                                     return 0;
        if (memo[p][spent] != -1)                       return memo[p][spent];
        return memo[p][spent] = Math.max(dp(spent, p + 1), favours[p] + dp(spent + prices[p], p + 1));
    }

}
