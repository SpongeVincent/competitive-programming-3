import java.io.*;
import java.net.Inet4Address;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class UVa00574 {

    static int t, n;
    static ArrayList<ArrayList<Integer>> result;
    static ArrayList<String> resultS;
    static int[] wl;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(System.out)));
        StringTokenizer st = new StringTokenizer(in.readLine());
        t = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        while (n != 0) {
            result = new ArrayList<ArrayList<Integer>>();
            resultS = new ArrayList<String>();
            wl = new int[n];
            for (int i = 0; i < n; i++) wl[i] = Integer.parseInt(st.nextToken());
            ArrayList<Integer> origin = new ArrayList<Integer>();
            origin.add(0);
            bt(origin, 0);
            out.printf("Sums of %d:\n", t);
            for (ArrayList<Integer> a : result) {
                if (a.size() > 1) {
                    for (int i = 1; i < a.size(); i++) {
                        if (i == 1) {
                            out.print(a.get(i));
                        } else {
                            out.printf("+%d", a.get(i));
                        }
                    }
                    out.print("\n");
                }
            }
            if (result.isEmpty()) out.println("NONE");
            st = new StringTokenizer(in.readLine());
            t = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
        }
        out.flush();
        out.close();
    }

    @SuppressWarnings("unchecked")
    public static void bt(ArrayList<Integer> arr, int p) {
        if (p < n && t > arr.get(0)) {
            ArrayList<Integer> tempArr = (ArrayList<Integer>) arr.clone();
            tempArr.set(0, tempArr.get(0) + wl[p]);
            tempArr.add(wl[p]);
            bt(tempArr, p + 1);
            ArrayList<Integer> tempArr2 = (ArrayList<Integer>) arr.clone();
            bt(tempArr2, p + 1);
        } else if (t == arr.get(0) && !resultS.contains(arr.toString())) {
            result.add(arr);
            resultS.add(arr.toString());
        }
    }
}
