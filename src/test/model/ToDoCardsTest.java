package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ToDoCardsTest {
    private CreditCard c1;
    private CreditCard c2;
    private CreditCard c3;

    private Transaction t1;

    private ToDoCards cardList;

    @BeforeEach
    public void setup() {
        c1 = new CreditCard("1234 1234 4321 4321","Kay Sun",
                "2205 Lower Mall, Vancouver","(778)1231123", 500);
        c2 = new CreditCard("1231 3131 4342 1235","Janice Lee",
                "1002 Happy Garden, Vancouver","(778)9871153", 3000);
        c2 = new CreditCard("1783 3299 4992 9915","Tom Lu",
                "1188 Agronomy Road, Vancouver","(778)1133222", 10000);

        t1 = new Transaction("2015-12-23",499);

        cardList = new ToDoCards();

    }

    @Test
    public void testAddCard() {
        cardList.addCard(c1);
        List<CreditCard> result = cardList.getCreditCardsList();
        assertEquals(1,result.size());
        assertTrue(result.contains(c1));
    }

    @Test
    public void testGetCreditCardList() {
        cardList.addCard(c1);
        cardList.addCard(c2);

        List<CreditCard> expectedCardList = new ArrayList<>();
        expectedCardList.add(c1);
        expectedCardList.add(c2);

        assertEquals(expectedCardList,cardList.getCreditCardsList());
    }

    @Test
    public void testCardListLength() {
        cardList.addCard(c1);
        cardList.addCard(c2);
        cardList.addCard(c3);
        assertEquals(3,cardList.cardsListLength());

    }

//    @Test
//    public void testLowCreditLeft() {
//        c1.updateBalance(t1);
//
//        cardList.addCard(c1);
//        cardList.addCard(c2);
//        cardList.addCard(c3);
//
//
//
//        List<CreditCard> resultCardList = cardList.lowCreditLeft(0.5);
//        List<CreditCard> expectedCardList = new ArrayList<>();
////        expectedCardList.add(c1);
//
//        assertEquals(2,resultCardList.size());
//
////        assertEquals(expectedCardList,cardList.lowCreditLeft(0.5));
//
//    }






//    @Test
//    public void testFindCardExists() {
//        cardsList1.addCard(c1);
//        cardsList1.addCard(c2);
//
//        assertTrue(cardsList1.findCard("1234 1234 4321 4321"));
//    }
//    @Test
//    public void testFindCardNotExists() {
//        cardsList1.addCard(c1);
//        cardsList1.addCard(c2);
//
//        assertFalse(cardsList1.findCard("1234 1234 4321 4111"));
//    }



//    @Test
//    public void testAddCard() {
//        cardsList1.addCard(c1);
//        cardsList1.addCard(c2);
//        assertEquals(2,cardsList1.);
//
//    }
//    @Test
//    public void testlowCreditLeft



}
