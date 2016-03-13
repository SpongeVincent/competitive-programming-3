import java.io.*;
import java.util.*;

/* Idea: don't go through the coin type which has been given up to avoid double-counting. And, don't use recurrsion
as it gonna excess the recurrsion depth limit. 
/*

class Main {

    static int[] types = new int[] {1, 5, 10, 25, 50};

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        long[] rst = new long[30001];
        rst[0] = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j < 30001; j++) {
                if (j - types[i] >= 0) rst[j] += rst[j - types[i]];
            }
        }

        while (in.hasNextInt()) {
            int num = in.nextInt();
            if (num > 4) out.printf("There are %d ways to produce %d cents change.\n", rst[num], num);
            else out.printf("There is only 1 way to produce %d cents change.\n", num);
        }
        out.close();

    }
}
