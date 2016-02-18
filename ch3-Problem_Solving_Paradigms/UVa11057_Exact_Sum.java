import java.io.*;
import java.util.*;

public class UVa11057 {

    static int[] prices;
    static ArrayList<int[]> result;
    static int total;
    static HashMap<Integer, Integer> visited;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(System.out)));
        int num = Integer.parseInt(in.readLine().trim());
        while (num > 0) {
            prices = new int[num];
            result = new ArrayList<int[]>();
            visited = new HashMap<Integer, Integer>();
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < num; i++) prices[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(prices);
            total = Integer.parseInt(in.readLine().trim());
            for (int i = 0; i < num; i++) {
                if (!visited.containsKey(prices[i])){
                    int p = prices[i];
                    int p2 = total - p;
                    int temp = Arrays.binarySearch(prices, p2);
                    if (temp >= 0 && temp != i) {
                        result.add(new int[]{Math.abs(p2 - p), p, p2});
                        visited.put(p, 1);
                        visited.put(p2, 1);
                    }
                }
            }
            Collections.sort(result, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o2[0] != o1[0]) {
                        return o1[0] - o2[0];
                    } else {
                        return o1[1] - o2[1];
                    }
                }
            });
            out.printf("Peter should buy books whose prices are %d and %d.\n\n",
                    result.get(0)[1], result.get(0)[2]);
            try {
                in.readLine();
                num = Integer.parseInt(in.readLine().trim());
            } catch (Exception e) {
                break;
            }
        }
        out.flush();
        out.close();
    }
}
