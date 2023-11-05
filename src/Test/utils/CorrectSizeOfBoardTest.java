package Test.utils;

import Game.utils.CorrectSizeOfBoard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class CorrectSizeOfBoardTest {

    private CorrectSizeOfBoard underTest;

    @BeforeEach
    void setUp(){
        underTest = new CorrectSizeOfBoard();
    }

    @Test
    void itShouldValidateSizeOfBoard(){
        String size = "3";
        boolean isValid = underTest.test(size);
        assertTrue(isValid);

    }

    @Test
    void itShouldFailWhenSizeOfBoardIsSmallerThan3(){
        String size = "2";
        boolean isValid = underTest.test(size);
        assertFalse(isValid);

    }

    @Test
    void itShouldFailWhenSizeOfBoardIsNotNumber(){
        String size = "3as";
        boolean isValid = underTest.test(size);
        assertFalse(isValid);

    }
}