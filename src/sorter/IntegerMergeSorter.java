package sorter;

import java.util.Arrays;

public class IntegerMergeSorter {

    /**
     * The method allocates memory and gets ready for sorting.
     * @param arrayOfIntsFromFiles Int array for merge sorting.
     * @param sortMode Boolean variable for detecting ascending or descending sorting mode is used.
     * @return Sorted array of int type by merge sorting.
     * */
    public static int[] mergeSort(int[] arrayOfIntsFromFiles, boolean sortMode) {
        int[] buffer1 = Arrays.copyOf(arrayOfIntsFromFiles, arrayOfIntsFromFiles.length);
        int[] buffer2 = new int[arrayOfIntsFromFiles.length];
        return mergeSortForInts(buffer1, buffer2,
                0, arrayOfIntsFromFiles.length, sortMode);
    }

    /**
     * The method calculates and sorts arrays by merge sorting.
     * @param sortArray Array for sorting.
     * @param buffer Buffer with the size of sortArray.
     * @param startIndex Start index for sorting.
     * @param endIndex End index for sorting.
     * @param sortMode Ascending or descending sort order.
     * @return Array sorted by merge sorting with the sortMode order.
     * */
    public static int[] mergeSortForInts(int[] sortArray, int[] buffer,
                                         int startIndex, int endIndex, boolean sortMode) {
        if (startIndex >= endIndex - 1)
            return sortArray;

        // the sorting block, after it array is sorted
        int middle = startIndex + (endIndex - startIndex) / 2;
        int[] sorted1 = mergeSortForInts(sortArray, buffer, startIndex, middle, sortMode);
        int[] sorted2 = mergeSortForInts(sortArray, buffer, middle, endIndex, sortMode);

        // merging block, there is correct array with all data after all
        int index1 = startIndex;
        int index2 = middle;
        int destIndex = startIndex;
        int[] sortedArray = sorted1 == sortArray ? buffer : sortArray;

        if (sortMode) {
            while (index1 < middle && index2 < endIndex)
                sortedArray[destIndex++] = sorted1[index1] < sorted2[index2] ?
                        sorted1[index1++] : sorted2[index2++];
        } else {
            while (index1 < middle && index2 < endIndex)
                sortedArray[destIndex++] = sorted1[index1] > sorted2[index2] ?
                        sorted1[index1++] : sorted2[index2++];
        }

        while (index1 < middle)
            sortedArray[destIndex++] = sorted1[index1++];

        while (index2 < endIndex)
            sortedArray[destIndex++] = sorted2[index2++];

        return sortedArray;
    }
}
