package karwowski.game_of_life;

import java.util.*;

/**
 * Board stores all the information about the state of the game.
 * The class is also responsible for computing the next state.
 * Rules of the Game:
 * 1. Any live cell with 0 or 1 live neighbors becomes dead, because of underpopulation
 * 2. Any live cell with 2 or 3 live neighbors stays alive, because its neighborhood is just right
 * 3. Any live cell with more than 3 live neighbors becomes dead, because of overpopulation
 * 4. Any dead cell with exactly 3 live neighbors becomes alive, by reproduction
 */
public class Board {
    /**
     * THRESHOLD value determines how many cells are alive at random initialization.
     * THRESHOLD value == 0.8 means that ~20% of the cells are going to start alive.
     */
    private static final double THRESHOLD = 0.8;

    private final int width;
    private final int height;
    private byte[][] state;

    private Board(int width, int height) {
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Board dimensions must be positive!");

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

    /**
     * Get a Board with state filled with 0's.
     * @param width width of the Board
     * @param height height of the Board
     * @return a "dead" Board
     */
    public static Board deadBoard(int width, int height) {
        return new Board(width, height);
    }

    /**
     * Get a Board randomly filled with 0's and 1's.
     * The amount of alive cells (1's) is determined by the THRESHOLD value.
     * @param width width of the Board
     * @param height height of the Board
     * @return a Board randomly filled with 0's and 1's
     */
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

    /**
     * Create a Board from initial state.
     * @param state initial state
     * @return a Board with specified initial state
     */
    public static Board fromState(byte[][] state) {
        int w = state.length;
        int h = state[0].length;

        Board b = deadBoard(w, h);
        b.state = state;

        return b;
    }

    /**
     * Move to the next state of the game.
     * The state is calculated according to the rules specified in the Board class doc.
     */
    public void nextState() {
        byte[][] newState = new byte[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Count the neighbours of the current cell.
                int neighbours = countNeighbours(i , j);

                /*
                    We only check if Rules #2 or #4 are fulfilled,
                    otherwise the cell stays (or becomes) dead.
                 */
                if (neighbours == 3 || (neighbours == 2 && state[i][j] == 1))
                    newState[i][j] = 1;
            }
        }

        state = newState;
    }

    /**
     * Count alive neighbours of the [i-th, j-th] cell.
     * @param i row of the input cell (indexed from 0)
     * @param j column of the input cell (indexed from 0)
     * @return the number of alive neighbours of the [i-th, j-th] cell
     */
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
