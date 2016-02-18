import java.io.*;
import java.util.Formatter;
import java.util.StringTokenizer;

public class UVa12356 {
    public static void main(String[] args) {
        StringTokenizer st = new StringTokenizer(getNextLine());
        soldierNo = Integer.parseInt(st.nextToken());
        int reportNo = Integer.parseInt(st.nextToken());
        while (soldierNo != 0 && reportNo != 0){
            int[] line = buildLine(soldierNo);
            for (int i=0; i<reportNo; i++){
                st = new StringTokenizer(getNextLine());
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                process(left, right);
            }
            write("-\n");
            st = new StringTokenizer(getNextLine());
            soldierNo = Integer.parseInt(st.nextToken());
            reportNo = Integer.parseInt(st.nextToken());
        }
        flushOutput();
    }

    public static int[] buildLine(int soldierNo){
        line[0] = line[soldierNo + 1] = 0;
        for (int i=1; i<=soldierNo; i++){
            line[i] = i;
        }
        return line;
    }

    public static void process(int left, int right){
        String leftResult = "*";
        String rightResult = "*";
        for (int i=  left; i <= right; i++){
            line[i] = 0;
        }

        /* to find the first surviving soldier to the right of R */
        for (int i = right; i <= soldierNo; i++){
            if (line[i] != 0) {
                rightResult = String.valueOf(line[i]);
                break;
            }
        }

        /* to find the first surviving soldier to the left of L */
        for(int i=left; i>0; i--){
            if (line[i] != 0){
                leftResult = String.valueOf(line[i]);
                break;
            }
        }
        write(leftResult + " " + rightResult + "\n");
    }

    public static BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter stdOut = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder(25000);
    public static int[] line = new int[100002];
    public static int soldierNo;

    private static String getNextLine(){
        try{
            return stdIn.readLine();
        }catch (IOException ioe){
            System.out.println("I/O exception");
        }
        return "";
    }

    private static void write(Object w){
        sb.append(w);
        checkSb();
    }

    private static void checkSb(){
        try{
            if (sb.length() >= 20000){
                stdOut.write(sb.toString());
                sb = new StringBuilder(25000);
            }
        }catch (IOException ioe){
            System.out.println("I/O exception");
        }
    }

    private static void flushOutput(){
        try{
            stdOut.write(sb.toString());
            stdOut.flush();
            stdOut.close();
        }catch(IOException ioe){}
    }
}
