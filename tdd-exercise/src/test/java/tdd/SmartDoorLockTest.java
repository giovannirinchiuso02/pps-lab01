package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    public static final int INITIAL_PIN = 1000;
    public static final int WRONG_PIN = 1111;
    private SmartDoorLock smartDoorLock = new SmartDoorLockImpl();


    @Test
    void testisLocked() {
        assertFalse(smartDoorLock.isLocked());
    }

    @Test
    void testDefaultPin() {
        assertEquals(0, smartDoorLock.getPin());
    }

    @Test
    void testSetPin() {
        smartDoorLock.setPin(INITIAL_PIN);
        assertEquals(INITIAL_PIN, smartDoorLock.getPin());
    }

    @Test
    void testLockAttemptButPinIsNotSet() {
        assertThrows(IllegalStateException.class, () -> smartDoorLock.lock());
    }

    @Test
    void testCanUnlock() {
        smartDoorLock.setPin(INITIAL_PIN);
        smartDoorLock.lock();
        smartDoorLock.unlock(INITIAL_PIN);
        assertFalse(smartDoorLock.isLocked());
    }

    @Test
    void testAttemptedUnlockWrongPin() {
        assertThrows(IllegalArgumentException.class, () -> smartDoorLock.unlock(WRONG_PIN));
    }

    @Test
    void testCanLock() {
        smartDoorLock.setPin(INITIAL_PIN);
        smartDoorLock.lock();
        assertTrue(smartDoorLock.isLocked());
    }

    @Test
    void testIsInitiallyBlocked() {
        assertFalse(smartDoorLock.isBlocked());
    }

    @Test
    void testGetAttempts() {
        smartDoorLock.setPin(INITIAL_PIN);
        smartDoorLock.lock();
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> smartDoorLock.unlock(WRONG_PIN)),
                () -> assertEquals(1, smartDoorLock.getFailedAttempts()));
    }

    @Test
    void testGetMaxAttempts() {
        assertEquals(SmartDoorLockImpl.MAX_ATTEMPTS, smartDoorLock.getMaxAttempts());
    }

    @Test
    void canBlock() {
        smartDoorLock.setPin(INITIAL_PIN);
        smartDoorLock.lock();
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> smartDoorLock.unlock(WRONG_PIN)),
                () -> assertThrows(IllegalArgumentException.class, () -> smartDoorLock.unlock(WRONG_PIN)),
                () -> assertThrows(IllegalStateException.class, () -> smartDoorLock.unlock(WRONG_PIN)),
                () -> assertEquals(SmartDoorLockImpl.MAX_ATTEMPTS, smartDoorLock.getFailedAttempts()),
                () -> assertTrue(smartDoorLock.isBlocked()),
                () -> assertThrows(IllegalStateException.class, () -> smartDoorLock.unlock(INITIAL_PIN))
        );
    }

    @Test
    void testReset() {
        smartDoorLock.setPin(INITIAL_PIN);
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> smartDoorLock.unlock(WRONG_PIN)),
                () -> assertThrows(IllegalArgumentException.class, () -> smartDoorLock.unlock(WRONG_PIN)),
                () -> assertThrows(IllegalStateException.class, () -> smartDoorLock.unlock(WRONG_PIN)),
                () -> assertEquals(SmartDoorLockImpl.MAX_ATTEMPTS, smartDoorLock.getFailedAttempts()),
                () -> assertTrue(smartDoorLock.isBlocked()),
                () -> assertThrows(IllegalStateException.class, () -> smartDoorLock.unlock(INITIAL_PIN))

        );
        smartDoorLock.reset();
        assertAll(
                () -> assertFalse(smartDoorLock.isBlocked()),
                () -> assertFalse(smartDoorLock.isLocked()),
                () -> assertEquals(0, smartDoorLock.getFailedAttempts())
        );
    }



}





