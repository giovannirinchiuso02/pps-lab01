package tdd;

import java.util.List;
import java.util.ArrayList;

public class MinMaxStackImpl implements MinMaxStack {

    List<Integer> stack;

    MinMaxStackImpl() {
        stack = new ArrayList();
    }

    @Override
    public void push(int value) {
        stack.add(value);
    }

    @Override
    public int pop() {
        if (!stack.isEmpty()) {
            return stack.removeLast();
        }
        throw new IllegalStateException("Stack is empty");
    }

    @Override
    public int peek() {
        if (!stack.isEmpty()) {
            return stack.getLast();
        }
        throw new IllegalStateException("Stack is empty");

    }

    @Override
    public int getMin() {
        if(!stack.isEmpty()) {
            return stack.stream().min(Integer::compare).get();
        }
        throw new IllegalStateException("Stack is empty");
    }

    @Override
    public int getMax() {
        if(!stack.isEmpty()) {
            return stack.stream().max(Integer::compare).get();
        }
        throw new IllegalStateException("Stack is empty");
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
