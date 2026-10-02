package parking;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WalletTest {


    @Test
    void testDefaultConstructor() {
        Wallet wallet = new Wallet();

        assertEquals(0.0, wallet.getBalance());
    }


    @Test
    void testInitialBalance() {
        Wallet wallet = new Wallet(100.0);

        assertEquals(100.0, wallet.getBalance());
    }









    @Test
    void testAddFunds() {
        Wallet wallet = new Wallet(100.0);

        wallet.addFunds(50.0);

        assertEquals(150.0, wallet.getBalance());
    }


    @Test
    void testAddZeroFunds() {
        Wallet wallet = new Wallet(100.0);

        assertThrows(
                InvalidAmountException.class,
                () -> wallet.addFunds(0.0)
        );

        assertEquals(100.0, wallet.getBalance());
    }

    // 5. Add negative funds -> exception
    @Test
    void testAddNegativeFunds() {
        Wallet wallet = new Wallet(100.0);

        assertThrows(
                InvalidAmountException.class,
                () -> wallet.addFunds(-50.0)
        );

        assertEquals(100.0, wallet.getBalance());
    }

    // 6. Deduct valid amount
    @Test
    void testDeductFunds() {
        Wallet wallet = new Wallet(100.0);

        wallet.deductFunds(40.0);

        assertEquals(60.0, wallet.getBalance());
    }

    // 7. Deduct exact balance
    @Test
    void testDeductExactBalance() {
        Wallet wallet = new Wallet(100.0);

        wallet.deductFunds(100.0);

        assertEquals(0.0, wallet.getBalance());
    }

    // 8. Deduct more than balance
    @Test
    void testDeductInsufficientFunds() {
        Wallet wallet = new Wallet(100.0);

        assertThrows(
                InsufficientFundsException.class,
                () -> wallet.deductFunds(150.0)
        );

        assertEquals(100.0, wallet.getBalance());
    }

    // 9. Deduct zero
    @Test
    void testDeductZeroFunds() {
        Wallet wallet = new Wallet(100.0);

        assertThrows(
                InvalidAmountException.class,
                () -> wallet.deductFunds(0.0)
        );
    }

    // 10. Deduct negative amount
    @Test
    void testDeductNegativeFunds() {
        Wallet wallet = new Wallet(100.0);

        assertThrows(
                InvalidAmountException.class,
                () -> wallet.deductFunds(-20.0)
        );
    }

    // 11. Successful transfer
    @Test
    void testTransferFunds() {
        Wallet from = new Wallet(100.0);
        Wallet to = new Wallet(50.0);

        from.transferFunds(to, 30.0);

        assertEquals(70.0, from.getBalance());
        assertEquals(80.0, to.getBalance());
    }

    // 12. Transfer exact balance
    @Test
    void testTransferExactBalance() {
        Wallet from = new Wallet(100.0);
        Wallet to = new Wallet(50.0);

        from.transferFunds(to, 100.0);

        assertEquals(0.0, from.getBalance());
        assertEquals(150.0, to.getBalance());
    }

    // 13. Transfer more than balance
    @Test
    void testTransferInsufficientFunds() {
        Wallet from = new Wallet(100.0);
        Wallet to = new Wallet(50.0);

        assertThrows(
                InsufficientFundsException.class,
                () -> from.transferFunds(to, 150.0)
        );

        assertEquals(100.0, from.getBalance());
        assertEquals(50.0, to.getBalance());
    }

    // 14. Transfer zero
    @Test
    void testTransferZeroFunds() {
        Wallet from = new Wallet(100.0);
        Wallet to = new Wallet(50.0);

        assertThrows(
                InvalidAmountException.class,
                () -> from.transferFunds(to, 0.0)
        );
    }

    // 15. Transfer negative amount
    @Test
    void testTransferNegativeFunds() {
        Wallet from = new Wallet(100.0);
        Wallet to = new Wallet(50.0);

        assertThrows(
                InvalidAmountException.class,
                () -> from.transferFunds(to, -20.0)
        );
    }
}