import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private AccountHolder accountHolder;
    private BankAccount bankAccount;
    public static final double INITIAL_BALANCE = 0;
    public static final int ID_ROSSI = 1;
    public static final int WRONG_ID = ID_ROSSI + 1;
    public static final double FIRST_DEPOSIT = 100 ;
    public static final double DEPOSIT_SECOND_ID = 50;
    public static final double FIRST_WITHDRAW = 70;

    @BeforeEach
    void setUpBankAccount(){
        accountHolder = new AccountHolder("Mario", "Rossi", ID_ROSSI);
        bankAccount = new SimpleBankAccount(accountHolder, INITIAL_BALANCE);
    }

    @Test
    void testInitialBalance() {
        assertAll(
                () -> assertEquals(INITIAL_BALANCE, bankAccount.getBalance()),
                () -> assertEquals(accountHolder, bankAccount.getHolder()));
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), FIRST_DEPOSIT);
        assertEquals(FIRST_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.getId(), FIRST_DEPOSIT);
        bankAccount.deposit(WRONG_ID, DEPOSIT_SECOND_ID);
        assertEquals(FIRST_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.getId(), FIRST_DEPOSIT);
        bankAccount.withdraw(accountHolder.getId(), FIRST_WITHDRAW);
        assertEquals(FIRST_DEPOSIT - FIRST_WITHDRAW - SimpleBankAccount.FEE, bankAccount.getBalance());
    }

    @Test
    void testWrongIdWithdraw() {
        bankAccount.deposit(accountHolder.getId(), FIRST_DEPOSIT);
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(ID_ROSSI, FIRST_DEPOSIT));
    }

    @Test
    void testWrongAmountWithdraw() {
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(ID_ROSSI, FIRST_DEPOSIT));
    }

    @Test
    void testEqualAmountWithdraw() {
        bankAccount.deposit(accountHolder.getId(), FIRST_DEPOSIT);
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(ID_ROSSI, FIRST_DEPOSIT));
    }

    @Test
    void testIllegalArgumentExceptionWithWrongAmount() {
        bankAccount.deposit(accountHolder.getId(), FIRST_DEPOSIT);
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(ID_ROSSI, FIRST_DEPOSIT));
    }
}
