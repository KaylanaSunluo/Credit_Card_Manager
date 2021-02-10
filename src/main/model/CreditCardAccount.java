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
    private String cardNo;                   // card number
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
     * EFFECTS: constructs a credit card account with account number, card number, card holder name,
     *          card holder address, card holder phone number, credit limit, zero balance and
     *          an empty transaction list.
     *
     *          account number is a positive integer not assigned to any other credit card account;
     *          cardNo is in the form "xxxx xxxx xxxx xxxx"; name on credit card account is set to
     *          cardHolderName; address on credit card account is set to cardHolderAddress; phone
     *          number on credit card account is set to phone; credit limit is set to initialCreditLimit;
     *          balance on credit card account is set to 0; transaction list is set to a empty list.
     */

    public CreditCardAccount(String cardNumber, String cardHolderName, String cardHolderAddress,
                             String phone, int initialCreditLimit) {
        this.accountNo = nextAccountNo++;
        this.cardNo = cardNumber;
        this.name = cardHolderName;
        this.address = cardHolderAddress;
        this.phoneNo = phone;
        this.creditLimit = initialCreditLimit;
        this.balance = 0;
        this.transactions = new TransactionList();
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

    public double getCreditLimit() {
        return creditLimit;
    }

    public TransactionList getTransactions() {
        return transactions;
    }






    /*  MODIFIES: this
     * EFFECTS: amount is deducted from balance and updated
     * 			balance is returned
     */
    public double updateBalance(Transaction transaction) {
        balance = balance + transaction.getAmount();
        return balance;
    }






}



