package karwowski.game_of_life;

public class BoardRenderer {
    private static final char EMPTY_CELL = ' ';
    private static final char FULL_CELL = '#';

    public static void render(Board board) {
        System.out.println("-".repeat(board.getWidth() + 2));
        for (int j = 0; j < board.getHeight(); j++) {
            System.out.print("|");
            for (int i = 0; i < board.getWidth(); i++) {
                System.out.print((board.getState()[i][j] == 1) ?
                        FULL_CELL : EMPTY_CELL);
            }
            System.out.println("|");
        }
        System.out.println("-".repeat(board.getWidth() + 2));
    }
}
