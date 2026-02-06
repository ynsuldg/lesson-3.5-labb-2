package se.iths.yunus.unittesting.service;

import org.springframework.stereotype.Service;
import se.iths.yunus.unittesting.component.AccountComponent;
import se.iths.yunus.unittesting.exception.InsufficientFundsException;
import se.iths.yunus.unittesting.exception.InvalidAmountException;
import se.iths.yunus.unittesting.exception.MaxWithdrawalExceededException;

@Service
public class ATMService {

    private final AccountComponent account;
    private static final int MAX_WITHDRAWAL = 5000;

    public ATMService(AccountComponent account) {
        this.account = account;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than 0.");
        }
        account.deposit(amount);
    }

    public void withdraw(double amount) {

        if (amount <= 0)
            throw new InvalidAmountException("Invalid amount");

        if (amount > MAX_WITHDRAWAL)
            throw new MaxWithdrawalExceededException("Too large amount");

        double balance = account.showBalance();

        if (amount > balance)
            throw new InsufficientFundsException("Not enough funds");

        account.withdraw(amount);
    }

    public double showBalance() {
        return account.showBalance();
    }
}