package az.ders.fizzbuzz;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FizzBuzzTest {

  @Test
  @Order(1)
  void testForDivisibleByThree() {
    assertEquals("Fizz", FizzBuzz.compute(3), "Should return Fizz");
  }

  @Test
  @Order(2)
  void testForDivisibleByFive() {
    assertEquals("Buzz", FizzBuzz.compute(5), "Should return Buzz");
  }

  @Test
  @Order(3)
  void testForDivisibleByThreeAndFive() {
    assertEquals("FizzBuzz", FizzBuzz.compute(15), "Should return FizzBuzz");
  }

  @Test
  @Order(4)
  void testForNotDivisibleByThreeOrFive() {
    assertEquals("1", FizzBuzz.compute(1), "Should return 1");
  }

  @DisplayName("Testing with Small data file")
  @ParameterizedTest(name = "value={0}, expected={1}")
  @CsvFileSource(resources = "/small-test-data.csv")
  @Order(5)
  void testSmallDataFile(int value, String expected) {
    assertEquals(expected, FizzBuzz.compute(value));
  }

  @DisplayName("Testing with Medium data file")
  @ParameterizedTest(name = "value={0}, expected={1}")
  @CsvFileSource(resources = "/medium-test-data.csv")
  @Order(6)
  void testMediumDataFile(int value, String expected) {
    assertEquals(expected, FizzBuzz.compute(value));
  }

  @DisplayName("Testing with Large data file")
  @ParameterizedTest(name = "value={0}, expected={1}")
  @CsvFileSource(resources = "/large-test-data.csv")
  @Order(7)
  void testLargeDataFile(int value, String expected) {
    assertEquals(expected, FizzBuzz.compute(value));
  }

}
