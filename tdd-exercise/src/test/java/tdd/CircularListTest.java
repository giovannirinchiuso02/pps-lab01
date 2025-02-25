package tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    List<Integer> list = new LinkedList<>();
    CircularQueue queue = new CircularQueueImpl();

    @Test
    void testQueueInitiallyEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test
    void testQueueInitiallySize() {
        assertEquals(0, queue.size());
    }

    @Test
    void canPush() {
        queue.push(1);
        assertAll(
        () -> assertEquals(1, queue.size()),
        () -> assertEquals(1, queue.peek()),
        () -> assertFalse(queue.isEmpty()));
    }

    @Test
    void canPeekWithEmptyList() {
        assertThrows(IllegalStateException.class, () -> queue.peek());
    }

    @Test
    void testMultiplePush() {
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        assertAll(
                () -> assertEquals(4, queue.size()),
                () -> assertEquals(1, queue.peek()),
                () -> assertFalse(queue.isEmpty()));
    }

}
