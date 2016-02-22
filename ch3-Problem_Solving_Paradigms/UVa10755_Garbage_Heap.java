import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

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
            int[][][] cube = new int[a][b][c];
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    for (int k = 0; k < c; k++) {
                        cube[i][j][k] = Integer.parseInt(st.nextToken());
                    }
                }
            }
        }
        out.flush();
        out.close();
    }
}
