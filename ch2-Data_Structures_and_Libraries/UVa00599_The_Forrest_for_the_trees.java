import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Formatter;

public class UVa00599 {

    public static void main(String[] args) throws Throwable {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int caseNo = Integer.parseInt(in.readLine());
        for (int i = 0; i < caseNo; i++) {
            String s = in.readLine();
            ArrayList<int[]> edges = new ArrayList<int[]>();
            while (s.charAt(0) != '*') {
                edges.add(new int[]{s.charAt(1) - 'A', s.charAt(3) - 'A'});
                s = in.readLine();
            }
            s = in.readLine();
            ArrayList<Integer> vertex = new ArrayList<Integer>();
            for (int j = 0; j < s.length(); j = j + 2) {
                vertex.add(s.charAt(j) - 'A');
            }
            UnionFind uf = new UnionFind();
            for (int v : vertex) {
                uf.setSetSize(v);
            }
            for (int[] edge : edges) {
                uf.unionSet(edge[0], edge[1]);
            }
            out.write(new Formatter().format("There are %d tree(s) and %d acorn(s).\n",
                    uf.getNumTree(), uf.getNumOfAcorns()).toString());
        }
        out.flush();
        out.close();
    }


    static class UnionFind {

        private int[] p, rank, setSize;

        public UnionFind() {
            p = new int[26];
            rank = new int[26];
            setSize = new int[26];
            for (int i = 0; i < 26; i++) {
                p[i] = i;
            }
        }

        public int findSet(int i) {
            if (p[i] == i) {
                return i;
            } else {
                int ret = findSet(p[i]);
                p[i] = ret;
                return ret;
            }
        }

        public void setSetSize(int i) {
            setSize[i] = 1;
        }

        public Boolean isSameSet(int i, int j) {
            return findSet(i) == findSet(j);
        }

        public void unionSet(int i, int j) {
            if (!isSameSet(i, j)) {
                int x = findSet(i), y = findSet(j);
                if (rank[x] > rank[y]) {
                    p[y] = x;
                    setSize[x] = setSize[x] + setSize[y];
                } else {
                    p[x] = y;
                    setSize[y] = setSize[y] + setSize[x];
                    if (rank[x] == rank[y]) {
                        rank[y]++;
                    }
                }
            }
        }

        public int getNumTree() {
            int rst = 0;
            for (int i = 0; i < 26; i++) {
                if (setSize[i] > 1 && p[i] == i) rst++;
            }
            return rst;
        }

        public int getNumOfAcorns() {
            int rst = 0;
            for (int i = 0; i < 26; i++) {
                if (setSize[i] == 1 && p[i] == i) rst++;
            }
            return rst;
        }
    }
}
