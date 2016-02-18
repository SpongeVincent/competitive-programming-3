import java.io.*;
import java.util.*;
import java.lang.StringBuilder;

public class UVa10258 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder(25000);

    public static void main(String[] args) throws IOException {
        int caseNo = Integer.parseInt(in.readLine());
        in.readLine();
        for (int k = 0; k < caseNo; k++) {
            HashMap<Integer, int[]> scores = new HashMap<Integer, int[]>();
            HashMap<Integer, HashMap> trails = new HashMap<Integer, HashMap>();
            HashMap<Integer, HashSet> ac = new HashMap<Integer, HashSet>();
            StringTokenizer st = new StringTokenizer(in.readLine());
            while (st.countTokens() == 4) {
                process(scores, trails, ac, st);
                try {
                    st = new StringTokenizer(in.readLine());
                } catch (Exception e) {
                    break;
                }
            }
            sortScores(scores);
            if (k < caseNo - 1) sb.append("\n");
            if (sb.length() > 21000) {
                out.write(sb.toString());
                sb = new StringBuilder(25000);
            }
        }
        out.write(sb.toString());
        out.flush();
        out.close();
    }

    @SuppressWarnings("unchecked")
    public static void process(HashMap<Integer, int[]> scores,
                               HashMap<Integer, HashMap> trails,
                               HashMap<Integer, HashSet> ac,
                               StringTokenizer st){
        int tester = Integer.parseInt(st.nextToken());
        int quizNo = Integer.parseInt(st.nextToken());
        int time = Integer.parseInt(st.nextToken());
        String judge = st.nextToken();
        if (!scores.containsKey(tester)) {
            scores.put(tester, new int[]{0, 0});
            ac.put(tester, new HashSet<Integer>());
        }
        if (judge.equals("I")) {
            if (!trails.containsKey(tester)) {
                trails.put(tester, new HashMap<Integer, Integer>());
            }
            HashMap<Integer, Integer> temp = trails.get(tester);
            temp.put(quizNo, temp.containsKey(quizNo) ? temp.get(quizNo) + 1 : 1);
        } else if (judge.equals("C") && !ac.get(tester).contains(quizNo)) {
            int previous = 0;
            ac.get(tester).add(quizNo);
            if (trails.containsKey(tester)) {
                previous = trails.get(tester).containsKey(quizNo) ?
                        (Integer) trails.get(tester).get(quizNo) * 20 : 0;
            }
            int penalty = time + previous;
            int[] temp = scores.get(tester);
            scores.put(tester, new int[]{++temp[0], temp[1] + penalty}); /*notice*/
        }
    }

    public static void sortScores(HashMap<Integer, int[]> scores){
        int[][] arr = new int[scores.size()][3];
        int i = 0;
        for (Map.Entry<Integer, int[]> entry :scores.entrySet()) {
            int[] value = entry.getValue();
            arr[i][0] = value[0]; /* AC # */
            arr[i][1] = value[1]; /* penalty */
            arr[i][2] = entry.getKey(); /* contestant # */
            i = i + 1;
        }
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] tester1, int[] tester2) {
                if (tester1[0] != tester2[0]) {
                    return tester2[0] - tester1[0];
                } else {
                    if (tester1[1] != tester2[1]) {
                        return tester1[1] - tester2[1];
                    } else {
                        return tester1[2] - tester2[2];
                    }
                }
            }
        });
        for (int[] e: arr) {
            Formatter f = new Formatter();
            sb.append(f.format("%d %d %d\n", e[2], e[0], e[1]).toString());
        }
    }
}
