package az.ders.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculatorTest {

  private Calculator calculator;

  @BeforeEach
  void setUp() {
    calculator = new Calculator();
  }

  @Test
  void testAddition() {
    int result = calculator.add(3, 7);
    assertEquals(10, result, "3 + 7 should be 10");
  }

  @ParameterizedTest
  @CsvSource({"2,3,5", "5,5,10", "10,15,25"})
  void testAdditionParameterized(int a, int b, int expected) {
    assertEquals(expected, calculator.add(a, b));
  }

  @Test
  void testDivideByPositive() {
    assertEquals(2, calculator.divide(10, 5));
  }

  @Test
  void testDivideByNegative() {
    assertEquals(-2, calculator.divide(10, -5));
  }

  @Test
  void testDivideByZero() {
    Exception exception = assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    assertEquals("Cannot divide by zero", exception.getMessage());
  }

  @Test
  void testMultiplyWithZero() {
    assertEquals(0, calculator.multiply(10, 0), "Multiplication with zero should be zero");
  }

  @RepeatedTest(5)
  void testMultiplyWithPositive() {
    assertEquals(100, calculator.multiply(10, 10), "Multiplication with zero should be zero");
  }

}