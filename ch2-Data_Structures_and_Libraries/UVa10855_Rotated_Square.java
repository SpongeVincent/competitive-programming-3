import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.util.Formatter;
import java.util.StringTokenizer;

public class UVa10855 {

    public static void main(String[] args) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(25000);
        StringTokenizer st = new StringTokenizer(in.readLine());
        Formatter formatter = new Formatter();
        int largeSize = Integer.parseInt(st.nextToken());
        int smallSize = Integer.parseInt(st.nextToken());
        while (largeSize != 0 && smallSize != 0){
            char[][] largeSquare = new char[largeSize][largeSize];
            for (int i = 0; i < largeSize; i++){
                largeSquare[i] = in.readLine().toCharArray();
            }
            char[][] smallSquare = new char[smallSize][smallSize];
            for (int i = 0; i < smallSize; i++){
                smallSquare[i] = in.readLine().toCharArray();
            }
            int[] res = new int[4];
            for (int i = 0; i < 4; i++){
                res[i] = check(largeSquare, smallSquare);
                smallSquare = rotate(smallSquare);
            }
            formatter = new Formatter();
            formatter.format("%d %d %d %d\n", res[0], res[1], res[2], res[3]);
            sb.append(formatter.toString());
            if (sb.length() > 20000){
                out.write(sb.toString());
                sb = new StringBuilder(25000);
            }
            st = new StringTokenizer(in.readLine());
            largeSize = Integer.parseInt(st.nextToken());
            smallSize = Integer.parseInt(st.nextToken());
        }
        out.write(sb.toString());
        out.flush();
        out.close();
    }

    public static char[][] rotate(char[][] origin){
        int size = origin.length;
        char[][] rotatedSquare = new char[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                rotatedSquare[i][j] = origin[size - j - 1][i];
            }
        }
        return rotatedSquare;
    }

    public static int check(char[][] ls, char[][] ss){
        boolean same = true;
        int result = 0;
        int lSize = ls.length;
        int sSize = ss.length;
        for (int h = 0; h <= lSize - sSize; h++){
            for (int v = 0; v <= lSize - sSize; v++) {
                same = true;
                for (int j = 0; j < sSize; j++) {
                    for (int k = 0; k < sSize; k++) {
                        if (ss[j][k] != ls[j + h][k + v]) {
                            same = false;
                            break;
                        }
                    }

                }
                if (same) result++;
            }
        }
        return result;
    }
}
