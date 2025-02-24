package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {

    private int pin;
    private boolean isLocked;
    private int attempts;



    public SmartDoorLockImpl(final int pin) {
        this.pin = pin;
        this.isLocked = true;
        this.attempts = 0;
    }

    @Override
    public void setPin(int pin) {
        if(isLocked) {
            throw new IllegalStateException("Locked, cannot set pin");
        }
        else {
            this.pin = pin;
        }
    }

    @Override
    public int getPin() {
        return this.pin;
    }


    @Override
    public void unlock(int pin) {
        if (this.pin != pin) {
            this.attempts++;
            throw new IllegalArgumentException("This pin is not the same as the real pin");
        }
        else {
            this.isLocked = false;
            this.attempts = 0;
        }
    }

    @Override
    public void lock() {
        this.isLocked = true;
    }

    @Override
    public boolean isLocked() {
        return this.isLocked;
    }

    @Override
    public boolean isBlocked() {
        return false;
    }

    @Override
    public int getMaxAttempts() {
        return 0;
    }

    @Override
    public int getFailedAttempts() {
        return this.attempts;
    }

    @Override
    public void reset() {

    }
}
