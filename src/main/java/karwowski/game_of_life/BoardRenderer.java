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

    public static void renderWithLanterna(Board board, Terminal terminal)
            throws IOException {
        if (board == null || terminal == null)
            throw new IllegalArgumentException(
                    "Board and Terminal can't be null when rendering!");

        final TextGraphics textGraphics = terminal.newTextGraphics();
        terminal.clearScreen();

        terminal.putString("-".repeat(board.getWidth() + 2));
        newLine(terminal);
        for (int j = 0; j < board.getHeight(); j++) {
            terminal.putCharacter('|');
            for (int i = 0; i < board.getWidth(); i++) {
                terminal.putCharacter((board.getState()[i][j] == 1) ?
                        FULL_CELL : EMPTY_CELL);
            }
            terminal.putCharacter('|');
            newLine(terminal);
        }
        terminal.putString("-".repeat(board.getWidth() + 2));
        newLine(terminal);

        terminal.flush();
    }

    private static void newLine(Terminal terminal) throws IOException {
        terminal.setCursorPosition(
                terminal.getCursorPosition().withColumn(0).withRelativeRow(1));
    }
}
