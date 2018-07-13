package homework2;

import java.util.Scanner;

@SuppressWarnings("WeakerAccess")
public class Task4 {

    public static void main(String... args) {
        Scanner scan = new Scanner(System.in);
        String[] raw = scan.nextLine().split(" ");

        double price = Double.parseDouble(raw[0]);
        double monthly = Double.parseDouble(raw[1]);
        int payments = Integer.parseInt(raw[2]);

        solution(price, monthly, payments);
    }

    static void solution(double price, double monthPayment, int payments) {
        double l = 0;
        double r = 100;

        for (int i = 0; i < 100; i++) {
            double m = (l + r) / 2;
            if (processPayments(price, monthPayment, payments, m) >= 0) {
                r = m;
            } else {
                l = m;
            }
        }

        System.out.println(l * 12);
    }

    static double processPayments(double price, double monthPayment, int payments, double interest) {
        double balance = price;

        for (int i = payments; i > 0; i--) {
            balance = balance + getPercent(balance, interest) - monthPayment;
        }

        return balance;
    }

    static double getPercent(double total, double percent) {
        return total / 100 * percent;
    }
}
