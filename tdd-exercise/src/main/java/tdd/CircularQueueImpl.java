package tdd;

import java.util.List;
import java.util.LinkedList;

public class CircularQueueImpl implements CircularQueue {

    List<Integer> queue;

    public CircularQueueImpl() {
        queue = new LinkedList<>();
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
        queue.add(i);
    }

    @Override
    public int peek() {
        if(this.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue.getFirst();
    }
}
