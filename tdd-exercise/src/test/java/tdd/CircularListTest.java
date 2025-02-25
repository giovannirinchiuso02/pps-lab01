package tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    public static final int INITIAL_CAPACITY = 5;
    public static final int NEW_CAPACITY = 10;
    CircularQueue queue = new CircularQueueImpl(INITIAL_CAPACITY);
    List<Integer> longList = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
    List<Integer> shortList = new LinkedList<>(Arrays.asList(1, 2, 3, 4));

    private void pushList(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            queue.push(list.get(i));
        }
    }

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
        this.pushList(shortList);
        assertAll(
        () -> assertEquals(shortList.size(), queue.size()),
        () -> assertEquals(shortList.getFirst(), queue.peek()),
        () -> assertFalse(queue.isEmpty()));
    }

    @Test
    void canPeekWithEmptyList() {
        assertThrows(IllegalStateException.class, () -> queue.peek());
    }

    @Test
    void testMultiplePush() {
       this.pushList(shortList);
        assertAll(
                () -> assertEquals(shortList.size(), queue.size()),
                () -> assertEquals(shortList.getFirst(), queue.peek()),
                () -> assertFalse(queue.isEmpty()));
    }

    @Test
    void canPop() {
        this.pushList(shortList);
        assertAll(
                () -> assertEquals(shortList.getFirst(), queue.pop()),
                () -> assertEquals(shortList.size() - 1, queue.size()));

    }

    @Test
    void canPopWithEmptyList() {
        assertThrows(IllegalStateException.class, () -> queue.pop());
    }

    @Test
    void testMultiplePop() {
        this.pushList(shortList);
        for(int i = 0; i < shortList.size(); i++) {
            assertEquals(shortList.get(i), queue.pop());
        }
    }

    @Test
    void testGetCapacity() {
        assertEquals(INITIAL_CAPACITY, queue.getCapacity());
    }

    @Test
    void testSetCapacity() {
        queue.setCapacity(NEW_CAPACITY);
        assertEquals(NEW_CAPACITY, queue.getCapacity());
    }

    @Test
    void testPushOverCapacity() {
        this.pushList(longList);
        assertAll(
                () -> assertEquals(longList.get(longList.size() - INITIAL_CAPACITY), queue.peek()),
                () -> assertEquals(INITIAL_CAPACITY, queue.size())
        );
    }


}
