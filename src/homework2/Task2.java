package homework2;

@SuppressWarnings("WeakerAccess")
class Task2 {
    public static void main(String... args) {
        double a = 15;
        double n = 7;

        double lastResult = 9.75;
        for (double i = n; i > 0; i--) {
            lastResult = calculateAn(lastResult, i, 15);
            System.out.println(lastResult);
        }
    }

    static double calculateAn(double aNext, double n, double a) {
        return (1 / n) * a + ((n - 1) * aNext) / n - (n - 1);
    }
}