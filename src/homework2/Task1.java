package homework2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("WeakerAccess")
public class Task1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> result = new ArrayList<>();

        // omit the first
        int iterations = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < iterations; i++) {
            int dollars = Integer.parseInt(scan.nextLine());
            String length = scan.nextLine();
            String rawStr = scan.nextLine();
            int[] array = Arrays.stream(rawStr.split(" ")).mapToInt(Integer::parseInt).toArray();

            result.add(solution(array, dollars));
        }

        scan.close();
        result.forEach(System.out::println);
    }

    static String solution(int[] array, int dollars) {
        // key is a value, value is an index
        List<List<Integer>> tuples = new ArrayList<>();

        // prepare the data
        for (int i = 0; i < array.length; i++) {
            tuples.add(new ArrayList<>(Arrays.asList(array[i], i)));
        }

        // sort the tuples by value
        tuples.sort((o1, o2) -> {
            if (o1.get(0) > o2.get(0)) return 1;
            if (o1.get(0).equals(o2.get(0))) return 0;
            return -1;
        });

        /* TODO: Implement binary search:
            1. for (min -> dollars) // it is always a sum of two ints, cut off all that can't take place in sum
            2. binsearch between cycles' counter and dollars (max)
            3. when found, take index from tuple
         */

        return "";
    }
}