import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(System.out)));
        int caseNum = Integer.parseInt(in.readLine());
        for (int t = 0; t < caseNum; t++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            long[][][] cube = new long[a][b][c];
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    for (int k = 0; k < c; k++) {
                        cube[i][j][k] = Integer.parseInt(st.nextToken());
                    }
                }
            }
            out.println(kadane3D(cube));
        }
        out.flush();
        out.close();

    }
    public static long[] kadane1D(long[] arr){
        /* return value : int[] rst = {max. start(up), end(down) */
        long currSum = 0, max = 0, start = 0, end = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] + currSum > arr[i]) {
                currSum += arr[i];
                if (currSum > max) {
                    max = currSum;
                    end = i;
                }
            }
            else {
                currSum = arr[i];
                start = i;
            }
        }
        return new long[]{max, start, end};
    }

    public static long[] kadane2D(long[][] arr) {
        /* return value: int[] rst = {max, left, right, up, down} */
        long max = 0, left = 0, right = 0, up = 0, down = 0;
        for (int l = 0; l < arr[0].length; l++ ) {
            long[] temp = new long[arr.length];
            for (int r = l; r < arr[0].length; r++) {
                /* increment of temp arr */
                for (int i = 0; i < arr.length; i++) temp[i] += arr[i][r];
                long[] rst1D = kadane1D(temp);
                if (rst1D[0] > max) {
                    max = rst1D[0]; left = l; right = r; up = rst1D[1]; down = rst1D[2];
                }
            }
        }
        return new long[]{max, left, right, up, down};
    }

    public static long kadane3D(long[][][] arr) {
        /* return value: int rst = max */
        long max = 0;
        for (int cUp = 0; cUp < arr.length; cUp++) {
            long temp[][] = new long[arr[0].length][arr[0][0].length];
            for (int cDown = cUp; cDown < arr.length; cDown++) {
                for (int a = 0; a < arr[0].length; a++) {
                    for (int b = 0; b < arr[0][0].length; b++) {
                        temp[a][b] += arr[a][b][cDown];
                        long currSum = kadane2D(temp)[0];
                        if (currSum > max) max = currSum;
                    }
                }
            }
        }
        return max;
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
}
