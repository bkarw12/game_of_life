package karwowski.game_of_life;

import com.googlecode.lanterna.input.*;
import com.googlecode.lanterna.terminal.*;
import org.apache.commons.cli.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Main class executed at program startup.
 * Gets input from the user and controls the Game's progress.
 */
public class Main {
    private static final String FILE_FLAG = "f";

    public static void main(String[] args) {
        int m = 0, n = 0;

        Options options = new Options();
        options.addOption(new Option(
                FILE_FLAG, "filename", true, "input filename"));

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }

        final Board board;

        // Check if input file is provided, if not then create random Board
        if (cmd.hasOption(FILE_FLAG)) {
            // Read input file
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(
                        new FileReader(cmd.getOptionValue(FILE_FLAG)));
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
            System.out.println(reader.lines().collect(Collectors.joining()));
            
            // TODO remove
            board = Board.deadBoard(5, 5);
        } else {
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
            board = Board.randomBoard(m, n);
        }

        // Initialize the terminal
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        defaultTerminalFactory.setTerminalEmulatorTitle("Game of Life");
        Terminal terminal = null;
        boolean exited = false;
        try {
            terminal = defaultTerminalFactory.createTerminal();
            Component c = (Component) terminal;
            terminal.enterPrivateMode();
            terminal.setCursorVisible(false);

            // Run the Game until the program Frame is exited
            while (!exited && c.isValid()) {
                // Render the board and instructions
                BoardRenderer.render(board, terminal);
                terminal.putString("Press ESC to exit");
                terminal.flush();
                board.nextState();
                // Check for pressing ESC to exit
                KeyStroke keyStroke = terminal.pollInput();
                if (keyStroke != null)
                    exited = keyStroke.getKeyType() == KeyType.Escape;
                Thread.sleep(100);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (terminal != null) {
                try {
                    terminal.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
