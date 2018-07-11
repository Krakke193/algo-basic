package theory;

import java.util.Comparator;

@SuppressWarnings("WeakerAccess")
public class Utils {
    public void swap(int[] array, int i, int j) {
        if (i == j) return; // no need to swap the same items

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <T> int binSearch(T[] array, T element, Comparator<T> comparator, int lo, int hi) {
        if (lo < hi) {
            int mid = (lo / 2) + (hi / 2);
            int cmp = comparator.compare(array[mid], element);
            if (cmp < 0) return binSearch(array, element, comparator, mid + 1, hi);
            if (cmp > 0) return binSearch(array, element, comparator, lo, mid - 1);
            return mid;
        }
        if (array[lo].equals(element)) {
            return lo;
        }
        return -1;
    }

    public static <T> int binSearch(T[] array, T element, Comparator<T> comparator) {
        return binSearch(array, element, comparator, 0, array.length - 1);
    }
}
