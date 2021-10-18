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

    public static Board fromState(byte[][] state) {
        int w = state.length;
        int h = state[0].length;

        Board b = deadBoard(w, h);
        b.state = state;

        return b;
    }

    public void nextState() {
        byte[][] newState = new byte[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int neighbours = countNeighbours(i , j);

                if (neighbours == 3 || (neighbours == 2 && state[i][j] == 1))
                    newState[i][j] = 1;
            }
        }

        state = newState;
    }

    private int countNeighbours(int i, int j) {
        int count = 0;
        for (int k = i - 1; k <= i + 1; k++) {
            for (int l = j - 1; l <= j + 1; l++) {
                if (k >= 0 && k < width && l >= 0 && l < height)
                    count += state[k][l];
            }
        }
        return count - state[i][j];
    }

    @Override
    public String toString() {
        return Arrays.deepToString(state);
    }
}
