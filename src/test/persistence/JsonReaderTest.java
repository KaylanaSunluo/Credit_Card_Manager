package persistence;

import model.CreditCard;
import model.ToDoCards;
import model.Transaction;
import model.TransactionList;
import model.exceptions.FormatIncorrectException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ToDoCards cardList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyToDoCards() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyToDoCards.json");
        try {
            ToDoCards cardList = reader.read();
            assertEquals(0, cardList.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralToDoCards() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralToDoCards.json");
        try {
            ToDoCards cardList = reader.read();

            List<CreditCard> cards = cardList.getList();
            assertEquals(2, cards.size());

            TransactionList transactionList = new TransactionList();
            Transaction t1 = new Transaction("2020-02-02",200);

            transactionList.insertTransactionToTransactionList(t1);
            transactionList.insertTransactionToTransactionList(t1);

            checkCreditCard(cards.get(0),1,"2222 3333 4444 5555", "JL",
                    "1002 Happy Garden, Vancouver", "(778)9871153", 3000,0,
                    transactionList);
            checkCreditCard(cards.get(1),2,"1111 2222 3333 4444", "KF",
                    "2205 Lower Mall, Vancouver", "(778)1231123", 500, 0,
                    new TransactionList());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
