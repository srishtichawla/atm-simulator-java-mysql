package bank.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class SignupThree extends JFrame implements ActionListener {
    JRadioButton r1, r2, r3, r4;
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    JButton submit, cancel;
    String formno;

    SignupThree(String formno) {
        this.formno = formno;
        setLayout(null);

        JLabel l1 = new JLabel("Page 3: Account Details");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        l1.setBounds(280, 40, 400, 40);
        add(l1);

        JLabel type = new JLabel("Account Type");
        type.setFont(new Font("Raleway", Font.BOLD, 22));
        type.setBounds(100, 140, 400, 30);
        add(type);

        r1 = new JRadioButton("Saving Account");
        r2 = new JRadioButton("Non - Reg Saving Account");
        r3 = new JRadioButton("Chequing Account");
        r4 = new JRadioButton("Recurring Deposit Account");

        ButtonGroup groupaccount = new ButtonGroup();
        groupaccount.add(r1);
        groupaccount.add(r2);
        groupaccount.add(r3);
        groupaccount.add(r4);

        JRadioButton[] radios = {r1, r2, r3, r4};
        for (int i = 0; i < radios.length; i++) {
            radios[i].setFont(new Font("Raleway", Font.BOLD, 16));
            radios[i].setBackground(Color.WHITE);
            radios[i].setBounds(100 + (i % 2) * 250, 180 + (i / 2) * 40, 250, 20);
            add(radios[i]);
        }

        JLabel card = new JLabel("Card Number: xxxx-xxxx-xxxx-XXXX");
        card.setFont(new Font("Raleway", Font.BOLD, 16));
        card.setBounds(100, 270, 400, 30);
        add(card);

        JLabel pin = new JLabel("PIN: xxxx");
        pin.setFont(new Font("Raleway", Font.BOLD, 16));
        pin.setBounds(100, 310, 300, 30);
        add(pin);

        JLabel services = new JLabel("Services Required: ");
        services.setFont(new Font("Raleway", Font.BOLD, 22));
        services.setBounds(100, 370, 400, 20);
        add(services);

        c1 = new JCheckBox("ATM CARD");
        c2 = new JCheckBox("Mobile Banking");
        c3 = new JCheckBox("Internet Banking");
        c4 = new JCheckBox("Email & SMS Alerts");
        c5 = new JCheckBox("Cheque Book");
        c6 = new JCheckBox("E-Statement");
        c7 = new JCheckBox("I hereby declare that the above mentioned details are all correct upto my knowledge.");

        JCheckBox[] servicesBoxes = {c1, c2, c3, c4, c5, c6};
        int y = 410;
        for (int i = 0; i < servicesBoxes.length; i++) {
            servicesBoxes[i].setFont(new Font("Raleway", Font.BOLD, 16));
            servicesBoxes[i].setBackground(Color.WHITE);
            servicesBoxes[i].setBounds(100 + (i % 2) * 250, y, 200, 30);
            add(servicesBoxes[i]);
            if (i % 2 == 1) y += 40;
        }

        c7.setFont(new Font("Raleway", Font.BOLD, 12));
        c7.setBackground(Color.WHITE);
        c7.setBounds(100, y + 20, 600, 30);
        add(c7);

        submit = new JButton("SUBMIT");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Raleway", Font.BOLD, 14));
        submit.setBounds(250, y + 70, 100, 30);
        submit.setOpaque(true);
        submit.setBorderPainted(false);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("CANCEL");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Raleway", Font.BOLD, 14));
        cancel.setBounds(420, y + 70, 110, 30);
        cancel.setOpaque(true);
        cancel.setBorderPainted(false);
        cancel.addActionListener(this);
        add(cancel);

        getContentPane().setBackground(Color.WHITE);
        setSize(850, 820);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String accountType = null;

            if (r1.isSelected()) accountType = "Saving Account";
            else if (r2.isSelected()) accountType = "Non - Reg Saving Account";
            else if (r3.isSelected()) accountType = "Chequing Account";
            else if (r4.isSelected()) accountType = "Recurring Deposit Account";

            String facility = "";
            if (c1.isSelected()) facility += " ATM Card";
            if (c2.isSelected()) facility += " Mobile Banking";
            if (c3.isSelected()) facility += " Internet Banking";
            if (c4.isSelected()) facility += " Email & SMS Alerts";
            if (c5.isSelected()) facility += " Cheque Book";
            if (c6.isSelected()) facility += " E-Statement";

            if (accountType == null) {
                JOptionPane.showMessageDialog(null, "Please select an account type.");
            } else if (!c7.isSelected()) {
                JOptionPane.showMessageDialog(null, "Please confirm the declaration.");
            } else {
                try {
                    Conn conn = new Conn();

                    long cardNumber = Math.abs((long)(Math.random() * 9000000000000000L) + 1000000000000000L);
                    int pin = (int)(Math.random() * 9000) + 1000;

                    String query1 = "INSERT INTO signupthree VALUES('" + formno + "', '" + accountType + "', '" + cardNumber + "', '" + pin + "', '" + facility + "')";
                    String query2 = "INSERT INTO login VALUES('" + formno + "', '" + cardNumber + "', '" + pin + "')";
                    conn.s.executeUpdate(query1);
                    conn.s.executeUpdate(query2);

                    JOptionPane.showMessageDialog(null, "Card Number: " + cardNumber + "\nPIN: " + pin);

                    setVisible(false);
                    new Deposit("" + pin).setVisible(true);

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }

    //public static void main(String[] args) {
        // new SignupThree("1234");
    }

