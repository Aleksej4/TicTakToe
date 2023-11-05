package Test.utils;

import Game.Board;
import Game.StartScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp(){
        StartScreen frame = new StartScreen();
        board = new Board(3, frame, "X");
    }
    @Test
    void tieGameTest() {
        board.buttons[0][0].setText("O");
        board.buttons[0][1].setText("O");
        board.buttons[0][2].setText("X");
        board.buttons[1][0].setText("X");
        board.buttons[1][1].setText("O");
        board.buttons[1][2].setText("O");
        board.buttons[2][0].setText("X");
        board.buttons[2][1].setText("X");
        board.buttons[2][2].setText("O");

        assertTrue(board.isTieGame());
    }
    @Test
    void winGameTestRow(){
        board.buttons[0][0].setText("O");
        board.buttons[0][1].setText("O");
        board.buttons[0][2].setText("O");

        assertTrue(board.checkWin("O"));
    }
    @Test
    void winGameTestColumn(){
        board.buttons[0][0].setText("O");
        board.buttons[1][0].setText("O");
        board.buttons[2][0].setText("O");

        assertTrue(board.checkWin("O"));
    }
    @Test
    void winGameTestDiagonal(){
        board.buttons[0][0].setText("O");
        board.buttons[1][1].setText("O");
        board.buttons[2][2].setText("O");

        assertTrue(board.checkWin("O"));
    }

}
