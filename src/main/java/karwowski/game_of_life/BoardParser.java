package karwowski.game_of_life;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * BoardParser is responsible for parsing Board state data from input files.
 */
public class BoardParser {
    public static Board parse(BufferedReader reader) throws IOException {
        int m = 0, n = 0;
        var cells = new LinkedList<Integer>();

        String line = reader.readLine();
        if (line == null)
            throw new IOException("Input file can't be empty!");
        n = line.length();

        while (line != null) {
            m++;
            if (!line.matches("[01]+"))
                throw new IOException("Input file can only contain 0's and 1's!");
            if (line.length() != n)
                throw new IOException("Some input file line lengths aren't identical!");

            // Line is okay, parse it
            for (char c: line.toCharArray()) {
                cells.add(Character.getNumericValue(c));
            }

            // Line parsed, read the next one
            line = reader.readLine();
        }

        // All lines parsed, create Board state
        byte[][] state = new byte[m][n];
        Iterator<Integer> cellsIterator = cells.iterator();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cellsIterator.hasNext()) {
                    state[i][j] = cellsIterator.next().byteValue();
                }
            }
        }

        return Board.fromState(state);
    }
}
