package se.iths.yunus.unittesting.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountComponentTest {

    private AccountComponent account;

    @BeforeEach
    void setup() {
        account = new AccountComponent();
    }

    @Test
    void balanceStartsAtZero() {
        assertEquals(0, account.showBalance());
    }

    @Test
    void depositIncreasesBalance() {
        account.deposit(100);
        assertEquals(100, account.showBalance());
    }

    @Test
    void withdrawDecreasesBalance() {
        account.deposit(200);
        account.withdraw(100);
        assertEquals(100, account.showBalance());
    }

    @Test
    void depositAndWithdrawTogether() {
        account.deposit(300);
        account.withdraw(200);
        account.deposit(100);
        assertEquals(200, account.showBalance());
    }
}