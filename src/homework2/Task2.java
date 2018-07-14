package homework2;

import java.util.Arrays;
import java.util.Scanner;

@SuppressWarnings("WeakerAccess")
class Task2 {
    public static void main(String... args) {
        Scanner scan = new Scanner(System.in);
        double[] raw = Arrays.stream(scan.nextLine().split(" ")).mapToDouble(Double::parseDouble).toArray();

        double n = raw[0];
        double a = raw[1];

        solution(n, a);
    }

    static void solution(double n, double a) {
        double l = 0;
        double r = 100000000;

        for (int i = 0; i < 1000; i++) {
            double m = (l + r) / 2;
            if (minPosition(m, n - 1, a) >= 0) {
                r = m;
            } else {
                l = m;
            }
        }

        System.out.println(r);
    }

    static double minPosition(double m, double n, double a) {
        double min = Double.MAX_VALUE;

        for (double i = n; i > 0; i--) {
            m = calculateAn(m, i, a);
            min = Double.min(min, m);
        }

        return min;
    }

    static double calculateAn(double aNext, double n, double a) {
        return (1 / n) * a + ((n - 1) * aNext) / n - (n - 1);
    }
}