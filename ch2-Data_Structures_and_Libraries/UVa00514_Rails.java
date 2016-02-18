import java.io.*;
import java.util.StringTokenizer;
import java.util.Stack;

public class UVa00514 {

    public static Stack<Integer> station;
    public static int size;
    public static StringTokenizer st;
    public static int toFind;

    public static void main(String[] args) throws Throwable {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder(25000);
        st = new StringTokenizer(in.readLine());
        size = Integer.parseInt(st.nextToken());
        while (size != 0) {
            String line = in.readLine();
            while (!line.equals("0")) {
                st = new StringTokenizer(line);
                station = new Stack<Integer>();
                toFind = Integer.parseInt(st.nextToken());
                if (process(1)) {
                    sb.append("Yes\n");
                } else {
                    sb.append("No\n");
                }
                if (sb.length() > 20000) {
                    out.write(st.toString());
                    sb = new StringBuilder(25000);
                }
                line = in.readLine();
            }
            sb.append("\n");
            st = new StringTokenizer(in.readLine());
            size = Integer.parseInt(st.nextToken());

        }
        out.write(sb.toString());
        out.flush();
        out.close();
    }

    public static boolean process(int point) {
        if (station.empty()) {
            station.push(point);
            return process(point);
        } else if (station.peek() == toFind){
            if (!st.hasMoreTokens()) {
                return true;
            } else {
                station.pop();
                toFind = Integer.parseInt(st.nextToken());
                return process(point);
            }
        } else {
            if (point > size){
                return false;
            }
            station.push(point);
            point++;
            return process(point);
        }
    }
}
