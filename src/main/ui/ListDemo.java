package ui;

import model.CreditCard;
import model.ToDoCards;
import model.Transaction;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ListDemo extends JPanel implements ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;

    private static final String addCardString = "Add";
    private static final String saveString = "Save records";
    private static final String loadString = "Load records";
    private static final String clearString = "Clear";
    private JTextField cardNo;
    private JTextField name;
    private JTextField address;
    private JTextField phone;
    private JTextField creditLimit;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/GUIAccounts.json";

    private ToDoCards cardList;

    private JButton clearButton;

    private int accountNum = 4;

    public ListDemo() {
        super(new BorderLayout());
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        CreditCard card1 = new CreditCard("1234 1234 4321 4321","Kay Sun",
                "2205 Lower Mall, Vancouver","(778)1231123", 500);
        CreditCard card2 = new CreditCard("1231 3131 4342 1235","Janice Lee",
                "1002 Happy Garden, Vancouver","(778)9871153", 3000);
        CreditCard card3 = new CreditCard("1783 3299 4992 9915","Tom Lu",
                "1188 Agronomy Road, Vancouver","(778)1133222", 10000);
        Transaction transaction = new Transaction("2020-03-07",200);

        cardList = new ToDoCards();
        card1.addTransactionToCard(transaction);
        cardList.addCard(card1);
        cardList.addCard(card2);
        cardList.addCard(card3);

        listModel = new DefaultListModel();
        listModel.addElement(showCardInfo(card1));
        listModel.addElement(showCardInfo(card2));
        listModel.addElement(showCardInfo(card3));

        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
        JScrollPane listScrollPane = new JScrollPane(list);

        JButton addButton = new JButton(addCardString);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addCardString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);

        clearButton = new JButton(clearString);
        clearButton.setActionCommand(clearString);
        clearButton.addActionListener(new ClearListener());

        JButton saveButton = new JButton(saveString);
        SaveListener saveListener = new SaveListener(saveButton);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(saveListener);

        JButton loadButton = new JButton(loadString);
        LoadListener loadListener = new LoadListener(loadButton);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(loadListener);

        cardNo = new JTextField(10);
        cardNo.addActionListener(addListener);
        cardNo.getDocument().addDocumentListener(addListener);
//        String str = listModel.getElementAt(list.getSelectedIndex()).toString();

        name = new JTextField(10);
        name.addActionListener(addListener);
        name.getDocument().addDocumentListener(addListener);

        address = new JTextField(10);
        address.addActionListener(addListener);
        address.getDocument().addDocumentListener(addListener);

        phone = new JTextField(10);
        phone.addActionListener(addListener);
        phone.getDocument().addDocumentListener(addListener);

        creditLimit = new JTextField(10);
        creditLimit.addActionListener(addListener);
        creditLimit.getDocument().addDocumentListener(addListener);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(clearButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.HORIZONTAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(cardNo);
        buttonPane.add(name);
        buttonPane.add(address);
        buttonPane.add(phone);
        buttonPane.add(creditLimit);

        buttonPane.add(addButton);
        buttonPane.add(saveButton);
        buttonPane.add(loadButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }


    private String showCardInfo(CreditCard card) {
        return "Account " + Integer.valueOf(card.getAccountNo()) + "   " + "Card No. " + card.getCreditCardNo();
    }

    class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            cardNo.setText("");
            name.setText("");
            address.setText("");
            phone.setText("");
            creditLimit.setText("");
        }
    }

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

    class LoadListener implements ActionListener {
        private JButton button;

        public LoadListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            try {
                cardList = jsonReader.read();
                System.out.println("Loaded from " + JSON_STORE);

                listModel.clear();
                for (CreditCard card: cardList.getCreditCardsList()) {
                    listModel.addElement(showCardInfo(card));
                }

//                //Create the list and put it in a scroll pane.
                list = new JList(listModel);
                list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                list.setSelectedIndex(0);
//                list.addListSelectionListener;
                list.setVisibleRowCount(10);
                JScrollPane listScrollPane = new JScrollPane(list);
                add(listScrollPane, BorderLayout.CENTER);





            } catch (IOException exception) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }



    //This listener is shared by the text field and the hire button.
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String cardNumber = cardNo.getText();

//            User didn't type in a unique name...
            if (name.equals("") || alreadyInList(cardNumber)) {
                Toolkit.getDefaultToolkit().beep();
                cardNo.requestFocusInWindow();
                cardNo.selectAll();
                return;
            }

            CreditCard newCard = new CreditCard(cardNo.getText(), name.getText(), address.getText(), phone.getText(),
                    Integer.valueOf(creditLimit.getText()));
            cardList.addCard(newCard);

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            listModel.addElement(showCardInfo(newCard));
            //If we just wanted to add to the end, we'd do this:
            //listModel.addElement(employeeName.getText());

            //Reset the text field.
            cardNo.requestFocusInWindow();
            cardNo.setText("");
            name.setText("");
            address.setText("");
            phone.setText("");
            creditLimit.setText("");

            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }








        //https://blog.csdn.net/xietansheng/article/details/74366470
//        private void runAddCard() {
//            JFrame jf = new JFrame("Add a Credit Card");
//            jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//            JPanel accountNoPanel = new JPanel();
//            accountNoPanel.add(new JLabel("Credit card No.:"));
//            accountNoPanel.add(new JTextField(10));

//            accountNoPanel.add(new JLabel("Card holder name:"));
//            cardNo = new JTextField(10);
////            cardNo.addActionListener(addListener);
////            cardNo.getDocument().addDocumentListener(addListener);
//
//            accountNoPanel.add(new JTextField(10));
//            accountNoPanel.add(new JLabel("Address:"));
//            accountNoPanel.add(new JTextField(10));
//            accountNoPanel.add(new JLabel("Phone:"));
//            accountNoPanel.add(new JTextField(10));
//            accountNoPanel.add(new JLabel("Initial credit limit:"));
//            accountNoPanel.add(new JTextField(10));
//
//            JPanel addCardPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
//            JButton addButton = new JButton("Add");
//            AddListener addListener = new AddListener(addButton);
//            addButton.setActionCommand("Add");
//            addButton.addActionListener(addListener);
//
//            JButton clearButton = new JButton("Clear");
//            addCardPanel.add(addButton);

//            cardNo = new JTextField(10);
//            cardNo.addActionListener(addListener);
//            cardNo.getDocument().addDocumentListener(addListener);
//            String name = listModel.getElementAt(
//                    list.getSelectedIndex()).toString();
//
//            Box verticalBox = Box.createVerticalBox();
//            verticalBox.add(accountNoPanel);
//            verticalBox.add(addCardPanel);
//
//            jf.setContentPane(verticalBox);
//
//            jf.pack();
//            jf.setLocationRelativeTo(null);
//            jf.setVisible(true);
//
//        }



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
        JComponent newContentPane = new ListDemo();
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

