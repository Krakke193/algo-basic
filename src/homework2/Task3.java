package homework2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("WeakerAccess")
public class Task3 {

    public static void main(String... args) {
        Scanner scan = new Scanner(System.in);
        List<String> results = new ArrayList<>();

        int iterations = Integer.parseInt(scan.nextLine());

        while (iterations > 0) {
            int i = Integer.parseInt(scan.nextLine());

            results.add(Integer.toString(i));

            iterations--;
        }

        results.stream()
                .map(Task3::solution)
                .reduce((acc, curr) -> acc + " " + curr)
                .ifPresent(System.out::println);
    }

    public static String solution(String index) {
        return String.format("Solved(%s)", index);
    }
}
