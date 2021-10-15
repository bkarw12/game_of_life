package karwowski.game_of_life;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int m = 0, n = 0;

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

        Board board = Board.randomBoard(m, n);
        System.out.println(board);
    }
}
