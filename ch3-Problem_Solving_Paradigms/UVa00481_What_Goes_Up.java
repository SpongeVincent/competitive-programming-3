import java.io.*;
import java.util.*;

class Main {

    static ArrayList<Integer> LIS_V, LIS_P, values;
    static int pos = 0;
    static int[] pre;

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        values = new ArrayList<>();
        while (in.hasNextInt()) {
            values.add(in.nextInt());
        }
        int size = values.size();

        /* initialize LIS_V and its predecessor recorder and values' predecessor recorder. */
        LIS_V = new ArrayList<>();
        LIS_P = new ArrayList<>();
        pre = new int[size];
        if (size > 0) {
            LIS_V.add(values.get(0)); LIS_P.add(0);
        }

        /* use binary search to find out the LIS */
        int p = 0;
        for (int v : values) {
            pos = Collections.binarySearch(LIS_V, v);
            if (pos < 0) {
                pos = -(pos + 1);
                if (pos == 0) {
                    LIS_P.set(pos, p);
                    LIS_V.set(pos, v);
                } else if (pos == LIS_V.size()) {
                    pre[p] = LIS_P.get(pos - 1);
                    LIS_P.add(p);
                    LIS_V.add(v);
                } else {
                    pre[p] = LIS_P.get(pos - 1);
                    LIS_P.set(pos, p);
                    LIS_V.set(pos, v);
                }
            }
            p++;
        }

        /* track back to print the LIS */
        int cnt = LIS_V.size();
        int[] rst = new int[cnt];
        int pointer = LIS_P.get(cnt - 1);
        while (cnt > 0) {
            rst[--cnt] = values.get(pointer);
            pointer = pre[pointer];
        }
        out.println(LIS_V.size());
        out.println("-");
        for (int i : rst) out.println(i);
        out.flush();
        out.close();
    }
}
