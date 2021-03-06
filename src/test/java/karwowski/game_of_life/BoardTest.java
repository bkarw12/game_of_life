package karwowski.game_of_life;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class BoardTest {
    Board board;

    @Test
    @DisplayName("Dead cells with no neighbours should stay dead")
    void testDeadNeighbours() {
        board = Board.deadBoard(3, 3);
        board.nextState();
        assertArrayEquals(
                board.getState(), Board.deadBoard(3, 3).getState());
    }

    @Test
    @DisplayName("Dead cells with exactly 3 neighbours should come alive")
    void test3Neighbours() {
        byte[][] initialState = {
                {0, 0, 1},
                {0, 1, 1},
                {0, 0, 0}
        };
        byte[][] expectedState = {
                {0, 1, 1},
                {0, 1, 1},
                {0, 0, 0}
        };
        board = Board.fromState(initialState);
        board.nextState();
        assertArrayEquals(
                board.getState(), expectedState
        );
    }
}
