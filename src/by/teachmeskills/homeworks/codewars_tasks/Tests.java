package by.teachmeskills.homeworks.codewars_tasks;

import by.teachmeskills.homeworks.codewars_tasks.solutions.Task1;
import org.junit.Test;
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

}
