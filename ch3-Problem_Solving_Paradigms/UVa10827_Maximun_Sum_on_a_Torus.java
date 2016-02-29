import java.io.IOException;
import java.util.Scanner;

public class UVa10827 {

    static int size;
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int caseNum = in.nextInt();
        for (int t = 0; t < caseNum; t++) {
            size = in.nextInt();
            int[][] board = new int[size * 2][size * 2];
            for (int a = 0; a < size; a++) {
                for (int b = 0; b < size; b++) {
                    board[b][a] = board[b + size][a] = board[b][a + size]
                            = board[b + size][a + size] = in.nextInt();
                }
            }
            System.out.println(kadane2D(board));
        }
    }

    public static int kadane1D(int[] arr) {
        int max = -1000;
        for (int start = 0; start < size; start++) {
            int currSum = 0;
            for (int i = start; i < start + size; i++) {
                if (arr[i] + currSum >= arr[i]) {
                    currSum += arr[i];
                    max = currSum > max ? currSum : max;
                } else {
                    currSum = arr[i];
                }
            }
            max = currSum > max ? currSum : max;
        }
        return max;
    }

    public static int kadane2D(int[][] arr) {
        int max = -1000;
        for (int l = 0; l < size; l++) {
            int[] temp = new int[arr.length];
            for (int r = l; r < l + size; r++) {
            /* increment of temp arr */
                for (int i = 0; i < arr.length; i++) temp[i] += arr[r][i];
                int rst1D = kadane1D(temp);
                max = rst1D > max ? rst1D : max;
            }
        }
        return max;
    }
}
