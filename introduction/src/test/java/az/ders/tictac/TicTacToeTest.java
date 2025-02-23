package az.ders.tictac;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicTacToeTest {

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

  @Test
  void whenPlayAndWholeHorizontalLineThenWinner() {
    ticTacToe.play(1, 1); // X
    ticTacToe.play(1, 2); // O
    ticTacToe.play(2, 1); // X
    ticTacToe.play(2, 2); // O
    String actual = ticTacToe.play(3, 1); // X
    assertEquals("X is the winner", actual);
  }

  @Test
  void whenPlayAndWholeVerticalLineThenWinner() {
    ticTacToe.play(2, 1); // X
    ticTacToe.play(1, 1); // O
    ticTacToe.play(3, 1); // X
    ticTacToe.play(1, 2); // O
    ticTacToe.play(2, 2); // X
    String actual = ticTacToe.play(1, 3); // O
    assertEquals("O is the winner", actual);
  }

  @Test
  void whenPlayAndTopBottomDiagonalLineThenWinner() {
    ticTacToe.play(1, 1); // X
    ticTacToe.play(1, 2); // O
    ticTacToe.play(2, 2); // X
    ticTacToe.play(1, 3); // O
    String actual = ticTacToe.play(3, 3); // X
    assertEquals("X is the winner", actual);
  }

  @Test
  void whenPlayAndBottomTopDiagonalLineThenWinner() {
    ticTacToe.play(1, 3); // X
    ticTacToe.play(1, 1); // O
    ticTacToe.play(2, 2); // X
    ticTacToe.play(1, 2); // O
    String actual = ticTacToe.play(3, 1); // X
    assertEquals("X is the winner", actual);
  }

  @Test
  void whenAllBoxesAreFilledThenDraw() {
    ticTacToe.play(1, 1);
    ticTacToe.play(1, 2);
    ticTacToe.play(1, 3);
    ticTacToe.play(2, 1);
    ticTacToe.play(2, 3);
    ticTacToe.play(2, 2);
    ticTacToe.play(3, 1);
    ticTacToe.play(3, 3);
    String actual = ticTacToe.play(3, 2);
    assertEquals("The result is draw", actual);
  }
}