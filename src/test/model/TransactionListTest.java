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
    private TransactionList transactionList2;
    private TransactionList expectedList;


    @BeforeEach
    public void setup() {
        t1 = new Transaction("2000-03-07", 1);
        t2 = new Transaction("2015-12-23",100);
        t3 = new Transaction("2016-01-24",-1000);
        t4 = new Transaction("2019-09-07",10000);
        t5 = new Transaction("2019-09-07",10);

        transactionList1 = new TransactionList();
        transactionList2 = new TransactionList();
        expectedList = new TransactionList();
    }

    @Test
    public void testInsertTransaction(){

        transactionList2.insertTransaction(t1);
        transactionList2.insertTransaction(t2);
        assertEquals(2,transactionList2.size());

//        assertTrue(transactionList2.contains(t2));


//        transactionList1.add(t2);
//
//        assertEquals(1,transactionList1.size());
//        assertTrue(transactionList1.contains(t2));
    }

    @Test
    public void testTransactionNum() {
        transactionList2.insertTransaction(t1);
        transactionList2.insertTransaction(t2);

        assertEquals(2,transactionList2.transactionNum());
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

}
