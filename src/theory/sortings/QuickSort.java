package theory.sortings;

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

    private void _sort(int wallIdx, int pivotIdx) {
        if (pivotIdx < wallIdx) return;

        for (int i = wallIdx; i < pivotIdx; i++) {
            if (array[i] < array[pivotIdx]) {
                swap(wallIdx, i);
                wallIdx++;
            }
        }

        swap(wallIdx, pivotIdx);

        _sort(0, wallIdx - 1); // wallIdx becomes pivot for the left part of the array
        _sort(wallIdx + 1, pivotIdx); // right part of the array
    }

    public static void main(String... args) {
        int array[] = {6, 5, 1, 3, 8, -10, 5, 4, 7, 9, 2, 5, -5};

        var qs = new QuickSort(array);
        qs.sort();

        System.out.println(qs);
    }
}
