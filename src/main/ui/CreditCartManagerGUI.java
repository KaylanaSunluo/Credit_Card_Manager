package ui;

import model.CreditCard;
import model.ToDoCards;
import model.Transaction;
import model.TransactionList;
import persistence.JsonReader;
import persistence.JsonWriter;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.util.List;
/*
 * Represents the main window in which the credit card lists are displayed
 *  and users can add a new card or search for transactions
 */

public class CreditCartManagerGUI extends JPanel implements ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;

    private static final String addCardString = "Add a new Card";
    private static final String clearString = "    Clear    ";
    private static final String searchString = "Search Transactions Before the Date";
    private static final String saveString = "Save records";
    private static final String loadString = "Load records";
    private JTextField cardNo;
    private JTextField name;
    private JTextField address;
    private JTextField phone;
    private JTextField creditLimit;

    private JTextField accountNo;
    private JTextField date;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/GUIAccounts.json";

    private ToDoCards cardList;

    private JButton addButton;
    private JButton clearButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton searchButton;

    private JPanel centerPane;

    private int accountNum;

    // EFFECTS: Constructs main window
    public CreditCartManagerGUI() {
        super(new BorderLayout());
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        createList();
        JScrollPane listScrollPane = new JScrollPane(list);

        addButton = new JButton(addCardString);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addCardString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);

        searchButton = new JButton(searchString);
        SearchListener searchListener = new SearchListener(searchButton);
        searchButton.setActionCommand(searchString);
        searchButton.addActionListener(searchListener);
        searchButton.setEnabled(false);

        addClearListenerToButton();
        addSaveListenerToButton();
        addLoadListenerToButton();

        addAddListenerToTextFields(addListener);

        addSearchListenerToTextFields(searchListener);

        createCenterPane(addButton, searchButton, saveButton, loadButton);

        add(listScrollPane, BorderLayout.CENTER);
        add(centerPane, BorderLayout.SOUTH);

    }

    // MODIFIES: accountNo and date
    // EFFECTS: adds a searchListener to accountNo and date each
    private void addSearchListenerToTextFields(SearchListener searchListener) {
        accountNo = new JTextField(10);
        addSearchListenerToTextField(searchListener, accountNo);

        date = new JTextField(10);
        addSearchListenerToTextField(searchListener, date);
    }

    // MODIFIES: cardNo, name, address, phone and creditLimit
    // EFFECTS: adds a addListener to cardNo, name, address, phone and creditLimit each
    private void addAddListenerToTextFields(AddListener addListener) {
        cardNo = new JTextField(10);
        addAddListenerToTextField(addListener, cardNo);

        name = new JTextField(10);
        addAddListenerToTextField(addListener, name);

        address = new JTextField(10);
        addAddListenerToTextField(addListener, address);

        phone = new JTextField(10);
        addAddListenerToTextField(addListener, phone);

        creditLimit = new JTextField(10);
        addAddListenerToTextField(addListener, creditLimit);
    }

    // MODIFIES: list
    // EFFECTS: creates a list that will be put in a scroll pane
    private void createList() {
        list = new JList(createListModel());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
    }

    // MODIFIES: accountNo
    // EFFECTS: adds a searchListener to accountNo
    private void addSearchListenerToTextField(SearchListener searchListener, JTextField accountNo) {
        accountNo.addActionListener(searchListener);
        accountNo.getDocument().addDocumentListener(searchListener);
    }

    // MODIFIES: cardNo
    // EFFECTS: adds a searchListener to cardNo
    private void addAddListenerToTextField(AddListener addListener, JTextField cardNo) {
        cardNo.addActionListener(addListener);
        cardNo.getDocument().addDocumentListener(addListener);
    }

    // MODIFIES: clearButton
    // EFFECTS: adds a clearListener to clearButton
    private void addClearListenerToButton() {
        clearButton = new JButton(clearString);
        ClearListener clearListener = new ClearListener(clearButton);
        clearButton.setActionCommand(clearString);
        clearButton.addActionListener(clearListener);
    }

    // MODIFIES: loadButton
    // EFFECTS: adds a loadListener to loadButton
    private void addLoadListenerToButton() {
        loadButton = new JButton(loadString);
        LoadListener loadListener = new LoadListener(loadButton);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(loadListener);
    }

    // MODIFIES: saveButton
    // EFFECTS: adds a saveListener to saveButton
    private void addSaveListenerToButton() {
        saveButton = new JButton(saveString);
        SaveListener saveListener = new SaveListener(saveButton);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(saveListener);
    }

    // EFFECTS: creates a panel using GridBagLayout
    private void createCenterPane(JButton addButton, JButton searchButton, JButton saveButton, JButton loadButton) {
        centerPane = new JPanel();
        centerPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;

        createLine(gbc, new JLabel("Card No.: "), new JLabel("Account No.:"), cardNo, accountNo);

        gbc.gridy++;
        createLine(gbc, new JLabel("Name: "), new JLabel("Date:"), name, date);

        gbc.gridy++;
        addLabelToPane(gbc,0, new JLabel("Address: "));
        addTextFieldToPane(gbc,1, address);
        addButtonToPane(gbc, 5, searchButton);

        gbc.gridy++;
        addLabelToPane(gbc,0, new JLabel("Phone: "));
        addTextFieldToPane(gbc,1, phone);
        addButtonToPane(gbc, 4, clearButton);

        gbc.gridy++;
        addLabelToPane(gbc,0, new JLabel("Credit Limit: "));
        addTextFieldToPane(gbc,1, creditLimit);

        gbc.gridy++;
        addButtonToPane(gbc, 1, addButton);
        addButtonToPane(gbc, 4, saveButton);
        addButtonToPane(gbc, 5, loadButton);

        centerPane.setBounds(100,100,400,200);
    }


    // MODIFIES: centerPane
    // EFFECTS: adds labels and text fields to the centerPane
    private void createLine(GridBagConstraints gbc, JLabel jl1, JLabel jl2, JTextField jtf1, JTextField jtf2) {
        addLabelToPane(gbc,0,jl1);
        addTextFieldToPane(gbc,1,jtf1);
        addLabelToPane(gbc,4,jl2);
        addTextFieldToPane(gbc,5,jtf2);
    }

    // MODIFIES: centerPane
    // EFFECTS: adds text fields to the centerPane
    private void addTextFieldToPane(GridBagConstraints gbc, int i, JTextField jtf) {
        gbc.gridx = i;
        centerPane.add(jtf, gbc);
    }

    // MODIFIES: centerPane
    // EFFECTS: adds labels to the centerPane
    private void addLabelToPane(GridBagConstraints gbc, int i, JLabel jl) {
        gbc.gridx = i;
        centerPane.add(jl, gbc);
    }

    // MODIFIES: centerPane
    // EFFECTS: adds buttons to the centerPane
    private void addButtonToPane(GridBagConstraints gbc, int i, JButton jb) {
        gbc.gridx = i;
        centerPane.add(jb, gbc);
    }

    // EFFECTS: sets up a list model
    private DefaultListModel createListModel() {

        CreditCard card1 = new CreditCard("1234 1234 4321 4321","Kay Sun",
                "2205 Lower Mall, Vancouver","(778)1231123", 500);
        CreditCard card2 = new CreditCard("1231 3131 4342 1235","Janice Lee",
                "1002 Happy Garden, Vancouver","(778)9871153", 3000);
        CreditCard card3 = new CreditCard("1783 3299 4992 9915","Tom Lu",
                "1188 Agronomy Road, Vancouver","(778)1133222", 10000);
        Transaction t1 = new Transaction("2020-03-07",200);
        Transaction t2 = new Transaction("2009-09-09",20);
        Transaction t3 = new Transaction("2015-03-15",100);

        cardList = new ToDoCards();
        card1.addTransactionToCard(t1);
        card1.addTransactionToCard(t2);
        card1.addTransactionToCard(t3);
//        cardList.addCard(card1);
//        cardList.addCard(card2);
//        cardList.addCard(card3);

        listModel = new DefaultListModel();
//        listModel.addElement(showCardInfo(card1));
//        listModel.addElement(showCardInfo(card2));
//        listModel.addElement(showCardInfo(card3));

        return listModel;
    }

    // EFFECTS: adds text fields to the centerPane
    private String showCardInfo(CreditCard card) {
        return "Account " + Integer.valueOf(card.getAccountNo()) + "   " + "Card No. " + card.getCreditCardNo();
    }

    // EFFECTS: show the details of each transaction
    private String showTransactionInfo(Transaction transaction) {
        return ("Date: " + transaction.getDate() + "  Amount: $"
                + transaction.getAmount() + ";");
    }

    //This listener is shared by the text field and the clear button
    class ClearListener implements ActionListener {
        private JButton button;

        public ClearListener(JButton button) {
            this.button = button;
        }

        public void actionPerformed(ActionEvent e) {
            try {
                InputStream input = new FileInputStream("./data/sound.wav");
                AudioStream as = new AudioStream(input);
                AudioPlayer.player.start(as);

            } catch (FileNotFoundException exception) {
                System.out.print("ClearListener caught FileNotFoundException!");
            } catch (IOException exception) {
                System.out.print("ClearListener caught IOException!");
            }

            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear? ",
                    "Confirm Clear", JOptionPane.YES_NO_OPTION);
            if (response == 0) {
                clearAllTextFields();
            }
        }
    }

    //This listener is shared by the text field and the save button
    class SaveListener implements ActionListener {
        private JButton button;

        public SaveListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(cardList);
                jsonWriter.close();
                System.out.println("Saved to" + JSON_STORE);
            } catch (FileNotFoundException exception) {
                System.out.println("Unable to save to " + JSON_STORE);
            }
        }
    }

    //This listener is shared by the text field and the load button
    class LoadListener implements ActionListener {
        private JButton button;

        public LoadListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            accountNum = 1;
            try {
                cardList = jsonReader.read();
                System.out.println("Loaded from " + JSON_STORE);

                listModel.clear();
                for (CreditCard card: cardList.getCreditCardsList()) {
                    card.changeAccountNo(accountNum++);
                    listModel.addElement(showCardInfo(card));
                }

               //Create the list and put it in a scroll pane.
                list = new JList(listModel);
                list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                list.setSelectedIndex(0);
                list.setVisibleRowCount(10);
                JScrollPane listScrollPane = new JScrollPane(list);
                add(listScrollPane, BorderLayout.CENTER);
            } catch (IOException exception) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
            clearButton.setEnabled(true);
        }
    }

    //This listener is shared by the text field and the search button
    class SearchListener implements ActionListener, DocumentListener {
        private CreditCard targetCard;
        private boolean alreadyEnabled = false;
        private JButton button;

        public SearchListener(JButton button) {
            this.button = button;
        }

        public void actionPerformed(ActionEvent e) {
            findTargetCard();

            TransactionList targetCardTransactionList = targetCard.getTransactionList();
            List<Transaction> resultCardTransactionList = null;

            try {
                resultCardTransactionList = targetCardTransactionList.transactionListBeforeGivenDate(date.getText());
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }

            listModel.clear();

            for (Transaction t: resultCardTransactionList) {
                listModel.addElement(showTransactionInfo(t));
            }

            //Create the list and put it in a scroll pane.
            list = new JList(listModel);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setSelectedIndex(0);
            list.setVisibleRowCount(10);
            JScrollPane listScrollPane = new JScrollPane(list);
            add(listScrollPane, BorderLayout.CENTER);

            clearAllTextFields();
            clearButton.setEnabled(true);

        }

        // EFFECTS: find the card with the given accountNo, printed a message otherwise
        public void findTargetCard() {
            int count = 1;
            for (CreditCard eachCard : cardList.getCreditCardsList()) {
                if (eachCard.getAccountNo() == Integer.valueOf(accountNo.getText())) {
                    targetCard = eachCard;
                    count++;
                } else if (count == cardList.cardListLength()) {
                    System.out.println("No such card exists!\n");
                    System.out.println();
                }
            }
        }

        //Required by DocumentListener.
        @Override
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        @Override
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }
        //Required by DocumentListener.

        @Override
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }

        //This method is required by ListSelectionListener.
