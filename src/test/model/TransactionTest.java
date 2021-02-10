package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    private Transaction transaction1;

    private String date1;
    private String date2;


    @BeforeEach
    public void setup(){
        transaction1 = new Transaction("2020-02-02",2000);


    }



    @Test
    public void testGetDate() {
        assertEquals("2020-02-02",transaction1.getDate());
    }

    @Test
    public void testGetAmount() {
        assertEquals(2000,transaction1.getAmount());
    }



    @Test
    public void testBeforeDate() throws ParseException {
        assertFalse (transaction1.beforeDate("2020-02-02"));
        assertFalse (transaction1.beforeDate("2000-07-03"));
        assertTrue (transaction1.beforeDate("2022-03-07"));


    }




}
