import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UVa10107 {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        ArrayList<Long> arr = new ArrayList<Long>();
        while (in.hasNext()){
            long n = in.nextLong();
            /* At here, you can use either arr.add(n) ,then Collections.sort(arr) or the method as below */
            /* However, the method I used as below is much faster :) */
            add(arr, n);
            if (arr.size() % 2 == 0){
                int i = arr.size() / 2;
                System.out.println(( arr.get(i - 1) + arr.get(i)) / 2);
            }else{
                System.out.println(arr.get(arr.size() / 2));
            }
        }
    }

    public static void add(ArrayList<Long> arr, long n){
        boolean add = false;
        if (arr.size() == 0){
            arr.add(n);
        }else{
            for (int i = 0; i < arr.size(); i++){
                if (n <= arr.get(i)){
                    arr.add(i, n);
                    add = true;
                    break;
                }
            }
            if (!add) {arr.add(n);}
        }
    }
}
