package karwowski.game_of_life;

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
        BoardRenderer.render(board);
        board.nextState();
        BoardRenderer.render(board);
        board.nextState();
        BoardRenderer.render(board);
    }
}
