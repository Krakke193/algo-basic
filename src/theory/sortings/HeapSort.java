package theory.sortings;

/**
 * Heap sort implementation
 *
 * @see <a href="https://www.geeksforgeeks.org/heap-sort/">reference</a>
 */
@SuppressWarnings("WeakerAccess")
public class HeapSort extends IntSorting {
    private final int length;

    public HeapSort(int[] initialArray) {
        super(initialArray);
        length = array.length;
    }

    /**
     * Build the heap first. Then pick the largest item into the
     * tail of an array, exclude it from the heap and heapify what
     * left.
     */
    @Override
    public void sort() {
        for (int i = length / 2 - 1; i >= 0; --i) {
            heapify(length, i);
        }

        for (int i = length - 1; i > 0; --i) {
            // swap first and last
            swap(0, i);

            // heapify
            heapify(i, 0);
        }
    }

    /**
     * Start heapify from the first node with children.
     * After swapping a head node with a child node, heapify affected
     * "partial heap", so that the lowest element can find it's way
     * down to the leaf of the tree.
     *
     * @param n    overall length of the heap
     * @param head heap of the heap that will be heapifyied
     */
    private void heapify(int n, int head) {
        int largest = head;
        int left = 2 * head + 1;
        int right = 2 * head + 2;

        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        if (largest != head) {
            swap(largest, head);

            heapify(n, largest);
        }
    }

    public static void main(String... args) {
        int array[] = {3, 5, 1, 9, -10, 8, 7};

        var hs = new HeapSort(array);
        hs.sort();

        System.out.println(hs);
    }
}
