package theory.sortings;

/**
 * QuickSort which uses randomization in form of shuffling the whole array.
 * https://www.geeksforgeeks.org/quick-sort/
 * https://youtu.be/aQiWF4E8flQ
 */
@SuppressWarnings("WeakerAccess")
public class QuickSort extends Sorting {

    public QuickSort(int[] array) {
        super(array);
    }

    @Override
    public void sort() {
        shuffle();
        int pivotIdx = array.length - 1;
        _sort(0, pivotIdx);
    }

    /**
     * Partition method. It will move all elements smaller than pivot to left of the
     * returned value and all elements that are bigger or equal – to the right.
     *
     * @param wallIdx  begin of the array's segment
     * @param pivotIdx end of the array's segment
     * @return the searched index
     */
    private int partition(int wallIdx, int pivotIdx) {
        for (int i = wallIdx; i < pivotIdx; i++) {
            if (array[i] < array[pivotIdx]) {
                swap(wallIdx, i);
                wallIdx++;
            }
        }

        swap(wallIdx, pivotIdx);

        return wallIdx;
    }

    /**
     * Main method.
     *
     * @param wallIdx  begin of the array's segment that will be sorted during this call
     * @param pivotIdx end of the segment that elements of the segment will be compared with
     */
    private void _sort(int wallIdx, int pivotIdx) {
        if (pivotIdx < wallIdx) return;

        int partitionIdx = partition(wallIdx, pivotIdx);
        _sort(0, partitionIdx - 1); // wallIdx becomes pivot for the left part of the array
        _sort(partitionIdx + 1, pivotIdx); // right part of the array
    }

    public static void main(String... args) {
        int array[] = {6, 5, 1, 3, 8, -10, 5, 4, 7, 9, 2, 5, -5};

        var qs = new QuickSort(array);
        qs.sort();

        System.out.println(qs);
    }
}
