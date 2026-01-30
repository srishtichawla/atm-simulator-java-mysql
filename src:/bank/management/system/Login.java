package bank.management.system;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    JTextField cardTextField;
    JButton login, clear, signup;
    JPasswordField pinTextField;

    Login() {
        setTitle("Automated Teller Machine");
        setLayout(null);

        // Logo
        ImageIcon i1 = new ImageIcon(getClass().getResource("/bank/management/system/icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);

        // Welcome Text
        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("OSWARD", Font.BOLD, 38));
        text.setBounds(200, 40, 500, 40);
        add(text);

        // Card No.
        JLabel cardno = new JLabel("Card No.: ");
        cardno.setFont(new Font("Raleway", Font.BOLD, 28));
        cardno.setBounds(120, 150, 150, 30);
        add(cardno);

        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 230, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);

        // PIN
        JLabel pin = new JLabel("PIN: ");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(120, 220, 150, 30);
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 230, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);

        // SIGN IN Button
        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        styleButton(login);
        login.addActionListener(this);       
        add(login);

        // CLEAR Button
        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        styleButton(clear);
        clear.addActionListener(this);
        add(clear);

        // SIGN UP Button
        signup = new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
        styleButton(signup);
        signup.addActionListener(this);
        add(signup);

        // Background color
        getContentPane().setBackground(Color.WHITE);

        // Frame settings
        setSize(1000, 680);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    // Button styling method (makes them black with white text)
    private void styleButton(JButton button) {
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear) {
            cardTextField.setText("");
            pinTextField.setText("");
        } else if (ae.getSource() == login) {
            String cardnumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();
            
            try {
                Conn conn = new Conn();
                String query = "SELECT * FROM cards WHERE card_number = '" + cardnumber + "' AND pin = '" + pinnumber + "'";
                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or Pin");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == signup) {
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }

    public static void main(String args[]) {
        new Login();
    }
}
