package az.ders.intro;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IntroUnitTests {

  @BeforeAll
  static void setupBeforeAllTests() {
    System.out.println("@BeforeAll executes before only once all tests");
    System.out.println();
  }

  @BeforeEach
  void setupBeforeEach() {
    System.out.println("@BeforeEach executes before each test");
  }

  @Test
  void test1(){
    System.out.println("Running test1");
  }

  @Test
  void test2(){
    System.out.println("Running test1");
  }

  @AfterEach
  void tearDownAfterEach() {
    System.out.println("@AfterEach executes before each test");
    System.out.println();
  }

  @AfterAll
  static void tearDownAfterAllTests() {
    System.out.println("@AfterAll executes after all tests");
  }

}
