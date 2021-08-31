package kap1;

import static org.junit.jupiter.api.Assertions.*;

class MyFirstSortTest {

    @org.junit.jupiter.api.Test
    void myFirstSort() {
        int[] values = {1, 7, 2, 4, 6, 9};

        int[] truth = {9, 7, 6, 4, 2, 1};
        MyFirstSort.myFirstSort(values);
        assertArrayEquals(truth, values);
    }

    @org.junit.jupiter.api.Test
    void findMax() {
        int[] values = {1, 7, 2, 4, 6, 9};
        int index = MyFirstSort.findMax(values, 0, values.length);
        assertEquals(5, index);
        assertEquals(9, values[index]);
    }
}