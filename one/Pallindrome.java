package one;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pallindrome {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Palindrome Checker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300); // Increase the size of the frame

        // Create a panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the panel

        // Create a label for input
        JLabel inputLabel = new JLabel("Enter your words:", SwingConstants.CENTER);
        inputLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Increase the font size of the label

        // Add hover effect to the label
        inputLabel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                inputLabel.setForeground(Color.BLUE);
            }

            public void mouseExited(MouseEvent evt) {
                inputLabel.setForeground(Color.BLACK);
            }
        });
        panel.add(inputLabel);

        // Create a text field for input
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14)); // Increase the font size of the text field

        // Add hover effect to the text field
        textField.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                textField.setBackground(Color.LIGHT_GRAY);
            }

            public void mouseExited(MouseEvent evt) {
                textField.setBackground(Color.WHITE);
            }
        });
        panel.add(textField);

        // Create a button to check palindrome
        JButton button = new JButton("Check");
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Increase the font size of the button

        // Add hover effect to the button
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(Color.GRAY);
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(UIManager.getColor("control"));
            }
        });
        panel.add(button);

        // Create a label to display the result
        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Increase the font size of the result label
        panel.add(resultLabel);

        // Add the panel to the frame
        frame.add(panel);
        frame.setVisible(true);

        // Add action listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText();
                if (isPalindrome(input)) {
                    resultLabel.setText("The input is a palindrome.");
                    resultLabel.setForeground(Color.GREEN); // Set the text color to green for positive result
                } else {
                    resultLabel.setText("The input is not a palindrome.");
                    resultLabel.setForeground(Color.RED); // Set the text color to red for negative result
                }
            }
        });
    }

    public static boolean isPalindrome(String input) {
        // Remove non-alphanumeric characters and convert to lower case
        String cleanedInput = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Check if the cleaned input is the same forwards and backwards
        int left = 0;
        int right = cleanedInput.length() - 1;

        while (left < right) {
            if (cleanedInput.charAt(left) != cleanedInput.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
