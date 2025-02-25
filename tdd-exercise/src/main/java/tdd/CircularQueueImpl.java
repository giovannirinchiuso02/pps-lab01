package tdd;

import java.util.List;
import java.util.LinkedList;

public class CircularQueueImpl implements CircularQueue {

    List<Integer> queue;
    private int capacity;

    public CircularQueueImpl(final int capacity) {
        queue = new LinkedList<>();
        this.capacity = capacity;
    }


    @Override
    public boolean isEmpty() {
        return queue.size() == 0;
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public void push(int i) {
        if (queue.size() == capacity) {
            queue.removeFirst();
        }
        queue.add(i);
    }

    @Override
    public int peek() {
        this.checkIfEmptyThrowException();
        return queue.getFirst();
    }

    @Override
    public int pop() {
        this.checkIfEmptyThrowException();
        return queue.removeFirst();
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public void setCapacity(int newCapacity) {
        this.capacity = newCapacity;
    }

    private void checkIfEmptyThrowException() {
        if(this.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
    }
}
