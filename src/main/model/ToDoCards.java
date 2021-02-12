package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    public int cardsListLength() {
        return cardList.size();
    }


    // REQUIRES: 0 < creditLimitPercent <= 1
    // EFFECTS: returns a list of credit card accounts that have used more than
    //          given percent of credit limit
//    public List<CreditCard> lowCreditLeft(double creditLimitPercent) {
//        List<CreditCard> cardsSoFar = new ArrayList<>();
//
//        for (CreditCard eachCard : cardList) {
//            if (eachCard.getBalance() >= creditLimitPercent * eachCard.getCreditLimit()) {
//                cardsSoFar.add(eachCard);
//            }
//        }
//        return cardsSoFar;
//    }
}

//    public boolean findCard(int accountNo) {
//        int count = 1;
//        for (CreditCard eachCard : cardsList) {
//            if (eachCard.getAccountNo() == accountNo) {
//                count++;
//                return eachCard
//
//            } else if (count >= cardsList.cardsNum()) {
//                System.out.println("No such card exists!\n");
//            }
//        }
//    }











