import java.io.*;
import java.util.LinkedList;

public class UVa11988 {

    public static void main(String[] args) throws Throwable{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder(50000);
        char[] sArray = in.readLine().toCharArray();
        while (sArray.length != 0) {
            LinkedList<Character> ll = toLinkedList(sArray);
            for (Character e : ll) {
                sb.append(e);
            }
            sb.append("\n");
            if (sb.length() > 40000) {
                out.write(sb.toString());
                sb = new StringBuilder(50000);
            }
            try{
                sArray = in.readLine().toCharArray();
            } catch (Exception e) {
                break;
            }

        }
        out.write(sb.toString());
        out.flush();
        out.close();
    }

    public static LinkedList<Character> toLinkedList(char[] sArray){
        LinkedList<Character> ll = new LinkedList<Character>();
        int point = 0;
        for (char e: sArray) {
            if (e == '[') {
                point = 0;
            } else if (e == ']') {
                point = ll.size();
            } else {
                ll.add(point, e);
                point++;
            }
        }
        return ll;
    }
}
