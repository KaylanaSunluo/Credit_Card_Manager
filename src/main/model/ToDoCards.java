package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//represents a list of cards remaining to be managed
public class ToDoCards {
    List<CreditCard> cardList;

    // EFFECTS: constructs an empty collection of creditCardAccount
    public ToDoCards() {
        cardList = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a credit card to the card list
    public void addCard(CreditCard creditCard) {
        cardList.add(creditCard);
    }

    // EFFECTS: returns the card list
    public List<CreditCard> getCreditCardsList() {
        return cardList;
    }

    // EFFECTS: returns the length of credit card list
    public int cardListLength() {
        return cardList.size();
    }

}













