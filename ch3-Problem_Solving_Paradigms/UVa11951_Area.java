import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class UVa10827 {

    static int N, M, layers, K, minCost, maxArea;
    static int[] temp;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(System.out)));
        int caseNum = Integer.parseInt(in.readLine().trim());

        for (int t = 1; t <= caseNum; t++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            int[][] board = new int[N][M];
            for (int a = 0; a < N; a++) {
                st = new StringTokenizer(in.readLine());
                for (int b = 0; b < M; b++) {
                    board[a][b] = Integer.parseInt(st.nextToken());;
                }
            }
            minCost = 0;
            maxArea = 0;
            kadane2D(board);
            out.printf("Case #%d: %d %d\n", t, maxArea, minCost);
        }
        out.flush();
        out.close();
    }

    public static void kadane1D(int cCost, int cArea, int start, int p) {
        if (p < M) {
            if (temp[p] > K) {
                kadane1D(0, 0, p + 1, p + 1);
            } else if (cCost + temp[p] > K) {
                kadane1D(cCost - temp[start], cArea - layers, start + 1, p);
            } else {
                cCost += temp[p];
                cArea += layers;
                if (cArea > maxArea) {
                    maxArea = cArea;
                    minCost = cCost;
                } else if (cArea == maxArea && cCost < minCost) minCost = cCost;
                kadane1D(cCost, cArea, start, p + 1);
            }
        }
    }

    public static void kadane2D(int[][] arr) {
        for (int l = 0; l < N; l++) {
            temp = new int[M];
            layers = 0;
            for (int r = l; r < N; r++) {
            /* increment of temp arr */
                layers++;
                for (int i = 0; i < M; i++) temp[i] += arr[r][i];
                kadane1D(0, 0, 0, 0);
            }
        }
    }
}
