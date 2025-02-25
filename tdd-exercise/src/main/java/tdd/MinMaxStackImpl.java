package tdd;

import java.util.List;
import java.util.ArrayList;

public class MinMaxStackImpl implements MinMaxStack {

    List<Integer> stack;

    MinMaxStackImpl() {
        stack = new ArrayList();
    }

    private void checkIsNotEmpty() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
    }

    @Override
    public void push(int value) {
        stack.add(value);
    }

    @Override
    public int pop() {
        checkIsNotEmpty();
        return stack.removeLast();
    }

    @Override
    public int peek() {
        checkIsNotEmpty();
        return stack.getLast();
    }

    @Override
    public int getMin() {
        checkIsNotEmpty();
        return stack.stream().min(Integer::compare).get();
    }

    @Override
    public int getMax() {
        checkIsNotEmpty();
        return stack.stream().max(Integer::compare).get();
    }

    @Override
    public boolean isEmpty() {
        if(this.size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int size() {
        return stack.size();
    }
}
