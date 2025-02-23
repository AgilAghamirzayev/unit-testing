package az.ders.tictac;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TicTacToeParamTest {

  private TicTacToe ticTacToe;

  @BeforeEach
  void setUp() {
    ticTacToe = new TicTacToe();
  }

  @Test
  void whenXOutsideBoardThenRuntimeException() {
    assertThrows(RuntimeException.class, () -> ticTacToe.play(5, 2));
  }

  @Test
  void whenYOutsideBoardThenRuntimeException() {
    assertThrows(RuntimeException.class, () -> ticTacToe.play(2, 5));
  }

  @Test
  void whenOccupiedThenRuntimeException() {
    ticTacToe.play(2, 1);
    assertThrows(RuntimeException.class, () -> ticTacToe.play(2, 1));
  }

  @Test
  void givenFirstTurnWhenNextPlayerThenX() {
    assertEquals('X', ticTacToe.nextPlayer());
  }

  @Test
  void givenLastTurnWasXWhenNextPlayerThenO() {
    ticTacToe.play(1, 1);
    assertEquals('O', ticTacToe.nextPlayer());
  }

  @Test
  void whenPlayThenNoWinner() {
    String actual = ticTacToe.play(1, 1);
    assertEquals("No winner", actual);
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("winningScenarios")
  void whenPlayAndWinningConditionThenWinner(String description, int[][] moves,
                                             String expectedWinner) {
    String actualWinner = "";
    for (int[] move : moves) {
      actualWinner = ticTacToe.play(move[0], move[1]);
    }
    assertEquals(expectedWinner, actualWinner);
  }

  static Stream<Arguments> winningScenarios() {
    return Stream.of(
        Arguments.of("Horizontal win for X",
            new int[][] {{1, 1}, {2, 1}, {1, 2}, {2, 2}, {1, 3}}, "X is the winner"),
        Arguments.of("Vertical win for O",
            new int[][] {{2, 1}, {1, 1}, {3, 1}, {1, 2}, {2, 2}, {1, 3}}, "O is the winner"),
        Arguments.of("Top-left to bottom-right diagonal win for X",
            new int[][] {{1, 1}, {2, 1}, {2, 2}, {2, 3}, {3, 3}}, "X is the winner"),
        Arguments.of("Bottom-left to top-right diagonal win for X",
            new int[][] {{1, 3}, {2, 1}, {2, 2}, {1, 2}, {3, 1}}, "X is the winner")
    );
  }

  @ParameterizedTest
  @MethodSource("drawScenarios")
  void whenAllBoxesAreFilledThenDraw(int[][] moves, String expectedResult) {
    String actualResult = "";

    for (int[] move : moves) {
      actualResult = ticTacToe.play(move[0], move[1]);
    }
    assertEquals(expectedResult, actualResult);
  }

  static Stream<Arguments> drawScenarios() {
    return Stream.of(
        Arguments.of(new int[][] {
            {1, 1}, {2, 1}, {3, 1},
            {1, 2}, {2, 3}, {2, 2},
            {1, 3}, {3, 3}, {3, 2}
        }, "The result is draw")
    );
  }

}