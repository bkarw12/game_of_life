package karwowski.game_of_life;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main class executed at program startup.
 * Gets input from the user and controls the Game's progress.
 */
public class Main {
    public static void main(String[] args) {
        int m = 0, n = 0;

        // Get input from user
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter board width: ");
            m = sc.nextInt();
            System.out.print("Enter board height: ");
            n = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Wrong input!");
            System.exit(1);
        }
        if (m <= 0 || n <= 0) {
            System.out.println("Input numbers should be positive!");
            System.exit(1);
        }

        // Start the game and let it run
        Board board = Board.randomBoard(m, n);

        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Terminal terminal = null;
        try {
            terminal = defaultTerminalFactory.createTerminal();
            terminal.enterPrivateMode();
            terminal.setCursorVisible(false);
            for (int i = 0; i < 10; i++) {
                BoardRenderer.renderWithLanterna(board, terminal, i);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (terminal != null) {
                try {
                    terminal.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
