package tdd;

import java.util.List;
import java.util.ArrayList;

public class MinMaxStackImpl implements MinMaxStack {

    List<Integer> stack;
    private int max;
    private int min;

    MinMaxStackImpl() {
        stack = new ArrayList();
        this.max = Integer.MIN_VALUE;
        this.min = Integer.MAX_VALUE;
    }

    private void checkIsNotEmpty() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
    }

    @Override
    public void push(final int value) {
        stack.add(value);
        this.checkNewValueIsMax(value);
        this.checkNewValueIsMin(value);
    }

    private void checkNewValueIsMin(final int value) {
        if(value < min) {
            min = value;
        }
    }

    private void checkNewValueIsMax(final int value) {
        if(value > max) {
            max = value;
        }
    }

    @Override
    public int pop() {
        checkIsNotEmpty();
        final int value = stack.removeLast();
        if(value == min) {
            if(this.isEmpty()) {
                min = Integer.MAX_VALUE;
            }
            else {
                this.findNewMin();
            }
        }
        if(value == max) {
            if(this.isEmpty()) {
                max = Integer.MIN_VALUE;
            }
            else {
                this.findNewMax();
            }
        }
        return value;
    }

    @Override
    public int peek() {
        checkIsNotEmpty();
        return stack.getLast();
    }

    @Override
    public int getMin() {
        checkIsNotEmpty();
        return this.min;
    }

    @Override
    public int getMax() {
        checkIsNotEmpty();
        return this.max;
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

    private void findNewMin() {
        this.checkIsNotEmpty();
        this.min = stack.stream().min(Integer::compare).get();
    }

    private void findNewMax() {
        this.checkIsNotEmpty();
        this.max = stack.stream().max(Integer::compare).get();
    }

}
