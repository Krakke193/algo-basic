package homework2;

import theory.Utils;

import java.util.*;

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
            1. for (min -> max)
            2. binsearch between cycles' counter and dollars (max)
            3. when found, take index from tuple
         */
        int i = -1;
        int j = -1;
        while (j == -1) {
            i++;
            j = binSearch(tuples, dollars, tuples.get(i).get(0), i, tuples.size() - 1);
        }

        int loIdx = Math.min(tuples.get(i).get(1), tuples.get(j).get(1));
        int hiIdx = Math.max(tuples.get(i).get(1), tuples.get(j).get(1));

        return ++loIdx + " " + ++hiIdx;
    }

    public static int binSearch(List<List<Integer>> tuples, int sum, int firstSumPart, int lo, int hi) {
        if (lo < hi) {
            int mid = (lo / 2) + (hi / 2);

            if (tuples.get(mid).get(0) + firstSumPart > sum) {
                // left
                return binSearch(tuples, sum, firstSumPart, lo, mid - 1);
            }
            if (tuples.get(mid).get(0) + firstSumPart < sum) {
                return binSearch(tuples, sum, firstSumPart, mid + 1, hi);
            }

            return mid;
        }
        if (tuples.get(lo).get(0) + firstSumPart == sum) {
            return lo;
        }
        return -1;
    }
}