import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.lang.Math;
import java.util.TreeSet;

public class UVa10038 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()){
            String[] sequence = in.nextLine().split(" ");
            TreeSet diffSet = new TreeSet();
            Boolean isJolly = true;
            int range = Integer.parseInt(sequence[0]);
            for (int i=2; i<sequence.length; i++){
                int diff = Math.abs(Integer.valueOf(sequence[i]) -
                        Integer.valueOf(sequence[i-1]));
                if(diff > range - 1 || diff < 1){
                    isJolly = false;
                    break;
                }else{
                    diffSet.add(diff);
                }
            }
            if(diffSet.size() != range - 1){
                isJolly = false;
            }
            if(isJolly){
                System.out.println("Jolly");
            }else {
                System.out.println("Not jolly");
            }
        }
    }
}
