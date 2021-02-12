package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionListTest {
    private Transaction t1;
    private Transaction t2;
    private Transaction t3;
    private Transaction t4;
    private Transaction t5;

    private TransactionList transactionList;


    @BeforeEach
    public void setup() {
        t1 = new Transaction("2000-03-07", 1.99);
        t2 = new Transaction("2015-12-23",100);
        t3 = new Transaction("2016-01-24",-1000);
        t4 = new Transaction("2019-09-07",10000);
        t5 = new Transaction("2019-09-07",10);

        transactionList = new TransactionList();
    }


    @Test
    public void testInsertTransactionToTransactionList(){
        transactionList.insertTransactionToTransactionList(t1);
        transactionList.insertTransactionToTransactionList(t2);

        List<Transaction> resultTransactionList = transactionList.getTransactionList();

        assertEquals(2,resultTransactionList.size());
        assertEquals(t1,resultTransactionList.get(0));
        assertEquals(t2,resultTransactionList.get(1));
    }


    @Test
    public void testLength() {
        transactionList.insertTransactionToTransactionList(t1);
        transactionList.insertTransactionToTransactionList(t2);

        assertEquals(2, transactionList.length());
    }


    @Test
    public void testTransactionAmount() {
        transactionList.insertTransactionToTransactionList(t1);
        transactionList.insertTransactionToTransactionList(t2);
        transactionList.insertTransactionToTransactionList(t3);
        transactionList.insertTransactionToTransactionList(t4);

        double expectedAmount = 1.99+100-1000+10000;
        assertEquals(expectedAmount, transactionList.transactionAmount());
    }


    @Test
    public void testGetTransactionList(){
        transactionList.insertTransactionToTransactionList(t1);
        transactionList.insertTransactionToTransactionList(t2);
        transactionList.insertTransactionToTransactionList(t2);

        List<Transaction> expectedTransactionList = new ArrayList<>();
        expectedTransactionList.add(t1);
        expectedTransactionList.add(t2);
        expectedTransactionList.add(t2);

        assertEquals(expectedTransactionList, transactionList.getTransactionList());
    }


    @Test
    public void testRemoveTransactionBeforeGivenDateNotSame() throws ParseException {
        transactionList.insertTransactionToTransactionList(t1);
        transactionList.insertTransactionToTransactionList(t2);
        transactionList.insertTransactionToTransactionList(t3);
        transactionList.insertTransactionToTransactionList(t4);

        List<Transaction> transactionListAfterRemoving =
                transactionList.transactionListBeforeGivenDate("2015-12-12");

        assertEquals(1, transactionListAfterRemoving.size());
        assertTrue(transactionListAfterRemoving.contains(t1));
    }


    @Test
    public void testRemoveTransactionBeforeGivenDateSame() throws ParseException {

        transactionList.insertTransactionToTransactionList(t1);
        transactionList.insertTransactionToTransactionList(t2);
        transactionList.insertTransactionToTransactionList(t3);
        transactionList.insertTransactionToTransactionList(t4);

        List<Transaction> transactionListAfterRemoving =
                transactionList.transactionListBeforeGivenDate("2016-01-24");

        assertEquals(2, transactionListAfterRemoving.size());
        assertTrue(transactionListAfterRemoving.contains(t1));
        assertTrue(transactionListAfterRemoving.contains(t2));
    }



}
