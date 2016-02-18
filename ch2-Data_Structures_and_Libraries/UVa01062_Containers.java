import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Stack;

public class UVa01062 {
    static ArrayList<Stack> as;
    static int[] s;
    public static void main(String[] args) throws Throwable{
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(25000);
        String str = in.readLine();
        int caseNo = 1;
        while (!str.equals("end")) {
            char[] temp = str.toCharArray();
            s = new int[temp.length];
            for (int i = 0; i < temp.length; i++){
               s[i] = temp[i] - 'A';
            }
            as = new ArrayList<Stack>();
            process(0);
            Formatter f = new Formatter();
            sb.append(f.format("Case %d: %d\n", caseNo, as.size()).toString());
            caseNo++;
            if (sb.length() > 20000) {
                out.write(sb.toString());
                sb = new StringBuilder(25000);
            }
            str = in.readLine();
        }
        out.write(sb.toString());
        out.flush();
        out.close();
    }

    public static void process(int p) {
        boolean stop = false;
        if (p < s.length) {
            if (as.isEmpty()) {
                Stack<Integer> temp = new Stack<Integer>();
                temp.add(s[p]);
                as.add(temp);
                process(p +1);
            } else {
                for (Stack<Integer> e : as) {
                    if (s[p] <= e.peek()) {
                        e.push(s[p]);
                        stop = true;
                        process(p + 1);
                        break;
                    }
                }
                if (!stop) {
                    Stack<Integer> temp = new Stack<Integer>();
                    temp.add(s[p]);
                    as.add(temp);
                    process(p +1);
                }
            }
        }
    }
}
