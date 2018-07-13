package homework2;

@SuppressWarnings("WeakerAccess")
public class Task4 {

    public static void main(String... args) {
        int price = 2000;
        int monthly = 510;
        int payments = 4;

        double correctInterest = 9.562054624583681;
        double interest = 9.562054624583681 / 12;

        solution(price, monthly, payments, interest);
    }

    static void solution(int price, int monthPayment, int payments, double interest) {
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

        System.out.println("l is " + l);
        System.out.println("r is " + r);
    }

    static double processPayments(int price, int monthPayment, int payments, double interest) {
        double balance = price;

        for (int i = payments; i > 0; i--) {
            balance = balance + getPercent(balance, interest) - monthPayment;
            System.out.println(balance);
        }

        System.out.println(" * * * * ");
        System.out.println("Balance after: " + balance);
        return balance;
    }

    static double getPercent(double total, double percent) {
        return total / 100 * percent;
    }
}
