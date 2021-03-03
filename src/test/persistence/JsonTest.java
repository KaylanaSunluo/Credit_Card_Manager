package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.CreditCard;
import model.Transaction;


public class JsonTest {
    protected void checkCreditCard(CreditCard card, int accountNo, String cardNo, String cardHolderName,
                                   String address, String phone, int creditLimit, double balance) {
        assertEquals(accountNo, card.getAccountNo());
        assertEquals(cardNo, card.getCreditCardNo());
        assertEquals(cardHolderName, card.getName());
        assertEquals(address, card.getAddress());
        assertEquals(phone, card.getPhoneNo());
        assertEquals(creditLimit, card.getCreditLimit());
        assertEquals(balance, card.getBalance());

    }

    protected void checkTransaction (Transaction transaction, String date, double amount) {
        assertEquals(transaction.getDate(),date);
        assertEquals(transaction.getAmount(),amount);
    }
}
