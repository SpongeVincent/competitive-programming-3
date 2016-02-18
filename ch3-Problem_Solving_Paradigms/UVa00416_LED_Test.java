import java.io.*;

public class UVa00416 {

    static String[] stdLED = new String[]{"YYYYNYY", "YYYYYYY", "YYYNNNN", "YNYYYYY",
            "YNYYNYY", "NYYNNYY", "YYYYNNY", "YYNYYNY", "NYYNNNN", "YYYYYYN"};
    static String[] testLED;
    static boolean result;
    static boolean[] badSegments;
    static int caseNo;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out =  new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                System.out)));
        String line;
            while ((line = in.readLine()) != null && line.charAt(0) != '0') {
                caseNo = Integer.valueOf(line.trim());
                result = false;
                testLED = new String[caseNo];
                for (int i = 0; i < caseNo; i++) testLED[i] = in.readLine().trim();
                for (int stdInd = 0; stdInd < 11 - caseNo && !result; stdInd++) {
                    badSegments = new boolean[7];
                    process(stdInd, 0);
                }
                out.println(result ? "MATCH" : "MISMATCH");
            }
        out.flush();
        out.close();
    }

    public static void process(int stdInd, int testInd) {
        if (stdInd < 10 && testInd < caseNo) {
            boolean isMatch = true;
            for (int i = 0; i < 7 && isMatch; i++) {
                char std = stdLED[stdInd].charAt(i), test = testLED[testInd].charAt(i);
                if (badSegments[i] && test == 'Y') {
                    isMatch = false;
                    continue;
                }
                if (std == 'Y' && test == 'N') {
                    badSegments[i] = true;
                } else if (std != test) isMatch = false;
            }
            if (isMatch && testInd == caseNo - 1) {
                result = true;
            } else if (isMatch) {
                process(stdInd + 1, testInd + 1);
            }
        }
    }
}
