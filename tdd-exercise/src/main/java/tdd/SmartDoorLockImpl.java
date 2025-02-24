package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {

    private int pin;
    private boolean isLocked;
    private boolean isBlocked;
    private boolean isPinSet;
    private int attempts;
    public static final int MAX_ATTEMPTS = 3;




    public SmartDoorLockImpl() {
        this.reset();
    }

    @Override
    public void setPin(int pin) {
        if(isLocked) {
            throw new IllegalStateException("Locked, cannot set pin");
        }
        else {
            this.pin = pin;
            this.isPinSet = true;
        }
    }

    @Override
    public int getPin() {
        return this.pin;
    }


    @Override
    public void unlock(final int pin) {
        if (isBlocked()) {
            throw new IllegalStateException("Blocked, cannot unlock");
        }

        if (this.pin == pin) {
            unlockSuccess();
            return;
        }

        handleFailedAttempt();
    }

    private void unlockSuccess() {
        this.isLocked = false;
        this.attempts = 0;
    }

    private void handleFailedAttempt() {
        this.attempts++;
        if (this.attempts >= MAX_ATTEMPTS) {
            this.isBlocked = true;
            throw new IllegalStateException("Blocked, cannot unlock");
        }
        throw new IllegalArgumentException("Invalid pin");
    }



    @Override
    public void lock() {
        if(!this.isPinSet) {
            throw new IllegalStateException("Before lock, set a pin");
        }
        else {
            this.isLocked = true;
        }
    }

    @Override
    public boolean isLocked() {
        return this.isLocked;
    }

    @Override
    public boolean isBlocked() {
        return this.isBlocked;
    }

    @Override
    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    @Override
    public int getFailedAttempts() {
        return this.attempts;
    }

    @Override
    public void reset() {
        this.isLocked = false;
        this.attempts = 0;
        this.isBlocked = false;
        this.isPinSet = false;
    }
}
