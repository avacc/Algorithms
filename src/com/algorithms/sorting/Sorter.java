package com.algorithms.sorting;

import java.util.Comparator;
/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 10/12/13
 * Time: 9:07 PM.
 */
public class Sorter {

    /**
     * For each unsorted[i], count how many keys are smaller than unsorted[i].
     * Use that info to compute the position of unsorted[i] in the sorted sequence
     *
     * Stable?: Yes
     * Running Time: O(n^2)
     *
     * @param unsorted - The unsorted array
     * @return The sorted array
     */
    public static int[] countingSort(int[] unsorted) {
        if (unsorted.length == 0)
            return unsorted;

        int[] counts = new int[unsorted.length];    // counts
        int[] sorted = new int[unsorted.length];    // sorted keys

        // count the number of elements lower than K[i]
        for (int i = 0; i < unsorted.length; i++) {
            for (int j = i + 1; j < unsorted.length; j++) {
                if (unsorted[i] > unsorted[j])
                    counts[i]++;
                else
                    counts[j]++;
            }
        }

        for (int i = 0; i < unsorted.length; i++) {
            sorted[counts[i]] = unsorted[i];
        }
        return sorted;
    }

    /**
     * For each unsorted[i], if you find an entry that appears to the right
     * of unsorted[i], swap unsorted[i] with that entry
     *
     * Stable?: No
     * Running Time: O(n^2)
     *
     * @param unsorted - The unsorted array
     * @return The sorted array
     */
    public static int[] selectionSortV1(int[] unsorted) {
        if (unsorted.length == 0)
            return unsorted;

        int[] sorted = new int[unsorted.length];
        for (int i = 0; i < unsorted.length; i++)
            sorted[i] = unsorted[i];
    }

}
