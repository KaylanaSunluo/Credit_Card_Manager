package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LabelChanger extends JFrame implements ActionListener {
    private JLabel label;
    private JTextField field;

    public LabelChanger() {
        super("Credit Cards Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(26, 26, 26, 26) );
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(new FlowLayout());
        JButton btn = new JButton("Change");
        JButton btn1 = new JButton("Change");
        JButton btn2 = new JButton("Change");
        btn.setActionCommand("myButton");
        btn.addActionListener(this); // Sets "this" object as an action listener for btn
        // so that when the btn is clicked,
        // this.actionPerformed(ActionEvent e) will be called.
        // You could also set a different object, if you wanted
        // a different object to respond to the button click
        label = new JLabel("flag");
        field = new JTextField(5);
        add(field);
        add(btn);
        add(btn1);
        add(btn2);
        add(label);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //This is the method that is called when the the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton")) {
            label.setText(field.getText());
        }
    }

    public static void main(String[] args) {
        new LabelChanger();
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



}

