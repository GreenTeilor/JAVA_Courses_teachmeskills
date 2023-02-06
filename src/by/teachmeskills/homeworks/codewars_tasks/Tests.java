package by.teachmeskills.homeworks.codewars_tasks;

import by.teachmeskills.homeworks.codewars_tasks.solutions.*;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Tests {

    @Test
    public void task1Test() {
        assertEquals("no one likes this", Task1.whoLikesIt());
        assertEquals("Peter likes this", Task1.whoLikesIt("Peter"));
        assertEquals("Jacob and Alex like this", Task1.whoLikesIt("Jacob", "Alex"));
        assertEquals("Max, John and Mark like this", Task1.whoLikesIt("Max", "John", "Mark"));
        assertEquals("Alex, Jacob and 2 others like this", Task1.whoLikesIt("Alex", "Jacob", "Mark", "Max"));
        assertEquals("Alex, Jacob and 3 others like this", Task1.whoLikesIt("Alex", "Jacob", "Mark", "Max", "Roma"));
    }

    @Test
    public void task2Test() {
        int[][] test1 = new int[][]{{75},
                {95, 64},
                {17, 47, 82},
                {18, 35, 87, 10},
                {20, 4, 82, 47, 65},
                {19, 1, 23, 75, 3, 34},
                {88, 2, 77, 73, 7, 63, 67},
                {99, 65, 4, 28, 6, 16, 70, 92},
                {41, 41, 26, 56, 83, 40, 80, 70, 33},
                {41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
                {53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
                {70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
                {91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
                {63, 66, 4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
                {4, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 4, 23}
        };
        assertEquals(1074, Task2.longestSlideDown(test1));

        int[][] test2 = new int[][]{{75},
                {95, 64}
        };
        assertEquals(170, Task2.longestSlideDown(test2));

        int[][] test3 = new int[][]{{4}
        };
        assertEquals(4, Task2.longestSlideDown(test3));
    }

    @Test
    public void task3Test() {
        assertEquals("Empty arrays should have a max of 0", 0, Task3.sequence(new int[]{}));
        assertEquals("Example array should have a max of 6", 6, Task3.sequence(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        assertEquals("Array with only negative numbers should have a max of 0", 0, Task3.sequence(new int[]{ -5, -11, -1, -7, -1, -6, -4}));
    }

    @Test
    public void task4Test() {
        long[] test1 = new long[] {55, 89, 1};
        assertArrayEquals(test1, Task4.productFib(4895));

        long[] test2 = new long[] {89, 144, 0};
        assertArrayEquals(test2, Task4.productFib(5895));

        long[] test3 = new long[] {144, 233, 1};
        assertArrayEquals(test3, Task4.productFib(33552));

        long[] test4 = new long[] {3, 5, 0};
        assertArrayEquals(test4, Task4.productFib(14));
    }

    @Test
    public void task5Test() {
        assertEquals("Shall ask for mineral water when no beers are ordered :", "Woda mineralna poprosze", Task5.orderingBeers(0));
        assertEquals("One is like an adjective, so should be written acording to the gender : Jedno", "Jedno piwo poprosze", Task5.orderingBeers(1));
        assertEquals("Dwa piwa poprosze", Task5.orderingBeers(2));
        assertEquals("Trzy piwa poprosze", Task5.orderingBeers(3));
        assertEquals("Dwadziescia osiem piw poprosze", Task5.orderingBeers(28));
        assertEquals("Dwadziescia piw poprosze", Task5.orderingBeers(20));
        assertEquals("Czterdziesci piw poprosze", Task5.orderingBeers(40));
        assertEquals("Dziewiecdziesiat trzy piwa poprosze", Task5.orderingBeers(93));
        assertEquals("Szesnascie piw poprosze", Task5.orderingBeers(16));
        assertEquals("Trzynascie piw poprosze", Task5.orderingBeers(13));
        assertEquals("Dwadziescia jeden piw poprosze", Task5.orderingBeers(21));
        assertEquals("", Task5.orderingBeers(-1));
        assertEquals("", Task5.orderingBeers(100));
    }

}
