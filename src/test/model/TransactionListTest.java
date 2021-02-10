package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionListTest {
    private Transaction t1;
    private Transaction t2;
    private Transaction t3;
    private Transaction t4;
    private Transaction t5;

    private TransactionList transactionList1;



    @BeforeEach
    public void setup() {
        t1 = new Transaction("2000-03-07", 1.99);
        t2 = new Transaction("2015-12-23",100);
        t3 = new Transaction("2016-01-24",-1000);
        t4 = new Transaction("2019-09-07",10000);
        t5 = new Transaction("2019-09-07",10);

        transactionList1 = new TransactionList();

    }


    @Test
    public void testInsertTransaction(){
        transactionList1.insertTransaction(t1);
        assertEquals(1,transactionList1.length());

    }


    @Test
    public void testTransactionNum() {
        transactionList1.insertTransaction(t1);
        transactionList1.insertTransaction(t2);

        assertEquals(2,transactionList1.length());
    }


    @Test
    public void testRemoveTransactionBeforeGivenDateNotSame() throws ParseException {
        transactionList1.insertTransaction(t1);
        transactionList1.insertTransaction(t2);
        transactionList1.insertTransaction(t3);
        transactionList1.insertTransaction(t4);

        LinkedList<Transaction> transactionListAfterRemoving =
                transactionList1.transactionListBeforeGivenDate("2015-12-12");

        assertEquals(1, transactionListAfterRemoving.size());
        assertTrue(transactionListAfterRemoving.contains(t1));
    }


    @Test
    public void testRemoveTransactionBeforeGivenDateSame() throws ParseException {

        transactionList1.insertTransaction(t1);
        transactionList1.insertTransaction(t2);
        transactionList1.insertTransaction(t3);
        transactionList1.insertTransaction(t4);

        LinkedList<Transaction> transactionListAfterRemoving =
                transactionList1.transactionListBeforeGivenDate("2016-01-24");

        assertEquals(2, transactionListAfterRemoving.size());
        assertTrue(transactionListAfterRemoving.contains(t1));
        assertTrue(transactionListAfterRemoving.contains(t2));
    }


    @Test
    public void testTransactionAmount() {
        transactionList1.insertTransaction(t1);
        transactionList1.insertTransaction(t2);
        transactionList1.insertTransaction(t3);
        transactionList1.insertTransaction(t4);

        double expectedAmount = 1.99+100-1000+10000;
        assertEquals(expectedAmount,transactionList1.transactionAmount());
    }
}
