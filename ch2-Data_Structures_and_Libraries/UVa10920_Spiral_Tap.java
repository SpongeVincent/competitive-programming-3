import java.io.*;
import java.util.StringTokenizer;
import java.util.Formatter;

public class UVa10920 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder(25000);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int size = Integer.parseInt(st.nextToken());
        int pos = Integer.parseInt(st.nextToken());
//        int[][] board = new int[100000][100000];
        while (size != 0 && pos != 0){
            int ceiling = findCeiling(pos);
            int[] cod = findCoordinate(ceiling, size, pos);

            Formatter ft = new Formatter();
            sb.append(ft.format("Line = %d, column = %d.\n", cod[0], cod[1]).toString());
            if (sb.length() > 20000){
                out.write(sb.toString());
                sb = new StringBuilder(25000);
            }

            st = new StringTokenizer(in.readLine());
            size = Integer.parseInt(st.nextToken());
            pos = Integer.parseInt(st.nextToken());
        }
        out.write(sb.toString());
        out.flush();
        out.close();
    }

    public static int findCeiling(int pos){
        int result = 1;
        for (int i = 1; i <= 100000; i+=2){
            if (i * i >= pos){
                result = i;
                break;
            }
        }
        return result;
    }

    public static int[] findCoordinate(int ceiling, int size, int pos){
        int[] cod = new int[]{ceiling, ceiling};
        boolean stop = false;
        long current = ceiling * ceiling;
        int[][] rules = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int line;
        int column;
        for (int i = 0; i < 4; i++){
            line = rules[i][0];
            column = rules[i][1];
            for (int j = 1; j < ceiling; j++){
                if (current == pos){
                    stop = true;
                    break;
                }
                current--;
                cod[0] += line;
                cod[1] += column;
            }
            if(stop) break;
        }
        int dif = (size - ceiling) / 2;
        cod = new int[]{cod[0] + dif, cod[1] + dif};
        return cod;
    }
}
