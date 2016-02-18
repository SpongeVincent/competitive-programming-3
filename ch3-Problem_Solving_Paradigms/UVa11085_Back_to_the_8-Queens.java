import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa11085 {

    static ArrayList<int[]> allP = new ArrayList<int[]>();

    public static void main(String[] args) throws Throwable {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringTokenizer st = new StringTokenizer(in.readLine());
        backtrack(new int[8], 0);
        int caseNo = 1;
        while (st.countTokens() > 2) {
            int[] origin = new int[8];
            for (int i = 0; i < 8; i++) origin[i] = Integer.parseInt(st.nextToken());
            int[] diff = new int[allP.size()];
            int cnt = 0;
            for (int[] p : allP) {
                for (int i = 0; i < 8; i++) {
                    diff[cnt] += origin[i] != p[i] ? 1 : 0;
                }
                cnt++;
            }
            Arrays.sort(diff);
            out.printf("Case %d: %d\n", caseNo, diff[0]);
            try {
                st = new StringTokenizer(in.readLine());
                caseNo++;
            } catch (Exception e) {
                break;
            }
        }
        out.flush();
        out.close();
    }

    public static void backtrack(int[] board, int col) {
        if (col > 7) {
            allP.add(board);
        } else {
            for (int tryRow = 1; tryRow < 9; tryRow++) {
                if (check(board, col, tryRow)) {
                    int[] tempBoard = board.clone();
                    tempBoard[col] = tryRow;
                    backtrack(tempBoard, col + 1);
                }
            }
        }
    }

    public static boolean check(int[] board, int col, int tryRow) {
        for (int preCol = 0; preCol < col; preCol++) {
            if (tryRow == board[preCol] || Math.abs(board[preCol] - tryRow) == Math.abs(preCol - col)) {
                return false;
            }
        }
        return true;
    }
}
