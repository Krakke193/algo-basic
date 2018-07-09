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
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[i] + array[j] == dollars) {
                    return Integer.toString(Math.min(i, j) + 1) + " " + Integer.toString(Math.max(i, j) + 1);
                }
            }
        }

        return "";
    }
}