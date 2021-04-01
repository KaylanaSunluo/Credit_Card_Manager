# Credit Cards App

## *Kaylana Sunluo*
<p>This application would be part of Transaction Processing System. The intended audience 
would include credit cards managers in a bank, and any other who needs to look into credit
cards information.</p>

This application would allow the users to:</br>
* search for a credit card with all the details
* set up a new credit card
* add a transaction to a credit card
* search for transactions of a credit card before a date
* visualize the transactions of a card


<p>Motivations: I had experience in designing a transaction processing system. It 
allows a bank to collect data from different sections online and 
build relationships among them. The bank could search for, edit and 
integrate data for further analysis. This project would be part of 
credit cards section and store structured data that allows managers to 
read and extract necessary information.</p>




## User Stories

- As a user, I want to be able to select a credit card account and view the details 
- As a user, I want to be able to create a new credit card account and add it to a list of cards
- As a user, I want to be able to select a credit card account and 
add a transaction to the transaction list of that card, updating the balance at the same time
- As a user, I want to be able to select a credit card account and 
search for a list of transactions before a given date of that card
- As a user, I want to be able to save my credit card list to file
- As a user, I want to be able to be able to load my credit card list from file</p>
 
## Phase 4: Task 2

- *Test and design a class in your model package that is robust.* 
<br> **class**: CreditCard </br>
<br>**method**: changeAccountNo(int)</br>
- *Include a type hierarchy in the code.*
<br> **interface**: RecordList</br>
<br> **subclasses**: ToDoCards,TransactionList </br>
<br>**overriding method**: changeAccountNo(int)</br>
