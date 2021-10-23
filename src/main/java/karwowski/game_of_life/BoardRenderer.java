package karwowski.game_of_life;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

/**
 * BoardRenderer pretty-prints the Board's state.
 */
public class BoardRenderer {
    private static final char EMPTY_CELL = ' ';
    private static final char FULL_CELL = '#';

    public static void render(Board board) {
        System.out.println("-".repeat(board.getWidth() + 2));
        for (int j = 0; j < board.getHeight(); j++) {
            System.out.print("|");
            for (int i = 0; i < board.getWidth(); i++) {
                System.out.print((board.getState()[i][j] == 1) ?
                        FULL_CELL : EMPTY_CELL);
            }
            System.out.println("|");
        }
        System.out.println("-".repeat(board.getWidth() + 2));
    }

    public static void renderWithLanterna(Board board, Terminal terminal, int i)
            throws IOException {
        final TextGraphics textGraphics = terminal.newTextGraphics();
        terminal.clearScreen();
        textGraphics.putString(terminal.getCursorPosition(), "Hello World! " + i);
        terminal.setCursorPosition(
                terminal.getCursorPosition().withColumn(0).withRelativeRow(1));
        textGraphics.putString(terminal.getCursorPosition(), "Hello World! " + i);
        terminal.flush();
    }
}
