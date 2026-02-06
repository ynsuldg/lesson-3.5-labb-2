package se.iths.yunus.unittesting.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.yunus.unittesting.component.AccountComponent;
import se.iths.yunus.unittesting.exception.InsufficientFundsException;
import se.iths.yunus.unittesting.exception.InvalidAmountException;
import se.iths.yunus.unittesting.exception.MaxWithdrawalExceededException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ATMServiceTest {

    @Mock
    private AccountComponent account;

    @InjectMocks
    private ATMService service;

    // ----------- DEPOSIT (ERROR FLOW) -----------

    @Test
    void depositInvalidAmountThrowsException() {
        // Act + Assert
        assertThrows(InvalidAmountException.class, () -> service.deposit(0.0));
        verify(account, never()).deposit(anyDouble());
    }

    // ----------- DEPOSIT (NORMAL FLOW) -----------

    @Test
    void depositValidAmountCallsComponent() {
        // Act
        service.deposit(100.0);

        // Assert
        verify(account).deposit(100.0);
    }

    // ----------- WITHDRAW (ERROR FLOWS) -----------

    @Test
    void withdrawInvalidAmountThrowsException() {
        // Act + Assert
        assertThrows(InvalidAmountException.class, () -> service.withdraw(-10.0));
        verify(account, never()).withdraw(anyDouble());
    }

    @Test
    void withdrawOverMaxThrowsException() {
        // Act + Assert
        assertThrows(MaxWithdrawalExceededException.class, () -> service.withdraw(999999.0));
        verify(account, never()).withdraw(anyDouble());
    }

    @Test
    void withdrawInsufficientFundsThrowsException() {
        // Arrange
        when(account.showBalance()).thenReturn(50.0);

        // Act + Assert
        assertThrows(InsufficientFundsException.class, () -> service.withdraw(150.0));
        verify(account, never()).withdraw(anyDouble());
    }

    // ----------- WITHDRAW (NORMAL FLOW) -----------

    @Test
    void withdrawValidAmountCallsComponent() {
        // Arrange
        when(account.showBalance()).thenReturn(500.0);

        // Act
        service.withdraw(100);

        // Assert
        verify(account).withdraw(100);
    }

    // ----------- GET BALANCE -----------

    @Test
    void getBalanceReturnsCorrectValue() {
        // Arrange
        when(account.showBalance()).thenReturn(500.0);

        // Act
        double result = service.showBalance();

        // Assert
        assertEquals(500.0, result);
        verify(account).showBalance();
    }
}