package test.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import task.task2.QuickSort;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {
    private void sortArray(int[] arr) {
        if (arr == null) {
            return;
        }

        QuickSort.quickSort(arr, 0, arr.length - 1);
    }

    @Test
    @DisplayName("Test of empty array")
    void testEmptyArray(){
        int[] actual = {};
        int[] expected = {};
        sortArray(actual);
        assertArrayEquals(expected, actual, "The empty array must be empty");
    }

    @Test
    @DisplayName("Test of one element array")
    void testOneElementArray(){
        int[] actual = {12};
        int[] expected = {12};
        sortArray(actual);
        assertArrayEquals(expected, actual, "An array of one element should remain unchanged");
    }

    @Test
    @DisplayName("Test of sorted array")
    void testAlreadySortedArray() {
        int[] actual = {1, 2, 3, 4, 5, 6};
        int[] expected = {1, 2, 3, 4, 5, 6};
        sortArray(actual);
        assertArrayEquals(expected, actual, "The already sorted array should remain unchanged");
    }

    @Test
    @DisplayName("Test of reverse sorted array")
    void testReverseSortedArray() {
        int[] actual = {6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6};
        sortArray(actual);
        assertArrayEquals(expected, actual, "An array sorted in reverse order must be sorted correctly");
    }

    @Test
    @DisplayName("Test of array with duplicates")
    void testArrayWithDuplicates() {
        int[] actual = {5, 1, 4, 1, 5, 9, 2, 6, 5};
        int[] expected = {1, 1, 2, 4, 5, 5, 5, 6, 9};
        sortArray(actual);
        assertArrayEquals(expected, actual, "The array with duplicates must be sorted correctly");
    }

    @Test
    @DisplayName("Test an array with all the same elements")
    void testArrayWithAllEqualElements() {
        int[] actual = {7, 7, 7, 7, 7};
        int[] expected = {7, 7, 7, 7, 7};
        sortArray(actual);
        assertArrayEquals(expected, actual, "An array with identical elements should remain unchanged");
    }

    @Test
    @DisplayName("Test of random array")
    void testRandomArray() {
        int[] actual = {3, 8, 1, 6, 4, 9, 2, 7, 5, 0};
        int[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        sortArray(actual);
        assertArrayEquals(expected, actual, "The random array must be sorted correctly");
    }

    @Test
    @DisplayName("test of array with negative and null elements")
    void testArrayWithNegativesAndZero() {
        int[] actual = {3, -1, 0, 4, -5, 1, 2, -1};
        int[] expected = {-5, -1, -1, 0, 1, 2, 3, 4};
        sortArray(actual);
        assertArrayEquals(expected, actual, "An array with negative numbers and zero must be sorted correctly");
    }

    @Test
    @DisplayName("Test of array with two elements (sorted)")
    void testTwoElementsSorted() {
        int[] actual = {10, 20};
        int[] expected = {10, 20};
        sortArray(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Test of array with two elements (unsorted)")
    void testTwoElementsUnSorted() {
        int[] actual = {20, 10};
        int[] expected = {10, 20};
        sortArray(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Test of big array")
    void testLargeArray() {
        int size = 1000;
        int[] actual = new int[size];
        int[] expected = new int[size];
        java.util.Random rand = new java.util.Random();
        for(int i = 0; i < size; i++) {
            int val = rand.nextInt(size * 2) - size;
            actual[i] = val;
            expected[i] = val;
        }

        Arrays.sort(expected);

        sortArray(actual);

        assertArrayEquals(expected, actual, "A large random array must be sorted correctly");
    }
}