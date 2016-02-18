import java.io.*;
import java.util.*;

public class UVa12192 {

    static int n, m, caseNum, lower, upper;
    static int[][] flat;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder(30000);
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        while (n != 0 && m != 0) {
            flat = new int[n][m];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < m; j++) {
                    flat[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            caseNum = Integer.parseInt(in.readLine().trim());
            for (int k = 0; k < caseNum; k++) {
                st = new StringTokenizer(in.readLine());
                lower = Integer.parseInt(st.nextToken());
                upper = Integer.parseInt(st.nextToken());
                int rst = 0;
                int lBound = m -1;
                for (int i = 0;  i < n; i++) {
                    lBound = lower_bound(i, lBound);
                    if (lBound == -1) {
                        lBound = m - 1;
                        continue;
                    }
                    for (int j = rst; lBound + j < m && i + j < n; j++) {
                        if (flat[i + j][lBound + j] <= upper) rst++;
                        else break;
                    }
                }
                sb.append(rst);
                sb.append("\n");
            }
            sb.append("-\n");
            if (sb.length() > 25000) {
                out.write(sb.toString());
                sb = new StringBuilder(30000);
            }
            st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
        }
        out.write(sb.toString());
        out.flush();
        out.close();
    }

    public static int lower_bound(int row, int lastLBound) {
        int low = 0;
        int high = lastLBound;
        int mid;
        int answer = -1;
        while(low <= high) {
            mid = low + (high - low) / 2;
            if (flat[row][mid] >= lower) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return answer;
    }
}
