package persistence;


import model.CreditCard;
import model.ToDoCards;
import model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    private CreditCard c1;
    private CreditCard c2;
    private Transaction t1;


    @BeforeEach
    public void setup() {
        c1 = new CreditCard("1111 2222 3333 4444", "KF",
                "2205 Lower Mall, Vancouver", "(778)1231123", 500);
        t1 = new Transaction("2020-02-02",200);

        c2 = new CreditCard("2222 3333 4444 5555", "JL",
                "1002 Happy Garden, Vancouver", "(778)9871153", 3000);
    }


    @Test
    void testWriterInvalidFile() {
        try {
            ToDoCards cardList = new ToDoCards();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyToDoCards() {
        try {
            ToDoCards cardList = new ToDoCards();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyToDoCards.json");
            writer.open();
            writer.write(cardList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyToDoCards.json");
            cardList = reader.read();
            assertEquals(0, cardList.cardListLength());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralToDoCards() {
        c1.addTransactionToCard(t1);
        c1.addTransactionToCard(t1);
        try {
            ToDoCards cardList = new ToDoCards();
            cardList.addCard(c1);
            cardList.addCard(c2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralToDoCards.json");
            writer.open();
            writer.write(cardList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralToDoCards.json");
            cardList = reader.read();

            List<CreditCard> cards = cardList.getCreditCardsList();
            assertEquals(2, cards.size());
            checkCreditCard(cards.get(0),1,"1111 2222 3333 4444", "KF",
                    "2205 Lower Mall, Vancouver", "(778)1231123", 500, 0);
            checkCreditCard(cards.get(1),2,"2222 3333 4444 5555", "JL",
                    "1002 Happy Garden, Vancouver", "(778)9871153", 3000,0);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
