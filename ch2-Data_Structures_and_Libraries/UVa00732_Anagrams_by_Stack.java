import java.io.*;
import java.lang.Throwable;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;

public class UVa00732 {
    static ArrayList<ArrayList> result;
    static int size;
    static char[] target;
    static char[] source;

    public static void main(String[] args) throws Throwable{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder(25000);
        source = in.readLine().toCharArray();
        while (source.length > 0) {
            do {
                target = in.readLine().toCharArray();
            } while (target.length == 0);
            size = target.length == source.length? target.length: 0;
            result = new ArrayList<ArrayList>();
            ArrayList<String> sResult = new ArrayList<String>();
            if (size != 0) find(0, 0, new Stack<Character>(), new ArrayList<String>());
            for (ArrayList<String> a : result) {
                StringBuilder s = new StringBuilder(5000);
                for (String e : a) {
                    s.append(e).append(" ");
                }
                s.deleteCharAt(s.length() - 1).append("\n");
                sResult.add(s.toString());
            }
            Collections.sort(sResult);
            sb.append("[\n");
            for (String s : sResult) {
                sb.append(s);
            }
            sb.append("]\n");
            if (sb.length() > 20000) {
                out.write(sb.toString());
                sb = new StringBuilder(25000);
            }
            try {
                do {
                    source = in.readLine().toCharArray();
                } while (source.length == 0);
            }catch (Exception e){
                break;
            }
        }
        out.write(sb.toString());
        out.flush();
        out.close();
    }

    public static void find(int pt, /* point to the current target */
                            int ps,
                            Stack<Character> s,
                            ArrayList<String> arr) {
        if (s.empty()) {
            if (ps < size){
                Stack<Character> tempS2 = new Stack<Character>();
                ArrayList<String> tempArr2 = (ArrayList<String>) arr.clone();
                tempS2.push(source[ps]);
                tempArr2.add("i");
                find(pt, ps + 1, tempS2, tempArr2);
            }
        } else {
            if (s.peek().equals(target[pt])) {
                if (pt + 1 == size) {
                    ArrayList<String> tempArr = (ArrayList<String>) arr.clone();
                    tempArr.add("o");
                    result.add(tempArr);
                } else if (pt + 1 < size) {
                    Stack<Character> tempS1 = (Stack<Character>) s.clone();
                    ArrayList<String> tempArr1 = (ArrayList<String>) arr.clone();
                    tempS1.pop();
                    tempArr1.add("o");
                    find(pt + 1, ps, tempS1, tempArr1);

                    if (ps < size) {
                        Stack<Character> tempS2 = (Stack<Character>) s.clone();
                        ArrayList<String> tempArr2 = (ArrayList<String>) arr.clone();
                        tempS2.push(source[ps]);
                        tempArr2.add("i");
                        find(pt, ps + 1, tempS1, tempArr2);
                    }
                }
            }
            if (ps < size) {
                Stack<Character> tempS2 = (Stack<Character>) s.clone();
                ArrayList<String> tempArr2 = (ArrayList<String>) arr.clone();
                tempS2.push(source[ps]);
                tempArr2.add("i");
                find(pt, ps + 1, tempS2, tempArr2);
            }
        }
    }
}
