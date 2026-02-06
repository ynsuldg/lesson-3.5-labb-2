package se.iths.yunus.unittesting.component;

import org.springframework.stereotype.Component;

@Component
public class AccountComponent {

    private double balance = 0;

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public double showBalance() {
        return balance;
    }
}
