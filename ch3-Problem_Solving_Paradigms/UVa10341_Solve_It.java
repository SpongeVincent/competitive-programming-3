import java.io.*;
import java.util.StringTokenizer;
import java.lang.Math;

public class UVa10341 {

    static int[] coe;
    static final double EPS = 1e-15;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(System.out)));
        StringTokenizer st = new StringTokenizer(in.readLine());
        while (st.hasMoreTokens()) {
            coe = new int[6];
            for (int i = 0; i < 6; i++) coe[i] = Integer.parseInt(st.nextToken());
            double upper = 1.0;
            double lower = 0.0;
            double rst = 0.0;
            double a = function(upper), b = function(lower);
            if (a * b > EPS) {
                out.println("No solution");
            } else if (Math.abs(a) < EPS) {
                out.println("1.0000");
            } else if (Math.abs(b) < EPS) {
                out.println("0.0000");
            } else {
                while (Math.abs(upper - lower) > EPS) {
                    double mid = (upper + lower) / 2.0;
                    if (function(mid) * function(upper) > EPS) {
                        upper = mid;
                    } else {
                        lower = mid;
                    }
                    rst = mid;
                }
                out.printf("%.4f\n", rst);
            }
            try {
                st = new StringTokenizer(in.readLine());
            } catch (Exception e) {
                break;
            }
        }
        out.flush();
        out.close();
    }

    public static double function(double x) {
        return coe[0] * Math.exp(-x) + coe[1] * Math.sin(x) + coe[2] * Math.cos(x) +
                coe[3] * Math.tan(x) + coe[4] * Math.pow(x, 2) + coe[5];
    }
}
