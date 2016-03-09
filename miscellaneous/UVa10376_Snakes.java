import java.io.*;
import java.util.*;

class Main {

    static int n;
    static boolean feasible;
    static double wLowerBound, eLowerBound;
    static double[][] touched;
    static int[][] snakes;
    static int[] radius;
    static boolean[] eTouch, wTouch, nTouch, sTouch, touch, visited;
    static boolean[][] adj;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int caseNum = in.nextInt();
        while (caseNum-- > 0) {
            n = in.nextInt();
            snakes = new int[n][2]; radius = new int[n]; adj = new boolean[n][n];
            eTouch = new boolean[n]; wTouch = new boolean[n]; nTouch = new boolean[n]; sTouch = new boolean[n];
            touch = new boolean[n]; visited = new boolean[n];
            feasible = true;
            for (int i = 0; i < n; i++) {
                snakes[i][0] = in.nextInt();
                snakes[i][1] = in.nextInt();
                radius[i] = in.nextInt();
                eTouch[i] = snakes[i][0] + radius[i] >= 1000;
                wTouch[i] = snakes[i][0] - radius[i] <= 0;
                nTouch[i] = snakes[i][1] + radius[i] >= 1000;
                sTouch[i] = snakes[i][1] - radius[i] <= 0;
                touch[i] = (eTouch[i] || wTouch[i] || nTouch[i] || sTouch[i]);
            }
            wLowerBound = eLowerBound = 1000.00;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    double dis = Math.sqrt(Math.pow(snakes[i][0] - snakes[j][0], 2) +
                            Math.pow(snakes[i][1] - snakes[j][1], 2));
                    adj[i][j] = adj[j][i] = dis <= (radius[i] + radius[j]);

                }
            }
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    bfs(i);
                    if (touched[2][0] < 0 && touched[3][0] < 0) {feasible = false; break;}
                    if (touched[0][1] < 9999 && touched[2][0] < 0) eLowerBound = Math.min(eLowerBound, touched[0][1]);
                    if (touched[1][1] < 9999 && touched[2][0] < 0) wLowerBound = Math.min(wLowerBound, touched[1][1]);
                }
            }
            if (feasible) {
                out.printf("Bill enters at (0.00, %.2f) and leaves at (1000.00, %.2f).\n",
                        wLowerBound, eLowerBound);
            } else {
                out.println("Bill will be bitten.");
            }
            if (caseNum >= 1) out.println();
        }
        out.close();
    }

    static void bfs(int i) {
        touched = new double[][] {{9999, 9999},{9999, 9999},{9999, 9999},{9999, 9999}};
        visited[i] = true;
        ArrayDeque<Integer> next = new ArrayDeque<>();
        next.add(i);
        while (!next.isEmpty()) {
            int curr = next.poll();
            checker(curr);
            for (int j = 0; j < n; j++) {
                if (adj[curr][j] && !visited[j]) {
                    next.add(j);
                    visited[j] = true;
                }
            }
        }
    }

    static void checker(int j) {
        if (eTouch[j]) {
            double temp = snakes[j][1] - Math.sqrt(radius[j] * radius[j] -
                    Math.pow(1000 - snakes[j][0], 2));
            touched[0][1] = temp < touched[0][1] ? temp : touched[0][1];
        }
        if (wTouch[j]) {
            double temp = snakes[j][1] - Math.sqrt(radius[j] * radius[j] -
                    Math.pow(snakes[j][0], 2));
            touched[1][1] = temp < touched[1][1] ? temp : touched[1][1];
        }
        if (nTouch[j]) touched[2][0] = -1;
        if (sTouch[j]) touched[3][0] = -1;
    }
}
