package homework2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("WeakerAccess")
public class Task3 {

    public static void main(String... args) {
        Scanner scan = new Scanner(System.in);
        List<String> data = new ArrayList<>();

        int iterations = Integer.parseInt(scan.nextLine());

        while (iterations > 0) {
            int i = Integer.parseInt(scan.nextLine());
            data.add(Integer.toString(i));
            iterations--;
        }

        data.stream()
                .map(Task3::solution)
                .reduce((acc, curr) -> acc + " " + curr)
                .ifPresent(System.out::println);
    }

    public static String solution(String index) {
        // 1. describe the sequence
        // 2. is $index element in sequence ?
        // 3. binsearch by hypotetical number of $index element in sequence:
        //    if found -> return 1
        //    not found -> return 0
        int intIndex = Integer.parseInt(index);
        int result = binSearch(intIndex, 0, intIndex / 2);

        if (result == -1) {
            return "0";
        }
        return "1";
    }

    public static int binSearch(long searchElem, int lo, int hi) {
        if (lo < hi) {
            int mid = (lo / 2) + (hi / 2);
            long midElem = sequenceFunc(mid);

            if (midElem > searchElem) {
                // left
                return binSearch(searchElem, lo, mid - 1);
            }
            if (midElem < searchElem) {
                return binSearch(searchElem, mid + 1, hi);
            }

            return mid;
        }
        if (sequenceFunc(lo) == searchElem) {
            return lo;
        }
        return -1;
    }

    public static long sequenceFunc(long x) {
        return ((x * (x+1)) / 2) + 1;
    }
}
