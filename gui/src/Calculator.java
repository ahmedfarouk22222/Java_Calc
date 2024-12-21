import javax.swing.*;
import java.awt.*;

public class Calculator {
    private char operator;
    private double total1=0.0;
    private double total2=0.0;
    private JPanel Calcularot;
    private JTextField textField1;
    private JButton btnmulty;
    private JButton btn8;
    private JButton btn2;
    private JButton btnplus;
    private JButton btn1;
    private JButton btn3;
    private JButton btn5;
    private JButton btn7;
    private JButton btn4;
    private JButton btn6;
    private JButton btnmins;
    private JButton btndiv;
    private JButton btnequal;
    private JButton btn9;
    private JButton btn0;

    private JPanel calculatorPanel;
    private JTextField textField;
    private JButton[] numberButtons = new JButton[10];
    private JButton btnPlus, btnMinus, btnMultiply, btnDivide, btnEqual;

    public Calculator() {
        initializeUI();
        initializeListeners();
    }

    private void initializeUI() {
        calculatorPanel = new JPanel();
        textField = new JTextField(10);
        calculatorPanel.add(textField);

        // Create number buttons
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            calculatorPanel.add(numberButtons[i]);
        }

        btnPlus = new JButton("+");
        btnMinus = new JButton("-");
        btnMultiply = new JButton("*");
        btnDivide = new JButton("/");
        btnEqual = new JButton("=");

        calculatorPanel.add(btnPlus);
        calculatorPanel.add(btnMinus);
        calculatorPanel.add(btnMultiply);
        calculatorPanel.add(btnDivide);
        calculatorPanel.add(btnEqual);
    }

    private void initializeListeners() {
        // Add number button listeners
        for (int i = 0; i < numberButtons.length; i++) {
            final int index = i; // To use in the listener
            numberButtons[i].addActionListener(e -> {
                String currentText = textField.getText();
                textField.setText(currentText + index);
            });
        }

        // Add operator listeners
        btnPlus.addActionListener(e -> handleOperator('+'));
        btnMinus.addActionListener(e -> handleOperator('-'));
        btnMultiply.addActionListener(e -> handleOperator('*'));
        btnDivide.addActionListener(e -> handleOperator('/'));
        btnEqual.addActionListener(e -> calculateResult());
    }

    private void handleOperator(char op) {
        operator = op;
        try {
            total1 = Double.parseDouble(textField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(calculatorPanel, "Invalid input");
            textField.setText("");
            return;
        }
        textField.setText(""); // Clear the text field for the next input
    }

    private void calculateResult() {
        try {
            total2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case '+':
                    total2 = total1 + total2;
                    break;
                case '-':
                    total2 = total1 - total2;
                    break;
                case '*':
                    total2 = total1 * total2;
                    break;
                case '/':
                    if (total2 != 0) {
                        total2 = total1 / total2;
                    } else {
                        JOptionPane.showMessageDialog(calculatorPanel, "Cannot divide by zero");
                        return;
                    }
                    break;
            }
            textField.setText(Double.toString(total2));
            total1 = 0; // Reset total1 for the next calculation
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(calculatorPanel, "Invalid input");
            textField.setText("");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().calculatorPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}