package two;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class fileEncryption {
    public static void main(String[] args) {
        JFrame frame = new JFrame("File Encryption/Decryption");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel fileLabel = new JLabel("Enter file path:", SwingConstants.CENTER);
        fileLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(fileLabel);

        JTextField filePathField = new JTextField();
        filePathField.setMaximumSize(new Dimension(Integer.MAX_VALUE, filePathField.getPreferredSize().height));
        filePathField.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(filePathField);

        JRadioButton encryptButton = new JRadioButton("Encrypt");
        JRadioButton decryptButton = new JRadioButton("Decrypt");
        encryptButton.setSelected(true); 
        ButtonGroup group = new ButtonGroup();
        group.add(encryptButton);
        group.add(decryptButton);

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(1, 2));
        radioPanel.add(encryptButton);
        radioPanel.add(decryptButton);
        panel.add(radioPanel);

        // Create a button to start the process
        JButton processButton = new JButton("Process");
        processButton.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(processButton);

        // Create a label to display the result
        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        panel.add(resultLabel);

        // Add the panel to the frame
        frame.add(panel);
        frame.setVisible(true);

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = filePathField.getText();
                if (filePath.isEmpty()) {
                    resultLabel.setText("Please enter a file path.");
                    return;
                }

                File file = new File(filePath);
                if (!file.exists() || !file.isFile()) {
                    resultLabel.setText("Invalid file path. Please check and try again.");
                    return;
                }

                try {
                    String content = new String(Files.readAllBytes(Paths.get(filePath)));
                    String result;
                    if (encryptButton.isSelected()) {
                        result = caesarCipher(content, 3); 
                        writeFile(filePath + ".encrypted", result);
                        resultLabel.setText("File encrypted successfully.");
                    } else {
                        result = caesarCipher(content, -3); 
                        writeFile(filePath + ".decrypted", result);
                        resultLabel.setText("File decrypted successfully.");
                    }
                } catch (IOException ioException) {
                    resultLabel.setText("Error reading or writing file. Please try again.");
                }
            }
        });
    }

    // Caesar Cipher encryption/decryption
    public static String caesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                result.append((char) ((character - base + shift + 26) % 26 + base));
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    // Write content to file
    public static void writeFile(String filePath, String content) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        }
    }
}
