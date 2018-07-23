package theory;

@SuppressWarnings({"SameParameterValue", "WeakerAccess"})
public class DynamicProgramming {

    @SuppressWarnings("AssertWithSideEffects")
    public static void main(String... args) {
        assert Grasshopper.canJumpTwoStumps(2) == 2;
        assert Grasshopper.canJumpTwoStumps(3) == 3;
        assert Grasshopper.canJumpTwoStumps(5) == 8;

        assert Grasshopper.canJumpKStumps(4, 4) == 8;
        assert Grasshopper.canJumpKStumps(3, 1) == 1;

        assert Grasshopper.canJumpKStumpsWithFrogs(3, 2, new boolean[]{false, true, true, false}) == 0;
        assert Grasshopper.canJumpKStumpsWithFrogs(3, 2, new boolean[]{false, true, false, false}) == 1;

        assert Grasshopper.canJumpKStumpsAndCollectMaxMoney(5, 2, new int[]{0, -2, -1, 3, -5, 2}) == 4;

        assert backpack(6, new int[]{8, 5, 5}, new int[]{4, 3, 3}) == 10;
        assert backpack(50, new int[]{60, 100, 120}, new int[]{10, 20, 30}) == 220;
        assert backpack(7, new int[]{1, 4, 5, 7}, new int[]{1, 3, 4, 5}) == 9;
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

        /**
         * Same as previous, but on some stamps frogs are sitting, and one cannot jump on them.
         * So there is no way that these stamps can be included in answer, therefore we set 0 there.
         *
         * @param n    stump indexes
         * @param k    grasshopper's jump distance
         * @param frog array representing frogs on stamps
         * @return ways to reach n-th stump and not get eaten
         */
        static int canJumpKStumpsWithFrogs(int n, int k, boolean[] frog) {
            if (n < 0) {
                return 0;
            }
            if (n == 0 || n == 1) {
                return 1;
            }

            int[] a = new int[n + 1];
            a[0] = 1;

            for (int i = 1; i <= n; ++i) {
                if (frog[i]) {
                    a[i] = 0;
                } else {
                    for (int j = 1; j <= Math.min(i, k); ++j) {
                        a[i] += a[i - j];
                    }
                }
            }

            return a[n];
        }

        static int canJumpKStumpsAndCollectMaxMoney(int n, int k, int[] cost) {
            if (n < 0) {
                return 0;
            }
            if (n == 0 || n == 1) {
                return 1;
            }

            int[] a = new int[n + 1];
            a[0] = 0;

            for (int i = 1; i <= n; ++i) {
                // lets assume that optimal way to reach this stamp is from previous
                // if we are wrong -- we will fix them during the loop next
                a[i] = a[i - 1] + cost[i];
                for (int j = 1; j <= Math.min(i, k); ++j) {
                    // if there are more optimal way let's store it
                    if (a[i] < a[i - j] + cost[i]) {
                        a[i] = a[i - j] + cost[i];
                    }
                }
            }

            return a[n];
        }
    }

    /**
     * This is bottom-up DP approach to solve the task. MANY thanks to this guy:
     * https://www.youtube.com/watch?v=8LusJS5-AGo
     *
     * @param capacity capacity of backpack
     * @param values   array of values
     * @param weights  array of weights
     * @return maximum value of backpack that can be picked
     */
    static int backpack(int capacity, int[] values, int[] weights) {
        // we will move through cache "with backward shift" by one element.
        int[][] total = new int[values.length][capacity + 1];

        for (int l = 1; l < values.length; l++) {
            for (int w = 1; w <= capacity; w++) {

                // if current item cannot be placed into backpack due to overweight
                if (w < weights[l]) {
                    // skip current
                    total[l][w] = total[l - 1][w];
                }

                // if can -- choose max between taking it and not taking it
                else {
                    total[l][w] = Math.max(
                            total[l - 1][w - weights[l]] + values[l],
                            total[l - 1][w]
                    );
                }
            }
        }

        return total[values.length - 1][capacity];
    }
}
