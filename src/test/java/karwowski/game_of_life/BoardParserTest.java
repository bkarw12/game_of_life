package karwowski.game_of_life;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.io.*;

public class BoardParserTest {
    private static final String INPUT_PATH = "input/";

    BufferedReader reader;
    Board board;

    @Test
    @DisplayName("Good input gets parsed and accepted")
    void testGoodInput() {
        try {
            byte[][] expectedState = {
                    {0, 0, 0, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0}
            };

            reader = new BufferedReader(new FileReader(INPUT_PATH + "good.txt"));
            board = BoardParser.parse(reader);
            assertArrayEquals(board.getState(), expectedState);
        } catch (Exception e) {
            fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Bad input with other characters than 0's/1's throws an exception")
    void testBadInputText() {
        try {
            reader = new BufferedReader(new FileReader(INPUT_PATH + "bad.txt"));
            board = BoardParser.parse(reader);
            fail("Test failed: expected IOException: "
                    + BoardParser.WRONG_CHARACTER_INPUT);
        } catch (IOException e) {
            assertEquals(e.getMessage(), BoardParser.WRONG_CHARACTER_INPUT);
        }
    }

    @Test
    @DisplayName("Bad input with wrong uneven row lenghts throws an exception")
    void testBadInputRowLength() {
        try {
            reader = new BufferedReader(new FileReader(INPUT_PATH + "bad2.txt"));
            board = BoardParser.parse(reader);
            fail("Test failed: expected IOException: "
                    + BoardParser.WRONG_ROW_LENGTH_INPUT);
        } catch (IOException e) {
            assertEquals(e.getMessage(), BoardParser.WRONG_ROW_LENGTH_INPUT);
        }
    }
}
