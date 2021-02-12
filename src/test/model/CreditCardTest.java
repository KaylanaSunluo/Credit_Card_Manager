package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardTest {
    private CreditCard card;

    private Transaction t1;
    private Transaction t2;
    private Transaction t3;

    @BeforeEach
    public void setup() {
        card = new CreditCard("3434 2323 3434 1212",
                "Kat Hathaway",
                "2203 Lowe Mall, Vancouver",
                "(778)3176276",1000);

        t1 = new Transaction("2000-03-07", 1.99);
        t2 = new Transaction("2015-12-23",100);
        t3 = new Transaction("2016-01-24",-1000);

    }

    @Test
    public void testGetAccountNo() {
        assertEquals(3,card.getAccountNo());
    }

    @Test
    public void testGetCreditCardNo() {
        assertEquals("3434 2323 3434 1212",card.getCreditCardNo());
    }

    @Test
    public void testGetName() {
        assertEquals("Kat Hathaway",card.getName());
    }

    @Test
    public void testGetAddress() {
        assertEquals("2203 Lowe Mall, Vancouver",card.getAddress());
    }

    @Test
    public void testGetPhoneNo() {
        assertEquals("(778)3176276",card.getPhoneNo());
    }

    @Test
    public void testGetBalance() {
        assertEquals(0,card.getBalance());
    }

    @Test
    public void testGetCreditLimit() {
        assertEquals(1000,card.getCreditLimit());
    }

    @Test
    public void testGetTransactionList() {
          card.addTransactionToCard(t1);
          card.addTransactionToCard(t2);
          TransactionList resultTransactionList = card.getTransactionList();

        TransactionList expectedTransactionList = new TransactionList();
        expectedTransactionList.insertTransactionToTransactionList(t1);
        expectedTransactionList.insertTransactionToTransactionList(t2);

        assertEquals(expectedTransactionList.getTransactionList(),
                resultTransactionList.getTransactionList());

    }

    @Test
    public void testUpdateBalance(){
        card.updateBalance(t3);
        assertEquals(-1000,card.getBalance());
    }

    @Test
    public void testAddTransactionToCard() {
        card.addTransactionToCard(t1);
        card.addTransactionToCard(t2);
        TransactionList transactionListOfCard = card.getTransactionList();
        List<Transaction> listOfTransactionOfCard= transactionListOfCard.getTransactionList();

        assertEquals(2, listOfTransactionOfCard.size());
        assertEquals(t1, listOfTransactionOfCard.get(0));
        assertEquals(t2, listOfTransactionOfCard.get(1));
    }
}
