package two;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class passwordStrength {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Password Strength Checker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel inputLabel = new JLabel("Enter Password:", SwingConstants.CENTER);
        inputLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(inputLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, passwordField.getPreferredSize().height));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(passwordField);

        JButton checkButton = new JButton("Check Strength");
        checkButton.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(checkButton);

        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(resultLabel);

        frame.add(panel);
        frame.setVisible(true);

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());
                String strength = checkPasswordStrength(password);
                resultLabel.setText("Password Strength: " + strength);
            }
        });
    }

    private static String checkPasswordStrength(String password) {
        int length = password.length();
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }

        if (length >= 8 && hasUppercase && hasLowercase && hasDigit && hasSpecialChar) {
            return "Strong";
        } else if (length >= 6 && (hasUppercase || hasLowercase) && (hasDigit || hasSpecialChar)) {
            return "Moderate";
        } else {
            return "Weak";
        }
    }
}

