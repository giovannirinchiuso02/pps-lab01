package tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    public static final int INITIAL_PIN = 1000;
    public static final int WRONG_PIN = 1111;
    private SmartDoorLock smartDoorLock = new SmartDoorLockImpl(INITIAL_PIN);

    @Test
    void testLock() {
        assertTrue(smartDoorLock.isLocked());
    }

    @Test
    void testSetPin() {
        smartDoorLock.unlock(INITIAL_PIN);
        smartDoorLock.setPin(WRONG_PIN);
        assertEquals(WRONG_PIN, smartDoorLock.getPin());
    }

    @Test
    void testSetPinAttemptButSmartDoorIsLocked() {
        assertThrows(IllegalStateException.class, () -> smartDoorLock.setPin(WRONG_PIN));
    }

    @Test
    void testCanUnlock() {
        smartDoorLock.unlock(INITIAL_PIN);
        assertFalse(smartDoorLock.isLocked());
    }

    @Test
    void testAttemptedUnlockWrongPin() {
        assertThrows(IllegalArgumentException.class, () -> smartDoorLock.unlock(WRONG_PIN));
    }

    @Test
    void testCanLock() {
        smartDoorLock.unlock(INITIAL_PIN);
        smartDoorLock.lock();
        assertTrue(smartDoorLock.isLocked());
    }

    @Test
    void testIsInitiallyBlocked() {
        assertFalse(smartDoorLock.isBlocked());
    }

    @Test
    void testGetAttempts() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> smartDoorLock.unlock(WRONG_PIN)),
                () -> assertEquals(1, smartDoorLock.getFailedAttempts()));
    }




}
