package persistence;

import model.CreditCard;
import model.ToDoCards;
import model.Transaction;
import model.TransactionList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTransactionListTest extends JsonTest{

    private Transaction t1;
    private Transaction t2;

    @BeforeEach
    public void setup() {
        t1 = new Transaction("2019-02-02", -900);
        t2 = new Transaction("2000-03-07", 52.8);
    }


    @Test
    void testWriterInvalidFile() {
        try {
            TransactionList transactionList = new TransactionList();
            JsonWriterTransactionList writer =
                    new JsonWriterTransactionList("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyToDoCards() {
        try {
            TransactionList transactionList = new TransactionList();
            JsonWriterTransactionList writer =
                    new JsonWriterTransactionList("./data/testWriterEmptyTransactionList.json");
            writer.open();
            writer.write(transactionList);
            writer.close();

            JsonReaderTransactionList reader =
                    new JsonReaderTransactionList("./data/testWriterEmptyTransactionList.json");
            transactionList = reader.read();
            assertEquals(0, transactionList.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralToDoCards() {
        try {
            TransactionList transactionList = new TransactionList();
            transactionList.insertTransactionToTransactionList(t1);
            transactionList.insertTransactionToTransactionList(t2);
            JsonWriterTransactionList writer =
                    new JsonWriterTransactionList("./data/testWriterGeneralTransactionList.json");
            writer.open();
            writer.write(transactionList);
            writer.close();

            JsonReaderTransactionList reader =
                    new JsonReaderTransactionList("./data/testWriterGeneralTransactionList.json");
            transactionList = reader.read();

            List<Transaction> transactions = transactionList.getTransactionList();
            assertEquals(2, transactions.size());
            checkTransaction(transactions.get(0),"2019-02-02", -900);
            checkTransaction(transactions.get(1),"2000-03-07", 52.8);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
