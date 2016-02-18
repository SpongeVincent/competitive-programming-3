import java.io.*;
import java.util.*;

public class UVa11100 {

    static int[] arr2;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(System.out)));
        int bagsNum = Integer.parseInt(in.readLine().trim());
        while (bagsNum > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            arr2 = new int[bagsNum];
            for (int i = 0; i < bagsNum; i++) arr2[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(arr2);
            int previous = arr2[0];
            int max = 0;
            int cnt = 0;
            for (int i = 0; i < bagsNum; i++) {
                int current = arr2[i];
                if (current != previous) {
                    max = cnt > max ? cnt : max;
                    cnt = 1;
                    previous = current;
                } else cnt++;
            }
            max = cnt > max ? cnt : max;
            out.println(max);
            for (int i = 0; i < max; i++) {
                out.print(arr2[i]);
                for (int j = i + max; j < bagsNum; j += max) {
                    out.print(" " + arr2[j]);
                }
                out.print("\n");
            }
            out.print("\n");
            bagsNum = Integer.parseInt(in.readLine().trim());
        }
        out.flush();
        out.close();
    }
}
