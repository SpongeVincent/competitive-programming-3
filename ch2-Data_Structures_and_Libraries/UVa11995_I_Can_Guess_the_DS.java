import java.io.*;
import java.util.*;

public class UVa11995 {

    static boolean isStack;
    static boolean isQueue;
    static boolean isPq;
    static BufferedWriter out;
    static BufferedReader in;
    static StringTokenizer st;

    public static void main(String[] args) throws Throwable{
        out = new BufferedWriter(new OutputStreamWriter(System.out));
        in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(25000);
        st = new StringTokenizer(in.readLine());
        int caseNo = Integer.parseInt(st.nextToken());
        while (caseNo > 0) {
            isStack = true;
            isQueue = true;
            isPq = true;
            int result = process(caseNo);
            if (result == 0) {
                sb.append("impossible\n");
            } else if (result > 1) {
                sb.append("not sure\n");
            } else if (isQueue) {
                sb.append("queue\n");
            } else if (isStack) {
                sb.append("stack\n");
            } else {
                sb.append("priority queue\n");
            }
            if (sb.length() > 20000) {
                out.write(sb.toString());
                sb = new StringBuilder(25000);
            }
            try {
                st = new StringTokenizer(in.readLine());
                caseNo = Integer.parseInt(st.nextToken());
            } catch (Exception e) {
                break;
            }
        }
        out.write(sb.toString());
        out.flush();
        out.close();
    }

    static int process(int caseNo) throws IOException{
        Stack<Integer> s = new Stack<Integer>();
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(1, Collections.reverseOrder());
        int result = 0;
        for (int i = 0; i < caseNo; i++) {
            st = new StringTokenizer(in.readLine());
            int type = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            if (!isPq && !isStack && !isQueue) {

            } else if (type == 1) {
                if (isPq) pq.add(value);
                if (isStack) s.add(value);
                if (isQueue) q.add(value);
            } else if(type ==2) {
                if (pq.isEmpty()) {
                    isPq = false;
                }
                else if (isPq) isPq = value == pq.poll();
                if (s.isEmpty()) {
                    isStack = false;
                }
                else if (isStack) isStack = value == s.pop();
                if (q.isEmpty()) {
                    isQueue = false;
                }
                else if (isQueue) isQueue = value == q.poll();
            }
        }
        if (isPq) result++;
        if (isQueue) result++;
        if (isStack) result++;
        return result;
    }

}
