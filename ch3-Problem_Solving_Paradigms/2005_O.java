import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MainO {

    static int roomNum;
    static int[] gps;
    static HashMap<Integer, Integer> onHold;
    static ArrayList<ArrayList> ports;
    static boolean stop;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(System.out)));
        roomNum = Integer.parseInt(in.readLine());
        while (roomNum != 0) {
            ports = new ArrayList<ArrayList>();
            gps = new int[roomNum];
            for (int i = 0; i < roomNum; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                String type = st.nextToken();
                if (type.equals("L")) {
                    gps[i] = Integer.parseInt(st.nextToken());
                } else if (type.equals("T")) {
                    gps[i] = - Integer.parseInt(st.nextToken());
                } else gps[i] = Integer.parseInt(st.nextToken());

                ArrayList<Integer> temp = new ArrayList<Integer>();
                while (st.hasMoreTokens()) {
                    int door = Integer.parseInt(st.nextToken());
                    if (door != 0) temp.add(door);
                }
                ports.add(temp);
            }
            stop = false;
            onHold = new HashMap<Integer, Integer>();
            run(0, 0);
            String rst = stop ? "Yes" : "No";
            out.println(rst);
            roomNum = Integer.parseInt(in.readLine());
        }
        out.flush();
        out.close();
    }

    static void run(int gp, int room) {
        if (room < roomNum && gp + gps[room] >= 0 && !stop) {
            if (!onHold.containsKey(room)) {
                if (gps[room] > 0 && gp < gps[room]) {
                    gp = gps[room];
                } else if (gps[room] < 0) {
                    gp += gps[room];
                }
                stop = (room == roomNum - 1);
                onHold.put(room, -1);
                ArrayList<Integer> temp = ports.get(room);
                for (int i : temp) {
                    run(gp, i - 1);
                }
            } else if (onHold.get(room) == -1) {
                onHold.put(room, gp);
            }
        }
    }
}
