package algorithm.second.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test the second example of the project.
 */
public class TestSecondExampleAudie {

  @Test
  void testCase1() {
    Audie audie = new Audie("docs/example-2.txt",
                            10,
                            4);
    String expectedOutput = """
            Guests:
            A B C M N R X
            Groups:
            B A
            R C M
            X
            N
            Group with strongest friendly relationship: R C M
            Group with least friendly relationship: B A
            """;
    assertEquals(expectedOutput,
                 audie.toString());
  }

  @Test
  void testCase2() {
    Audie audie = new Audie("docs/example-2.txt",
                            10,
                            3);
    String expectedOutput = """
            Guests:
            A B C M N R X
            Groups:
            B A
            R C M N
            X
            Group with strongest friendly relationship: R C M N
            Group with least friendly relationship: R C M N
            """;
    assertEquals(expectedOutput,
                 audie.toString());
  }

  @Test
  void testCase3() {
    Audie audie = new Audie("docs/example-2.txt",
                            14,
                            3);
    String expectedOutput = """
            Guests:
            C M R
            Groups:
            R
            C
            M
            Group with strongest friendly relationship: None
            Group with least friendly relationship: None
            """;
    assertEquals(expectedOutput,
                 audie.toString());
  }
}
