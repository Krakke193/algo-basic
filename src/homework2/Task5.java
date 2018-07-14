package homework2;

import java.util.Arrays;
import java.util.Scanner;

@SuppressWarnings("WeakerAccess")
public class Task5 {

    static long swapCounter = 0;

    public static void main(String... args) {
        Scanner scan = new Scanner(System.in);

        int iterations = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < iterations; i++) {
            scan.nextLine();
            int[] array = Arrays.stream(scan.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            mergeSort(array, 0, array.length - 1);
            System.out.println(swapCounter);
            swapCounter = 0;
        }
    }

    static void mergeSort(int arr[], int lo, int hi) {
        if (lo < hi) {
            int m = (lo + hi) / 2;

            mergeSort(arr, lo, m);
            mergeSort(arr, m + 1, hi);

            merge(arr, lo, m, hi);
        }
    }

    static void merge(int arr[], int lo, int m, int hi) {
        int[] leftArray = new int[m - lo + 1];
        int[] rightArray = new int[hi - m];

        System.arraycopy(arr, lo, leftArray, 0, leftArray.length);
        System.arraycopy(arr, m + 1, rightArray, 0, rightArray.length);

        int i = 0, j = 0;
        int k = lo;
        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] <= rightArray[j]) {
                swapCounter += j;
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }

            k++;
        }

        swapCounter += (leftArray.length - i) * (long) j;

        while (i < leftArray.length) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightArray.length) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
