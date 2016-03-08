import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

class Main {

    static int n, q, d, m;
    static long[] N;
    static int[] D, M;
    static int[][][] memo;

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        n = in.nextInt();
        q = in.nextInt();
        int cnt = 0;
        while (n != 0 && q != 0) {
            cnt++;
            N = new long[n];
            D = new int[q];
            M = new int[q];
            for (int i = 0; i < n; i++) {
                N[i] = in.nextInt();
            }
            for (int i = 0; i < q; i++) {
                D[i] = in.nextInt();
                M[i] = in.nextInt();
            }
            out.printf("SET %d:\n", cnt);
            for (int i = 0; i < q; i++) {
                d = D[i];
                m = M[i];
                memo = new int[n][m][d];
                for (int k = 0; k < n; ++k)
                    for (int j = 0; j < m; ++j)
                        Arrays.fill(memo[k][j], -1);
                out.printf("QUERY %d: %d\n", i + 1, dp(0, 0, 0));
            }
            n = in.nextInt();
            q = in.nextInt();
        }
        out.close();
    }

    static int dp(int p, int cnt, int sum) {
        if (cnt == m && sum == 0) return 1;
        if (p == n || cnt == m) return 0;
        if (p < n && cnt < m) {
            if (memo[p][cnt][sum] == -1) {
                memo[p][cnt][sum] = dp(p + 1, cnt + 1, (int) (((sum + N[p]) % d + d) % d)) +
                        dp(p + 1, cnt, sum);
            }
            return memo[p][cnt][sum];
        }
        return 0;
    }
}
