package karwowski.game_of_life;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.*;

import javax.swing.*;
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

        // Create new random Board
        Board board = Board.randomBoard(m, n);

        // Initialize the terminal
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Terminal terminal = null;
        boolean exited = false;
        try {
            terminal = defaultTerminalFactory.createTerminal();
            terminal.enterPrivateMode();
            terminal.setCursorVisible(false);
            while (!exited) {
                BoardRenderer.renderWithLanterna(board, terminal);
                terminal.putString("Press ESC to exit");
                terminal.flush();
                board.nextState();
                KeyStroke keyStroke = terminal.pollInput();
                if (keyStroke != null)
                    exited = keyStroke.getKeyType() == KeyType.Escape;
                Thread.sleep(100);
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
