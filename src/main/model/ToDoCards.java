package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ToDoCards {
    List<CreditCardAccount> cardsList;

    // EFFECTS: constructs an empty collection of creditCardAccount
    public ToDoCards() {
        cardsList = new ArrayList<>();
    }

    public void addCard(CreditCardAccount creditCardAccount) {
        cardsList.add(creditCardAccount);
    }

    // EFFECTS: returns a list of credit card accounts that have used more than 90% credit limit
    public List<CreditCardAccount> lowCreditLeft() {
        List<CreditCardAccount> cardsSoFar = new ArrayList<>();
        for (CreditCardAccount eachCard: cardsSoFar) {
            if (eachCard.getBalance() >= 0.9 * eachCard.getCreditLimit()) {
                cardsSoFar.add(eachCard);
            }
        }
        return cardsSoFar;
    }


}







