package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.CreditCard;
import model.Transaction;
import model.TransactionList;

import java.util.ArrayList;
import java.util.List;


public class JsonTest {
    protected void checkCreditCard(CreditCard card, int accountNo, String cardNo, String cardHolderName,
                                   String address, String phone, int creditLimit, double balance,
                                   TransactionList transactionList) {
        assertEquals(accountNo, card.getAccountNo());
        assertEquals(cardNo, card.getCreditCardNo());
        assertEquals(cardHolderName, card.getName());
        assertEquals(address, card.getAddress());
        assertEquals(phone, card.getPhoneNo());
        assertEquals(creditLimit, card.getCreditLimit());
        assertEquals(balance, card.getBalance());

        ArrayList<Transaction> list = card.getTransactionList();

        assertEquals(transactionList, list);

    }

}
