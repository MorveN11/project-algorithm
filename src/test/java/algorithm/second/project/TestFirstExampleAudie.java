package algorithm.second.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test first example of the project.
 */
public class TestFirstExampleAudie {

  @Test
  void testCase1() {
    Audie audie = new Audie("docs/example-1.txt",
        7,
        2);
    String expectedOutput = """
        Guests:
        Cube Judy Kitt P X
        Groups:
        P Kitt Judy Cube
        X
        Group with strongest friendly relationship: P Kitt Judy Cube
        Group with least friendly relationship: P Kitt Judy Cube
        """;
    assertEquals(expectedOutput,
        audie.toString());
  }
}
