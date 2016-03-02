import java.io.*;
import java.util.*;

public class Main{
    static int A, B, C;
    static long max;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int caseNum = in.nextInt();
        for (int t = 0; t < caseNum; t++) {
            A = in.nextInt() + 1;
            B = in.nextInt() + 1;
            C = in.nextInt() + 1;
            long[][][] cube = new long[A][B][C];
            for (int a = 1; a < A; a++) {
                for (int b = 1; b < B; b++) {
                    for (int c = 1; c < C; c++) {
                        cube[a][b][c] = in.nextLong();

                        /* reconstruct summed space table */
                        /* --------------------------------- */
                        cube[a][b][c] += cube[a][b - 1][c] + cube[a][b][c - 1] - cube[a][b - 1][c -1]
                                         + cube[a - 1][b][c] - cube[a - 1][b - 1][c] - cube[a - 1][b][c - 1]
                                         + cube[a - 1][b - 1][c - 1];
                        /* --------------------------------- */
                    }
                }
            }
            max = Long.MIN_VALUE;
            /* using Kadane's algorithm is kinda slow for these question. complexity is O(n^5). */
            /* --------------------------------- */
//            kadane3D(cube);
            /* --------------------------------- */


            /* using summed space/area table will not get TL. complexity is O(n^5) */
            /* --------------------------------- */
            summedSpace(cube);
            /* --------------------------------- */
            System.out.println(max);
            if (t < caseNum - 1) System.out.println();
        }
    }

    public static void summedSpace(long[][][] cube) {
        for (int down = 1; down < A; down++) {
            for (int up = down; up < A; up++) {

                for (int front = 1; front < B; front++) {
                    for (int back = front; back < B; back++) {

                        long min = 0;
                        for (int c = 1; c < C; c++) {
                            long subSum = cube[up][back][c] - cube[down - 1][back][c]
                                          - cube[up][front - 1][c]  + cube[down - 1][front - 1][c];
                            max = Math.max(max, subSum - min);

                            min = subSum < min ? subSum : min;
                        }
                    }
                }
            }
        }
    }

    public static void kadane1D(long[] arr) {
        long currSum = 0;
        for (long num : arr) {
            if (num + currSum >= num) {
                currSum += num;
                max = currSum > max ? currSum : max;
            } else currSum = num;
        }
        max = currSum > max ? currSum : max;
    }

    public static void kadane2D(long[][] arr) {
        for (int l = 0; l < arr[0].length; l++) {
            long[] temp = new long[arr.length];
            for (int r = l; r < arr[0].length; r++) {
            /* increment of temp arr */
                for (int i = 0; i < arr.length; i++) temp[i] += arr[i][r];
                kadane1D(temp);
            }
        }
    }

    public static void kadane3D(long[][][] arr) {
    /* return value: int rst = max */
        for (int down = 1; down < A; down++) {
            long temp[][] = new long[B][C];
            for (int up = down; up < A; up++) {
            /* increment of temp arr */
                for (int b = 1; b < B; b++) {
                    for (int c = 1; c < C; c++) {
                        temp[b][c] += arr[up][b][c];
                        kadane2D(temp);
                    }
                }
            }
        }
    }
}
