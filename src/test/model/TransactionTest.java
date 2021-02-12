package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    private Transaction transaction;

    private String date1;
    private String date2;


    @BeforeEach
    public void setup(){
        transaction = new Transaction("2020-02-02",2000);
    }



    @Test
    public void testGetDate() {
        assertEquals("2020-02-02", transaction.getDate());
    }

    @Test
    public void testGetAmount() {
        assertEquals(2000, transaction.getAmount());
    }



    @Test
    public void testBeforeDateBefore() throws ParseException {
        assertTrue (transaction.beforeDate("2022-03-07"));
    }

    @Test
    public void testBeforeDateNotBefore() throws ParseException {
        assertFalse (transaction.beforeDate("2020-02-02"));
        assertFalse (transaction.beforeDate("2000-07-03"));

    }


}
