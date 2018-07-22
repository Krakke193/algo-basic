package theory;

public class DynamicProgramming {

    public static void main(String... args) {
        assert Grasshopper.canJumpTwoStumps(2) == 2;
        assert Grasshopper.canJumpTwoStumps(3) == 3;
        assert Grasshopper.canJumpTwoStumps(5) == 8;

        assert Grasshopper.canJumpKStumps(4, 4) == 8;
        assert Grasshopper.canJumpKStumps(3, 1) == 1;
    }

    private static class Grasshopper {

        /**
         * Grasshopper can jump either one or two stumps forward. How many ways there are
         * to reach n-th stump
         * <p>
         * 1. what is an answer?
         * ways to reach the n-th stump
         * 2. recurrent formula?
         * a[i] = a[i-1] + a[i-2]
         * 3. base conditions
         * a[0] = 1 // it is one way to reach starting point -- doing nothing
         * a[1] = 1 // it is one way to reach first stump
         *
         * @param n stump indexes
         * @return ways to reach the n-th stump
         */
        private static int canJumpTwoStumps(int n) {
            if (n < 0) {
                return 0;
            }
            if (n == 0 || n == 1) {
                return 1;
            }

            int[] a = new int[n + 1];
            a[0] = 1;
            a[1] = 1;

            for (int i = 2; i <= n; ++i) {
                a[i] = a[i - 1] + a[i - 2];
            }

            return a[n];
        }

        /**
         * Now on each iteration we will sum the ways to reaching into any reachable stump!
         * Because grasshopper's jump range is dynamical we must take the lowest between
         * it's position (i) and it's jump distance (k) so that it couldn't "jump from negative
         * stump".
         *
         * @param n stump indexes
         * @param k grasshopper's jump distance
         * @return ways to reach n-th stump
         */
        private static int canJumpKStumps(int n, int k) {
            if (n < 0) {
                return 0;
            }
            if (n == 0 || n == 1) {
                return 1;
            }

            int[] a = new int[n + 1];
            a[0] = 1;

            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= Math.min(i, k); ++j) {
                    a[i] += a[i - j];
                }
            }

            return a[n];
        }
    }
}
