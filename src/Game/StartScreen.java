package Game;

import Game.utils.CorrectSizeOfBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame{
    private JRadioButton playerX;
    private JRadioButton playerO;
    private final CorrectSizeOfBoard correctSizeOfBoard = new CorrectSizeOfBoard();
    public StartScreen() {
        setScreen();
        setComponents();
        setVisible(true);
    }

    void setScreen() {
        setSize(400, 150);
        setLocationRelativeTo(null);
        setTitle("Tik Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
    }


    void setComponents() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter the size of the board (minimum is 3):");
        JTextField textField = new JTextField(3);

        panel.add(label);
        panel.add(textField);
        panel.add(button(textField));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0); // No padding

        add(panel, gbc);

        // Create a separate panel for radio buttons
        JPanel radioButtonPanel = new JPanel();
        playerX = new JRadioButton("X");
        playerO = new JRadioButton("O");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(playerX);
        buttonGroup.add(playerO);

        radioButtonPanel.add(playerO);
        radioButtonPanel.add(playerX);

        GridBagConstraints radioButtonsGbc = new GridBagConstraints();
        radioButtonsGbc.gridx = 0;
        radioButtonsGbc.gridy = 1; // Place radio buttons on the next line
        radioButtonsGbc.insets = new Insets(0, 0, 10, 0); // No padding

        add(radioButtonPanel, radioButtonsGbc);
    }

    JButton button(JTextField textField) {
        JButton button = new JButton("Play");

        button.addActionListener(e -> {
            String temp = textField.getText();

            if (!correctSizeOfBoard.test(temp)) {
                JOptionPane.showMessageDialog(this, "Entered number is invalid. Please enter a valid number greater than or equal to 3.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int size = Integer.parseInt(temp);
            String selectedName = getSelectedRadioButtonName();

            if (selectedName != null) {
                Board board = new Board(size, StartScreen.this, selectedName);
            } else {
                System.out.println("No player selected");
            }


        });

        return button;
    }

    private String getSelectedRadioButtonName() {
        if (playerX.isSelected()) {
            return playerX.getText();
        } else if (playerO.isSelected()) {
            return playerO.getText();
        } else {
            return null;
        }
    }

}
