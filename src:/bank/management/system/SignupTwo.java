package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignupTwo extends JFrame implements ActionListener {
    JTextField idNumberTextField;
    JButton next;
    JRadioButton accYes, accNo;
    JComboBox<String> religionCombo, educationCombo, occupationCombo, idTypeCombo, incomeCombo;
    String formno;

    SignupTwo(String formno) {
        this.formno = formno;
        setLayout(null);
        setTitle("NEW ACCOUNT APPLICATION FORM - FORM 2");

        JLabel additionalDetails = new JLabel("Page 2 : Additional Details");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        additionalDetails.setBounds(290, 40, 400, 30);
        add(additionalDetails);

        JLabel religion = new JLabel("Religion:");
        religion.setFont(new Font("Raleway", Font.BOLD, 20));
        religion.setBounds(100, 100, 200, 30);
        add(religion);

        religionCombo = new JComboBox<>(new String[]{"Select Religion", "Christianity", "Islam", "Hinduism", "Sikhism", "Buddhism", "Judaism", "Atheism", "Other"});
        religionCombo.setBounds(300, 100, 400, 30);
        add(religionCombo);

        JLabel income = new JLabel("Income:");
        income.setFont(new Font("Raleway", Font.BOLD, 20));
        income.setBounds(100, 150, 200, 30);
        add(income);

        incomeCombo = new JComboBox<>(new String[]{"Select Income", "$0 - $20,000 p.a", "$20,001 - $40,000 p.a", "$40,001 - $60,000 p.a", "$60,001 - $80,000 p.a", "$80,001 - $100,000 p.a", "$100,001+ p.a"});
        incomeCombo.setBounds(300, 150, 400, 30);
        add(incomeCombo);

        JLabel education = new JLabel("Education:");
        education.setFont(new Font("Raleway", Font.BOLD, 20));
        education.setBounds(100, 200, 200, 30);
        add(education);

        educationCombo = new JComboBox<>(new String[]{"Select Education", "High School", "Diploma", "Bachelor's Degree", "Master's Degree", "Doctorate", "Other"});
        educationCombo.setBounds(300, 200, 400, 30);
        add(educationCombo);

        JLabel occupation = new JLabel("Occupation:");
        occupation.setFont(new Font("Raleway", Font.BOLD, 20));
        occupation.setBounds(100, 250, 200, 30);
        add(occupation);

        occupationCombo = new JComboBox<>(new String[]{"Select Occupation", "Student", "Software Developer", "Data Analyst", "Teacher", "Nurse", "Retail Associate", "Engineer", "Doctor", "Construction Worker", "Accountant", "Consultant", "Other"});
        occupationCombo.setBounds(300, 250, 400, 30);
        add(occupationCombo);

        JLabel idType = new JLabel("Photo ID Type:");
        idType.setFont(new Font("Raleway", Font.BOLD, 20));
        idType.setBounds(100, 300, 200, 30);
        add(idType);

        idTypeCombo = new JComboBox<>(new String[]{"Select ID Type", "Passport", "Driver's License", "Health Card", "Permanent Resident Card", "Work Permit", "Other"});
        idTypeCombo.setBounds(300, 300, 400, 30);
        add(idTypeCombo);

        JLabel idNumber = new JLabel("Photo ID Number:");
        idNumber.setFont(new Font("Raleway", Font.BOLD, 20));
        idNumber.setBounds(100, 350, 200, 30);
        add(idNumber);

        idNumberTextField = new JTextField();
        idNumberTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        idNumberTextField.setBounds(300, 350, 400, 30);
        add(idNumberTextField);

        JLabel accLabel = new JLabel("Existing Account:");
        accLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        accLabel.setBounds(100, 400, 200, 30);
        add(accLabel);

        accYes = new JRadioButton("Yes");
        accYes.setBackground(Color.WHITE);
        accYes.setBounds(300, 400, 100, 30);
        add(accYes);

        accNo = new JRadioButton("No");
        accNo.setBackground(Color.WHITE);
        accNo.setBounds(400, 400, 100, 30);
        add(accNo);

        ButtonGroup accGroup = new ButtonGroup();
        accGroup.add(accYes);
        accGroup.add(accNo);

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620, 470, 80, 30);
        next.setOpaque(true);
        next.setBorderPainted(false);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);
        setSize(850, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String religion = ((String) religionCombo.getSelectedItem()).replace("'", "\\'");
        String income = ((String) incomeCombo.getSelectedItem()).replace("'", "\\'");
        String education = ((String) educationCombo.getSelectedItem()).replace("'", "\\'");
        String occupation = ((String) occupationCombo.getSelectedItem()).replace("'", "\\'");
        String idType = ((String) idTypeCombo.getSelectedItem()).replace("'", "\\'");
        String idNumber = idNumberTextField.getText().replace("'", "\\'");
        String existingAccount = accYes.isSelected() ? "Yes" : accNo.isSelected() ? "No" : "";

        try {
            if (religion.contains("Select") || income.contains("Select") || education.contains("Select") ||
                occupation.contains("Select") || idType.contains("Select") || idNumber.equals("") || existingAccount.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill all fields properly");
                return;
            }

            Conn c = new Conn();

            // Check if formno exists in signup table
            String checkQuery = "SELECT * FROM signup WHERE formno = '" + formno + "'";
            ResultSet rs = c.s.executeQuery(checkQuery);

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Form 1 is incomplete or form number does not exist.");
                return;
            }

            // Insert into signuptwo
            String query = "INSERT INTO signuptwo (formno, religion, income, education, occupation, id_type, id_number, existing_account) " +
                    "VALUES ('" + formno + "', '" + religion + "', '" + income + "', '" + education + "', '" + occupation + "', '" + idType + "', '" + idNumber + "', '" + existingAccount + "')";
            c.s.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Form 2 submitted successfully!");
            setVisible(false);
            new SignupThree(formno).setVisible(true);  // Link to SignupThree


            // Continue to next form or logic
            // new SignupThree(formno); // if applicable

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
        }
    }

   // public static void main(String[] args) {
        // For testing only if formno "1234" already exists in 'signup' table
       // new SignupTwo("1234");
    }

