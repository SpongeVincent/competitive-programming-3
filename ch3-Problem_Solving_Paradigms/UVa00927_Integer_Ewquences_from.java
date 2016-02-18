import java.io.*;
import java.util.StringTokenizer;
import java.lang.Math;

public class UVa00927 {

    public static void main(String[] args) throws Throwable{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int caseNo = Integer.parseInt(in.readLine());
        for (int i = 0; i < caseNo; i++) {
            StringTokenizer coe = new StringTokenizer(in.readLine());
            int d = Integer.parseInt(in.readLine());
            int k = Integer.parseInt(in.readLine());
            int n = 0;
            int range = 0;
            do {
                n++;
                range = range + d * n;
            } while (k > range);
            long rst = 0;
            int p = 0;
            coe.nextToken();
            while (coe.hasMoreTokens()) {
                rst += Math.pow(n, p) * Integer.parseInt(coe.nextToken());
                p++;
            }
            out.println(rst);
        }
        out.flush();
        out.close();

    }
}
