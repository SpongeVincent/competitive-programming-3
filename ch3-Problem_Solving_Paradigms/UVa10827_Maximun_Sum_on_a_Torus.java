import java.io.IOException;
import java.util.Scanner;

public class Main {

    static int size;
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        Scanner in = new Scanner(System.in);
        int caseNum = in.nextInt();
        for (int t = 0; t < caseNum; t++) {
            size = in.nextInt();
            int[][] board = new int[size * 2][size];
            for (int a = 0; a < size; a++) {
                for (int b = 0; b < size; b++) {
                    board[b][a] = in.nextInt();
                }
            }
            for (int i = size; i < size * 2; i++) {
                board[i] = board[i - size].clone();
            }
            startTime = System.currentTimeMillis();
            System.out.println(kadane2D(board));
        }
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) / 1000.0);
    }

    public static int kadane1D(int[] arr) {
        int currSum = 0, max = -1000;
        for (int num : arr) {
            if (num + currSum >= num) {
                currSum += num;
                max = currSum > max ? currSum : max;
            } else currSum = num;
        }
        max = currSum > max ? currSum : max;
        return max;
    }

    public static int kadane2D(int[][] arr) {
        int max = -1000;
        for (int l = 0; l < size; l++) {
            int[] temp = new int[size];
            for (int r = l; r < l + size; r++) {
            /* increment of temp arr */
                for (int i = 0; i < size; i++) temp[i] += arr[r][i];
                int rst1D = kadane1D(temp);
                max = rst1D > max ? rst1D : max;
            }
        }
        return max;
    }
}
