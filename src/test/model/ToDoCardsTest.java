package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoCardsTest extends List {
    private CreditCardAccount c1;
    private CreditCardAccount c2;
    private CreditCardAccount c3;

    private ToDoCards cardsList1;
    private ToDoCards cardsList2;

    @BeforeEach
    public void setup() {
        c1 = new CreditCardAccount("1234 1234 4321 4321","Kay Sun",
                "2205 Lower Mall, Vancouver","(778)1231123", 500);
        c2 = new CreditCardAccount("1231 3131 4342 1235","Janice Lee",
                "1002 Happy Garden, Vancouver","(778)9871153", 3000);
        c2 = new CreditCardAccount("1783 3299 4992 9915","Tom Lu",
                "1188 Agronomy Road, Vancouver","(778)1133222", 10000);

        cardsList1 = new ToDoCards();
        cardsList2 = new ToDoCards();
    }


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
