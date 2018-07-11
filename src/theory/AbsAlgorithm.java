package theory;

import java.util.Comparator;

public abstract class AbsAlgorithm {
    protected void swap(int[] array, int i, int j) {
        if (i == j) return; // no need to swap the same items

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    protected <T> int binSearch(T[] array, T element, Comparator<T> comparator, int hi, int lo) {
        if (lo < hi) {
            int mid = (lo / 2) + (hi / 2);
            int cmp = comparator.compare(array[mid], element);
            if (cmp < 0) return binSearch(array, element, comparator, lo, mid - 1);
            if (cmp > 0) return binSearch(array, element, comparator, mid + 1, hi);
            return mid;
        } // if
        return -1;
    }

    protected <T> int binSearch(T[] array, T element, Comparator<T> comparator) {
        return binSearch(array, element, comparator, array.length, 0);
    }
}
