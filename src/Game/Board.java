package Game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Board extends JFrame {
    private final String player;
    private String computer;
    private JLabel winnerLabel;
    public JButton[][] buttons;
    private final int size;

    public Board(int size, StartScreen frame, String player) {
        this.player = player;
        this.size = size;
        initialize(frame);
        createUI();
        setVisible(true);
    }

    private void initialize(StartScreen frame) {
        frame.setVisible(false);
        setSize(400, 400);
        setLocationRelativeTo(null);
        createButtons(frame);
        createTopPanel();
    }

    private void createUI() {
        setTitle("Tic-Tac-Toe");
    }

    private void createTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton startButton = new JButton("Start");
        winnerLabel = new JLabel("");
        topPanel.add(startButton);
        topPanel.add(winnerLabel);

        startButton.addActionListener(e -> {
            clearBoard();
            if (goesFirst()) {
                computer = "X";
            } else {
                computer = "O";
                computerMove();
            }
        });

        getContentPane().add(topPanel, BorderLayout.NORTH);
    }

    private void clearBoard() {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                buttons[row][column].setEnabled(true);
                buttons[row][column].setText("");
            }
        }
        winnerLabel.setText("");
    }

    private boolean goesFirst() {
        return player.equals("O");
    }

    private boolean isBoardFull() {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if (buttons[row][column].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void computerMove() {
        if (isBoardFull()) {
            return;
        }

        Random random = new Random();
        int row, column;
        do {
            row = random.nextInt(size);
            column = random.nextInt(size);
        } while (!buttons[row][column].getText().isEmpty());

        buttons[row][column].setText(computer);
        buttons[row][column].setEnabled(false);

        if (checkWin(computer)) {
            stopGame();
        }
    }

    public boolean checkWin(String symbol) {
        int symbolsToWin = 3;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (isWinningLine(symbol, row, col, 0, 1, symbolsToWin) ||
                        isWinningLine(symbol, row, col, 1, 0, symbolsToWin) ||
                        isWinningLine(symbol, row, col, 1, 1, symbolsToWin) ||
                        isWinningLine(symbol, row, col, 1, -1, symbolsToWin)) {
                    winnerLabel.setText("Winner: " + symbol);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isWinningLine(String symbol, int startRow, int startCol, int rowIncrement, int colIncrement, int symbolsToWin) {
        int consecutiveSymbols = 0;
        for (int i = 0; i < size; i++) {
            int row = startRow + i * rowIncrement;
            int col = startCol + i * colIncrement;
            if (row >= 0 && row < size && col >= 0 && col < size) {
                if (buttons[row][col].getText().equals(symbol)) {
                    consecutiveSymbols++;
                    if (consecutiveSymbols == symbolsToWin) {
                        return true;
                    }
                } else {
                    consecutiveSymbols = 0;
                }
            }
        }
        return false;
    }

    private void stopGame() {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                buttons[row][column].setEnabled(false);
            }
        }
    }

    private void createButtons(StartScreen frame) {
        buttons = new JButton[size][size];

        JPanel panel = new JPanel(new GridLayout(size, size));

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                buttons[row][column] = new JButton();
                buttons[row][column].setEnabled(false);
                int finalRow = row;
                int finalColumn = column;
                buttons[row][column].addActionListener(e -> {
                    buttons[finalRow][finalColumn].setText(player);
                    buttons[finalRow][finalColumn].setEnabled(false);

                    if (checkWin(player)) {

                        stopGame();
                    } else {
                        computerMove();
                    }
                    if(isTieGame())
                        stopGame();

                });
                panel.add(buttons[row][column]);
            }
        }
        add(panel);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frame.setVisible(true);
            }
        });
    }

    public boolean isTieGame() {
        if (isBoardFull() && !checkWin(player) && !checkWin(computer)) {
            winnerLabel.setText("It's a tie!");
            return true;
        }
        return false;
    }

}
