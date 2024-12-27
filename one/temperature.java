package one;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class temperature {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Temperature Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel inputLabel = new JLabel("Enter Temperature:", SwingConstants.CENTER);
        inputLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(inputLabel);

        JTextField temperatureField = new JTextField();
        temperatureField.setMaximumSize(new Dimension(Integer.MAX_VALUE, temperatureField.getPreferredSize().height));
        temperatureField.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(temperatureField);

        String[] units = {"Celsius", "Fahrenheit"};
        JComboBox<String> unitComboBox = new JComboBox<>(units);
        unitComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(unitComboBox);

        JButton convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(convertButton);

        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(resultLabel);

        frame.add(panel);
        frame.setVisible(true);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double temperature = Double.parseDouble(temperatureField.getText());
                    String unit = (String) unitComboBox.getSelectedItem();
                    double convertedTemperature;
                    String result;

                    if (unit.equals("Celsius")) {
                        convertedTemperature = (temperature * 9/5) + 32;
                        result = String.format("%.2f Celsius = %.2f Fahrenheit", temperature, convertedTemperature);
                    } else {
                        convertedTemperature = (temperature - 32) * 5/9;
                        result = String.format("%.2f Fahrenheit = %.2f Celsius", temperature, convertedTemperature);
                    }

                    resultLabel.setText(result);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter a valid temperature.");
                }
            }
        });
    }
}