//        public void valueChanged(ListSelectionEvent e) {
//            if (e.getValueIsAdjusting() == false) {
//
//                if (list.getSelectedIndex() == -1) {
//                    //No selection, disable fire button.
//                    searchButton.setEnabled(false);
//
//                } else {
//                    //Selection, enable the fire button.
//                    searchButton.setEnabled(true);
//                }
//            }
//        }
    }



    //This listener is shared by the text field and the add button.
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String cardNumber = cardNo.getText();

            //User didn't type in a unique name...
            if (name.equals("") || alreadyInList(cardNumber)) {
                Toolkit.getDefaultToolkit().beep();
                cardNo.requestFocusInWindow();
                cardNo.selectAll();
                return;
            }

            CreditCard newCard = new CreditCard(cardNo.getText(), name.getText(), address.getText(), phone.getText(),
                    Integer.valueOf(creditLimit.getText()));
            accountNum = 1;
            newCard.changeAccountNo(accountNum++);
            cardList.addCard(newCard);

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            listModel.addElement(showCardInfo(newCard));

            //Reset the text field.
            cardNo.requestFocusInWindow();
            clearAllTextFields();


            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }


        //This method tests for string equality. You could certainly
        //get more sophisticated about the algorithm.  For example,
        //you might want to ignore white space and capitalization.
        protected boolean alreadyInList(String cardNo) {
            return listModel.contains(cardNo);
        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    // EFFECTS: clear all the text fields
    private void clearAllTextFields() {
        cardNo.setText("");
        name.setText("");
        address.setText("");
        phone.setText("");
        creditLimit.setText("");
        accountNo.setText("");
        date.setText("");
    }


    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                clearButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                clearButton.setEnabled(true);
            }
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Credit Card Manager");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new CreditCartManagerGUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}

