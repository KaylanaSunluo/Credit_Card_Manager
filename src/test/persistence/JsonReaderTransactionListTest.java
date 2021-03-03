package persistence;

import model.Transaction;
import model.TransactionList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTransactionListTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReaderTransactionList reader =
                new JsonReaderTransactionList("./data/noSuchFile.json");
        try {
            TransactionList transactionList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyToDoCards() {
        JsonReaderTransactionList reader =
                new JsonReaderTransactionList("./data/testReaderEmptyTransactionList.json");
        try {
            TransactionList transactionList = reader.read();
            assertEquals(0, transactionList.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralToDoCards() {
        JsonReaderTransactionList reader =
                new JsonReaderTransactionList("./data/testReaderGeneralTransactionList.json");
        try {
            TransactionList transactionList = reader.read();

            List<Transaction> transactions = transactionList.getTransactionList();
            assertEquals(2, transactions.size());

            checkTransaction(transactions.get(0),"2020-02-02",200);
            checkTransaction(transactions.get(1),"2000-03-03", -20.1);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
