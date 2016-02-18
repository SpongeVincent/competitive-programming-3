import java.io.*;
import java.util.*;

public class UVa10901 {
    static ArrayDeque<int[]> left;
    static ArrayDeque<int[]> right;
    static ArrayList<int[]> result;
    static ArrayDeque<int[]> wl;
    static int n;
    static int t;
    static int m;
    static int cTime;

    public static void main(String[] args) throws Throwable {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder(25000);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int caseNo = Integer.parseInt(st.nextToken());
        for (int i = 0; i < caseNo; i++) {
            st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            left = new ArrayDeque<int[]>();
            right = new ArrayDeque<int[]>();
            wl = new ArrayDeque<int[]>();
            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(in.readLine());
                int time = Integer.parseInt(st.nextToken());
                int side = st.nextToken().equals("left") ? 0 : 1;
                wl.add(new int[] {time, side, j});
            }
            result = new ArrayList<int[]>();
            cTime = 0;
            process(left, right);
            Collections.sort(result, new Comparator<int[]>() {
                @Override
                public int compare(int[] car1, int[] car2) {
                    return car1[0] - car2[0];
                }
            });
            for (int[] e : result) {
                sb.append(e[1]);
                sb.append("\n");
            }
            if (i < caseNo - 1) sb.append("\n");
            if (sb.length() > 21000) {
                out.write(sb.toString());
                sb = new StringBuilder(25000);
            }
        }
        out.write(sb.toString());
        out.flush();
        out.close();
    }

    static void process(ArrayDeque<int[]> cSide,
                        ArrayDeque<int[]> oSide) {
        if (!cSide.isEmpty() || !oSide.isEmpty()) {
           if (cSide.isEmpty()) {
               addTime(t);
               process(oSide, cSide);
           } else {
               int load = 0;
               while (load < n && !cSide.isEmpty()) {
                   load++;
                   int[] temp = cSide.poll();
                   result.add(new int[] {temp[1], cTime + t});
               }
               addTime(t);
               process(oSide, cSide);
           }
        } else if (!wl.isEmpty()) {
            addTime(wl.peek()[0] - cTime);
            process(cSide, oSide);
        }
    }

    static void addTime(int increment) {
        cTime = cTime + increment;
        while (!wl.isEmpty() && wl.peek()[0] <= cTime){
            int[] temp = wl.poll();
            if (temp[1] == 0) {
                left.add(new int[] {temp[0], temp[2]});
            } else {
                right.add(new int[] {temp[0], temp[2]});
            }
        }
    }
}
