package karwowski.game_of_life;

import java.util.Arrays;
import java.util.Random;

public class Board {
    private static final double THRESHOLD = 0.9;

    private final int width;
    private final int height;
    private byte[][] state;

    private Board(int width, int height) {
        this.width = width;
        this.height = height;
        state = new byte[width][height];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public byte[][] getState() {
        return state;
    }

    public static Board deadBoard(int width, int height) {
        return new Board(width, height);
    }

    public static Board randomBoard(int width, int height) {
        Board b = deadBoard(width, height);
        Random r = new Random();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
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
