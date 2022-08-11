package sorter;

import java.util.Arrays;

public class StringMergeSorter {
    /**
     * The method allocates memory and gets ready for sorting.
     * @param arrayOfStringsFromFiles String array for merge sorting.
     * @param sortMode Boolean variable for detecting ascending or descending sorting mode.
     * @return Sorted array of int type by merge sorting.
     * */
    public static String[] mergeSort(String[] arrayOfStringsFromFiles, boolean sortMode) {
        String[] buffer1 = Arrays.copyOf(arrayOfStringsFromFiles, arrayOfStringsFromFiles.length);
        String[] buffer2 = new String[arrayOfStringsFromFiles.length];
        return mergeSortInner(buffer1, buffer2,
                0, arrayOfStringsFromFiles.length, sortMode);
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
    public static String[] mergeSortInner(String[] sortArray, String[] buffer,
                                          int startIndex, int endIndex,
                                          boolean sortMode) {
        if (startIndex >= endIndex - 1)
            return sortArray;

        // the sorting block, after it array is sorted
        int middle = startIndex + (endIndex - startIndex) / 2;
        String[] sorted1 = mergeSortInner(sortArray, buffer, startIndex, middle, sortMode);
        String[] sorted2 = mergeSortInner(sortArray, buffer, middle, endIndex, sortMode);

        // merging block, there is correct array with all data after all
        int index1 = startIndex;
        int index2 = middle;
        int destIndex = startIndex;
        String[] result = sorted1 == sortArray ? buffer : sortArray;

        if (sortMode) {
            while (index1 < middle && index2 < endIndex)
                result[destIndex++] = sorted1[index1].compareTo(sorted2[index2]) < 1 ?
                        sorted1[index1++] : sorted2[index2++];
        } else {
            while (index1 < middle && index2 < endIndex)
                result[destIndex++] = sorted1[index1].compareTo(sorted2[index2]) > 0 ?
                        sorted1[index1++] : sorted2[index2++];
        }
        while (index1 < middle)
            result[destIndex++] = sorted1[index1++];

        while (index2 < endIndex)
            result[destIndex++] = sorted2[index2++];

        return result;
    }
}
