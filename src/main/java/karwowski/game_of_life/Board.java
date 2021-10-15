package karwowski.game_of_life;

import java.util.Arrays;
import java.util.Random;

public class Board {
    private static final double THRESHOLD = 0.5;

    private byte[][] state;

    private Board(int m, int n) {
        state = new byte[m][n];
    }

    public static Board deadBoard(int m, int n) {
        return new Board(m, n);
    }

    public static Board randomBoard(int m, int n) {
        Board b = deadBoard(m, n);
        Random r = new Random();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                b.state[i][j] = (byte) ((r.nextDouble() >= THRESHOLD) ? 1 : 0);
            }
        }

        return b;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(state);
    }
}
