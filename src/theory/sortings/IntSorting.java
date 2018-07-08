package theory.sortings;

import java.util.Arrays;
import java.util.Random;

@SuppressWarnings("WeakerAccess")
public abstract class IntSorting {
    protected int[] array;

    public IntSorting(int[] array) {
        this.array = array;
    }

    public abstract void sort();

    @Override
    public String toString() {
        return String.format(
                "%s: %s",
                this.getClass().getSimpleName(),
                Arrays.toString(array)
        );
    }

    protected void swap(int i, int j) {
        if (i == j) return; // no need to swap the same items

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    protected void shuffle() {
        var random = new Random();

        for (int i = array.length - 1; i > 1; i--) {
            int j = random.nextInt(i);
            swap(i, j);
        }
    }
}
