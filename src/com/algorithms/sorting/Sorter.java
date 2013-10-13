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
    public static int[] countingSortV1(int[] unsorted) {
        if (unsorted.length == 0)
            return unsorted;

        int[] counts = new int[unsorted.length];    // counts for each key
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
     * For each unsorted[i], count how many keys are smaller than unsorted[i].
     * Use that info to compute the position of unsorted[i] in the sorted sequence
     * Assumption: Keys are between 0 and maxKey (inclusive)
     *
     * Stable?: Yes
     * Running Time: O(n), when maxKey = O(n)
     *
     * @param unsorted - The unsorted array
     * @param maxKey - Upper-bound for keys
     * @return The sorted array
     */
    public static int[] countingSortV2(int[] unsorted, int maxKey) {
        if (unsorted.length == 0)
            return unsorted;

        int[] counts = new int[maxKey+1];     // counts for each key
        int[] sorted = new int[unsorted.length];

        for (int j = 0; j < unsorted.length; j++)
            counts[unsorted[j]]++;
        // counts[i] now contains the number of keys that are equal to i

        for (int i = 0; i <= maxKey; i++)
            counts[i] += counts[i-1];
        // counts[i] now contains the number of keys that are <= 1

        for (int j = counts.length - 1; j >= 0; j--) {
            sorted[counts[unsorted[j]]-1] = unsorted[j];
            counts[unsorted[j]]--;  // this is to handle ties
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

        for (int i = 0; i < sorted.length; i++)
            for (int j = i + 1; j < sorted.length; j++)
                if (sorted[i] > sorted[j]) {
                    int tmp = sorted[i];
                    sorted[i] = sorted[j];
                    sorted[j] = tmp;
                }
        return sorted;
    }

    /**
     * Find the smallest unsorted[i], then the next smallest, etc.
     *
     * Stable?: No
     * Running Time: O(n^2)
     *
     * @param unsorted - The unsorted array
     * @return The sorted array
     */
    public static int[] selectionSortV2(int[] unsorted) {
        if (unsorted.length == 0)
            return unsorted;

        int[] sorted = new int[unsorted.length];
        for (int i = 0; i < unsorted.length; i++)
            sorted[i] = unsorted[i];

        for (int i = 0; i < sorted.length - 1; i++) {
            int min = sorted[i];
            int minIdx = -1;
            // find the lowest element to the right of sorted[i]
            for (int j = i + 1; j < sorted.length; j++) {
                if (min > sorted[j]) {
                    min = sorted[j];
                    minIdx = j;
                }
            }
            // swap sorted[minIdx] with sorted[i]
            if (minIdx != -1) {
                int tmp = sorted[i];
                sorted[i] = sorted[minIdx];
                sorted[minIdx] = tmp;
            }
        }
        return sorted;
    }

    /**
     * Determine the appropriate position for each unsorted[i] to the left of
     * its current position.
     * Similar to sorting a hand of cards
     *
     * Stable?: No
     * Running Time: O(n^2)
     *
     * @param unsorted - The unsorted array
     * @return The sorted array
     */
    public static int[] insertionSort(int[] unsorted) {
        if (unsorted.length == 0)
            return unsorted;

        int[] sorted = new int[unsorted.length];
        for (int i = 0; i < unsorted.length; i++)
            sorted[i] = unsorted[i];

        for (int i = 1; i < unsorted.length; i++)
            for (int j = i; j > 0 && sorted[j] < sorted[j-1]; j--) {
                int tmp = sorted[j];
                sorted[j] = sorted[j-1];
                sorted[j-1] = tmp;
            }
        return sorted;
    }

    /**
     * Go through the keys from left to right, keep exchanging neighboring pairs
     * until no two pairs are out of order
     *
     * Stable?: Yes
     * Running Time: O(n^2)
     *
     * @param unsorted - The unsorted array
     * @return The sorted array
     */
    public static int[] bubbleSort(int[] unsorted) {
        if (unsorted.length == 0)
            return unsorted;

        int[] sorted = new int[unsorted.length];
        for (int i = 0; i < unsorted.length; i++)
            sorted[i] = unsorted[i];

        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < sorted.length - 1; i++) {
                int j = i + 1;
                if (sorted[i] > sorted[j]) {
                    int tmp = sorted[i];
                    sorted[i] = sorted[j];
                    sorted[j] = tmp;
                    swapped = true;
                }
            }
        } while (swapped);
        return sorted;
    }

    /**
     * Sort the array by iteratively sorting based on one place-value,
     * starting from the least significant digit
     *
     * Stable?: Yes
     * Running Time: O(n)
     *
     * @param unsorted - The unsorted array
     * @param numDigits - The highest number of digits in the array
     * @return
     */
    public static int[] radixSort(int[] unsorted, int numDigits) {
        if (unsorted.length == 0)
            return unsorted;

        int[] sorted = new int[unsorted.length];
        for (int i = 0; i < unsorted.length; i++)
            sorted[i] = unsorted[i];

        // start from the least significant digit
        for (int d = 0; d < numDigits; d++)
            sorted = stableSortOnDigits(sorted, d);
        return sorted;
    }


    /**
     *
     * @param left
     * @param right
     * @return
     */
    public static int[] mergeV1(int[] left, int[] right) {
        int[] mergedArr = new int[left.length + right.length];
        int leftIdx = 0;
        int rightIdx = 0;
        int mergedIdx = 0;

        // merge for as long as there are elements in both arrays
        while ((leftIdx < left.length) && (rightIdx < right.length)) {
            if (left[leftIdx] < right[rightIdx])
                mergedArr[mergedIdx++] = left[leftIdx++];
            else
                mergedArr[mergedIdx++] = right[rightIdx++];
        }

        // copy the remaining elements to the result
        if (leftIdx < left.length)
            System.arraycopy(left, leftIdx, mergedArr, mergedIdx, left.length - leftIdx);
        else if (rightIdx < right.length)
            System.arraycopy(right, rightIdx, mergedArr, mergedIdx, right.length - rightIdx);
        return mergedArr;
    }

    /**
     *
     * @param left
     * @param right
     * @return
     */
    public static int[] mergeV2(int[] left, int[] right) {
        int[] mergedArr = new int[left.length + right.length];
        int leftIdx = 0;
        int rightIdx = 0;
        int mergedIdx = 0;

        // merge for as long as there are elements in both arrays
        while ((leftIdx < left.length) && (rightIdx < right.length)) {
            if (left[leftIdx] <= right[rightIdx])
                mergedArr[mergedIdx++] = left[leftIdx++];
            else
                mergedArr[mergedIdx++] = right[rightIdx++];
        }

        // copy the remaining elements to the result
        if (leftIdx < left.length)
            System.arraycopy(left, leftIdx, mergedArr, mergedIdx, left.length - leftIdx);
        else if (rightIdx < right.length)
            System.arraycopy(right, rightIdx, mergedArr, mergedIdx, right.length - rightIdx);
        return mergedArr;
    }

    /**
     *
     */
    public static int[] mergeSort(int[] unsorted, boolean stable) {
        if (unsorted.length <= 1)
            return unsorted;

        // split the array
        int medIdx = unsorted.length / 2;
        int[] leftArr = new int[medIdx];
        int[] rightArr = new int[unsorted.length - medIdx];

        System.arraycopy(unsorted, 0, leftArr, 0, leftArr.length);
        System.arraycopy(unsorted, medIdx, rightArr, 0, rightArr.length);

        // sort each part
        leftArr = mergeSort(leftArr, stable);
        rightArr = mergeSort(rightArr, stable);

        // merge the two parts together
        return stable ? mergeV2(leftArr, rightArr) : mergeV1(leftArr, rightArr);
    }
}
