package tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private SmartDoorLock smartDoorLock = new SmartDoorLockImpl();

    @Test
    public void testLock() {
        assertTrue(smartDoorLock.isLocked());
    }


}
