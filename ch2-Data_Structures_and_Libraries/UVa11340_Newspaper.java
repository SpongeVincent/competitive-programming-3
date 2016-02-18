import java.util.HashMap;
import java.util.Scanner;

public class UVa11340 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCases = in.nextInt();
        for(int cases=0; cases<testCases; cases++){
            int charNo = in.nextInt();
            HashMap<Character, Integer> charValueMap = new HashMap();
            for (int i=0; i<charNo; i++){
                char character = in.next().charAt(0);
                int value = in.nextInt();
                charValueMap.put(character, value);
            }
            int lineNo = in.nextInt();
            HashMap<Character, Integer> charCountMap = new HashMap();
            for (int i=0; i<lineNo+1; i++){
                charCountMap = processLine(charCountMap, in.nextLine());
            }
            System.out.printf("%.2f$\n", addValue(charCountMap, charValueMap));
        }
    }

    public static HashMap processLine(HashMap charCountMap, String line){
        for (char e: line.toCharArray()){
            if (charCountMap.containsKey(e)) {
                charCountMap.put(e, (Integer) charCountMap.get(e) + 1);
            }else{
                charCountMap.put(e, 1);
            }
        }
        return charCountMap;
    }

    public static double addValue(HashMap charCountMap, HashMap charValueMap){
        int value = 0;
        for (Object key: charCountMap.keySet()){
            if (charValueMap.containsKey(key)){
                Object amount = charCountMap.get(key);
                value = (Integer) amount * (Integer) charValueMap.get(key) + value;
            }
        }
        return value / 100.0;
    }
}
