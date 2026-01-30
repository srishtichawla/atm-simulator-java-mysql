package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MiniStatement extends JFrame implements ActionListener {

    String pinnumber;
    JButton exit;

    MiniStatement(String pinnumber) {
        this.pinnumber = pinnumber;

        setTitle("Mini Statement");
        setLayout(null);

        JLabel bank = new JLabel("Canadian Bank");
        bank.setBounds(120, 20, 300, 30);
        bank.setFont(new Font("System", Font.BOLD, 20));
        add(bank);

        JLabel card = new JLabel();
        card.setBounds(20, 60, 400, 20);
        card.setFont(new Font("Raleway", Font.PLAIN, 14));
        add(card);

        JLabel mini = new JLabel();
        mini.setFont(new Font("Raleway", Font.PLAIN, 14));

        // Wrap in JScrollPane
        JScrollPane scrollPane = new JScrollPane(mini);
        scrollPane.setBounds(20, 90, 360, 250);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);

        JLabel balanceLabel = new JLabel();
        balanceLabel.setBounds(20, 350, 300, 20);
        balanceLabel.setFont(new Font("Raleway", Font.BOLD, 14));
        add(balanceLabel);

        exit = new JButton("Exit");
        exit.setBounds(140, 380, 100, 30);
        exit.setFont(new Font("System", Font.BOLD, 14));
        exit.addActionListener(this);
        add(exit);

        try {
            Conn conn = new Conn();

            // Show Card Number
            ResultSet rsCard = conn.s.executeQuery("SELECT * FROM cards WHERE pin = '" + pinnumber + "'");
            while (rsCard.next()) {
                card.setText("Card Number: " + rsCard.getString("card_number"));
            }

            // Fetch Transactions & Calculate Balance
            ResultSet rs = conn.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinnumber + "'");
            int bal = 0;
            StringBuilder statement = new StringBuilder("<html>");
            while (rs.next()) {
                String type = rs.getString("type");
                int amount = Integer.parseInt(rs.getString("amount"));

                statement.append(rs.getString("date")).append("&nbsp;&nbsp;&nbsp;&nbsp;")
                         .append(type).append("&nbsp;&nbsp;&nbsp;&nbsp;$")
                         .append(amount).append("<br><br>");

                if (type.equals("Deposit")) {
                    bal += amount;
                } else {
                    bal -= amount;
                }
            }
            statement.append("</html>");
            mini.setText(statement.toString());
            balanceLabel.setText("Current Balance: $" + bal);

        } catch (Exception e) {
            e.printStackTrace();
        }

        setSize(400, 480);
        setLocationRelativeTo(null); // center of screen
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(pinnumber).setVisible(true);
    }
}
