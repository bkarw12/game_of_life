package karwowski.game_of_life;

import java.util.Arrays;
import java.util.Random;

public class Board {
    private static final double THRESHOLD = 0.5;

    private boolean[][] state;

    private Board(int m, int n) {
        state = new boolean[m][n];
    }

    public static Board deadBoard(int m, int n) {
        return new Board(m, n);
    }

    public static Board randomBoard(int m, int n) {
        Board b = deadBoard(m, n);
        Random r = new Random();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                b.state[i][j] = r.nextDouble() >= THRESHOLD;
            }
        }

        return b;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(state);
    }
}
