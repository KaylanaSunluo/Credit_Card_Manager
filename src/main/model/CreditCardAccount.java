package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

// Represents an credit card account having an account number, card number,
// name, address, phone number, credit limit (in dollars), balance (in dollars)
// and a list of transactions (in dollars)

public class CreditCardAccount {
    private static int nextAccountNo = 1;    // tracks number of next account created
    private int accountNo;                   // account number
    private int cardNo;                      // card number
    private String name;                     // cardholder name of card holder
    private String address;                  // the address of card holder
    private String phoneNo;                  // the phone number of card holder
    private int creditLimit;                 // credit limit
    private double balance;                  // the current balance of the card account,with
                                             // positive representing the money owed to the bank and
                                             // negative representing the money saved in the credit card

    private TransactionList transactions;  // the list of past transactions

    /*
     * REQUIRES: cardNumber consists of 16 digits, starting with 3761, no space;
     *           phone is in form: (123) 4567890;
     *           initialCreditLimit >= 1000
     * EFFECTS: account number is a positive integer not assigned to any other credit card account;
     *          cardNo id set to cardNumber; name on credit card account is set to cardHolderName;
     *          address on credit card account is set to cardHolderAddress; phone number on credit
     *          card account is set to phone; credit limit is set to initialCreditLimit; balance on
     *          credit card account is set to 0; transaction list is set to a empty list.
     */
    public void creditCardAccount(int cardNumber,
                                  String cardHolderName,
                                  String cardHolderAddress,
                                  String phone,
                                  int initialCreditLimit) throws ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
//        System.out.println(dtf.format(localDate));




        accountNo = nextAccountNo++;
        cardNo = cardNumber;
        name = cardHolderName;
        address = cardHolderAddress;
        phoneNo = phone;
        creditLimit = initialCreditLimit;
        balance = 0;
//        upToDateStatement = new Statement(localDate.toString(),
//                0,28,transactions);
        transactions = new TransactionList();
    }

    // getters
    public int getAccountNo() {
        return accountNo;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public TransactionList getTransactions() {
        return transactions;
    }

//    public Statement getUpToDateStatement() {
//        return upToDateStatement;
//    }



    /*
     * MODIFIES: this
     * EFFECTS: amount is deducted from balance and updated
     * 			balance is returned
     */
    public double updateBalance(Transaction transaction) {
        balance = balance + transaction.getAmount();
        return balance;
    }






}



