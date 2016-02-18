import java.io.*;

public class UVa00146 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder(25000);
        String s = in.readLine();
        while (!s.equals("#")){
            int[] sArray = new int[s.length()];
            for (int i = 0; i < s.length(); i++){
                sArray[i] = s.charAt(i) - 'a';
            }
            if (nextPermutation(sArray)){
                for (int e: sArray){
                    sb.append((char) (e + 'a'));
                }
                sb.append("\n");
            }else sb.append("No Successor\n");
            s = in.readLine();
            if (sb.length() > 23000){
                out.write(sb.toString());
                sb = new StringBuilder(25000);
            }
        }
        out.write(sb.toString());
        out.flush();
        out.close();
    }

    public static boolean nextPermutation(int[] sArray){
        for (int i = sArray.length - 2; i >= 0; i--){
            if (sArray[i] < sArray[i + 1]){
                for (int j = sArray.length - 1; j >= 0 ;j--){
                    if (sArray[i] < sArray[j]){
                        int temp = sArray[i];
                        sArray[i] = sArray[j];
                        sArray[j] = temp;
                        for (++i, j = sArray.length - 1; i < j; i++, j--){
                            temp = sArray[i];
                            sArray[i] = sArray[j];
                            sArray[j] = temp;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
