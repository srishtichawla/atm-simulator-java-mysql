package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton oneHundred, fiveHundred, thousand, twoThousand, threeThousand, fourThousand, back;
    String pinnumber;

    FastCash(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Select Withdrawal Amount");
        text.setBounds(210, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        oneHundred = new JButton("$100");
        oneHundred.setBounds(170, 415, 150, 30);
        oneHundred.addActionListener(this);
        image.add(oneHundred);

        fiveHundred = new JButton("$500");
        fiveHundred.setBounds(355, 415, 150, 30);
        fiveHundred.addActionListener(this);
        image.add(fiveHundred);

        thousand = new JButton("$1000");
        thousand.setBounds(170, 450, 150, 30);
        thousand.addActionListener(this);
        image.add(thousand);

        twoThousand = new JButton("$2000");
        twoThousand.setBounds(355, 450, 150, 30);
        twoThousand.addActionListener(this);
        image.add(twoThousand);

        threeThousand = new JButton("$3000");
        threeThousand.setBounds(170, 485, 150, 30);
        threeThousand.addActionListener(this);
        image.add(threeThousand);

        fourThousand = new JButton("$4000");
        fourThousand.setBounds(355, 485, 150, 30);
        fourThousand.addActionListener(this);
        image.add(fourThousand);

        back = new JButton("BACK");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
            return;
        }

        String amount = ((JButton) ae.getSource()).getText().substring(1); // "$100" -> "100"

        try {
            Conn c = new Conn();

            // Calculate balance
            ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinnumber + "'");
            int balance = 0;
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

            // Check for insufficient funds
            if (balance < Integer.parseInt(amount)) {
                JOptionPane.showMessageDialog(null, "Insufficient Balance");
                return;
            }

            // Perform withdrawal
            Date date = new Date();
            String query = "INSERT INTO bank VALUES('" + pinnumber + "', '" + date + "', 'Withdraw', '" + amount + "')";
            c.s.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "$" + amount + " Withdrawal Successful");
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 //   public static void main(String[] args) {
      //  new FastCash("");
    }

