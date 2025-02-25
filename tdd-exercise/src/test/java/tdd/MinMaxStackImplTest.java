package tdd;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {

   MinMaxStack stack = new MinMaxStackImpl();
    List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));


    private void pushList() {
        for(int i = 0; i < this.list.size(); i++) {
            stack.push(list.get(i));
        }
    }

    @Test
    void checkEmptyStack() {
       assertTrue(stack.isEmpty());
   }

   @Test
   void checkEmptyStackSize() {
       assertEquals(0, stack.size());
   }

   @Test
   void canPush() {
        pushList();
        assertAll(
               () -> assertEquals(list.getLast(), stack.peek()),
               () -> assertEquals(list.size(), stack.size())
        );
   }

   @Test
   void canPop() {
        this.pushList();
        assertEquals(list.getLast(), stack.pop());
   }

   @Test
    void canPeekWithEmptyStack() {
       assertThrows(IllegalStateException.class, () -> stack.peek());
   }

   @Test
    void canPopWithEmptyStack() {
       assertThrows(IllegalStateException.class, () -> stack.pop());
   }

   @Test
    void testGetMin() {
       this.pushList();
       assertEquals(list.stream().min(Integer::compare).get(), stack.getMin());
   }

   @Test
    void testGetMax() {
       this.pushList();
       assertEquals(list.stream().max(Integer::compare).get(), stack.getMax());
   }

   @Test
    void testGetMinWithEmptyStack() {
       assertThrows(IllegalStateException.class, () -> stack.getMin());
   }

   @Test
    void testGetMaxWithEmptyStack() {
       assertThrows(IllegalStateException.class, () -> stack.getMax());
   }

   @Test
    void testMultiplePop() {
        this.pushList();
        for(int i = this.list.size() - 1; i >= 0; i--) {
            int index = i;
            assertAll(
                    () -> assertEquals(list.get(index), stack.pop()),
                    () -> assertEquals(index, stack.size())
            );
        }

   }



}