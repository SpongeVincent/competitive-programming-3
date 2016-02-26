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
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            long[][][] cube = new long[C][A][B];
            st = new StringTokenizer(in.readLine());
            for (int a = 0; a < A; a++) {
                for (int b = 0; b < B; b++) {
                    for (int c = 0; c < C; c++) {
                        cube[c][a][b] = Integer.parseInt(st.nextToken());
                    }
                }
            }
            out.println(kadane3D(cube));
            if (t < caseNum - 1) out.println();
        }
        out.flush();
        out.close();
    }

    public static long kadane1D(long[] arr){
        long currSum = 0, max = -9999999999l;
        for (long num : arr) {
            if (num + currSum >= num) {
                currSum += num;
                max = currSum > max ? currSum : max;
            }
            else currSum = num;
        }
        max = currSum > max ? currSum : max;
        return max;
    }

    public static long kadane2D(long[][] arr) {
        long max = -99999999999l;
        for (int l = 0; l < arr[0].length; l++ ) {
            long[] temp = new long[arr.length];
            for (int r = l; r < arr[0].length; r++) {
                for (int i = 0; i < arr.length; i++) temp[i] += arr[i][r];
                long rst1D = kadane1D(temp);
                max = rst1D > max ? rst1D : max;
            }
        }
        return max;
    }

    public static long kadane3D(long[][][] arr) {
        long max = -99999999999l;
        for (int cUp = 0; cUp < arr.length; cUp++) {
            long temp[][] = new long[arr[0].length][arr[0][0].length];
            for (int cDown = cUp; cDown < arr.length; cDown++) {
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
