package karwowski.game_of_life;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * BoardParser is responsible for parsing Board state data from input files.
 */
public class BoardParser {
    public static void parse(BufferedReader reader) throws IOException {
        int m = 0, n = 0;
        LinkedList<Integer> cells;

        String line = reader.readLine();
        if (line == null)
            throw new IOException("Input file can't be empty!");
        n = line.length();

        while (line != null) {
            m++;
            if (!line.matches("[01]+"))
                throw new IOException("Input file can only contain 0's and 1's!");
            if (line.length() != n)
                throw new IOException("Some input file lines aren't identical!");

        }
    }
}
