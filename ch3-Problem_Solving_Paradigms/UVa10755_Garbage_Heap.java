import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int caseNum = in.nextInt();
        for (int t = 0; t < caseNum; t++) {
            int A = in.nextInt();
            int B = in.nextInt();
            int C = in.nextInt();
            long[][][] cube = new long[C][A][B];
            for (int a = 0; a < A; a++) {
                for (int b = 0; b < B; b++) {
                    for (int c = 0; c < C; c++) {
                        cube[c][a][b] = in.nextLong();
                    }
                }
            }
            System.out.println(kadane3D(cube));
            if (t < caseNum - 1) System.out.println();
        }
    }

    public static long kadane1D(long[] arr) {
        long currSum = 0, max = Long.MIN_VALUE;
        for (long num : arr) {
            if (num + currSum >= num) {
                currSum += num;
                max = currSum > max ? currSum : max;
            } else currSum = num;
        }
        max = currSum > max ? currSum : max;
        return max;
    }

    public static long kadane2D(long[][] arr) {
        long max = Long.MIN_VALUE;
        for (int l = 0; l < arr[0].length; l++) {
            long[] temp = new long[arr.length];
            for (int r = l; r < arr[0].length; r++) {
                /* increment of temp arr */
                for (int i = 0; i < arr.length; i++) temp[i] += arr[i][r];
                long rst1D = kadane1D(temp);
                max = rst1D > max ? rst1D : max;
            }
        }
        return max;
    }

    public static long kadane3D(long[][][] arr) {
        /* return value: int rst = max */
        long max = Long.MIN_VALUE;
        for (int cUp = 0; cUp < arr.length; cUp++) {
            long temp[][] = new long[arr[0].length][arr[0][0].length];
            for (int cDown = cUp; cDown < arr.length; cDown++) {
                /* increment of temp arr */
                for (int a = 0; a < arr[0].length; a++) {
                    for (int b = 0; b < arr[0][0].length; b++) {
                        temp[a][b] += arr[cDown][a][b];
                        long currSum = kadane2D(temp);
                        max = currSum > max ? currSum : max;
                    }
                }
            }
        }
        return max;
    }
}

//    public static int[] kadane1D(int[] arr){
//        /* return value : int[] rst = {max. start(up), end(down) */
//        int currSum = 0, max = 0, start = 0, end = 0;
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] + currSum > arr[i]) {
//                currSum += arr[i];
//                if (currSum > max) {
//                    max = currSum;
//                    end = i;
//                }
//            }
//            else {
//                currSum = arr[i];
//                start = i;
//            }
//        }
//        return new int[]{max, start, end};
//    }

//    public static int[] kadane2D(int[][] arr) {
//        /* return value: int[] rst = {max, left, right, up, down} */
//        int max = 0, left = 0, right = 0, up = 0, down = 0;
//        for (int l = 0; l < arr[0].length; l++ ) {
//            int[] temp = new int[arr.length];
//            for (int r = l; r < arr[0].length; r++) {
//                /* increment of temp arr */
//                for (int i = 0; i < arr.length; i++) temp[i] += arr[i][r];
//                int[] rst1D = kadane1D(temp);
//                if (rst1D[0] > max) {
//                    max = rst1D[0]; left = l; right = r; up = rst1D[1]; down = rst1D[2];
//                }
//            }
//        }
//        return new int[]{max, left, right, up, down};
//    }
