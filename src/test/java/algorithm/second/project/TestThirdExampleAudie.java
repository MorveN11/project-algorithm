package algorithm.second.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test first example of the project.
 */
public class TestThirdExampleAudie {

  @Test
  void testCase1() {
    Audie audie = new Audie("docs/example-3.txt",
        14,
        4);
    String expectedOutput = """
        Guests:
        A B S
        Groups:
        It is not possible
        """;
    assertEquals(expectedOutput,
        audie.toString());
  }
}
