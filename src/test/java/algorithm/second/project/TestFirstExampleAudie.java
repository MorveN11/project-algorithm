package algorithm.second.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test first example of the project.
 */
public class TestFirstExampleAudie {

  @Test
  void testCase1() {
    Audie audie = new Audie("docs/example-1.txt");
    int x = 7;
    int k = 2;
    String expectedGuests = """
            Guests:
            Cube Judy Kitt P X""";
    String guests = audie.getGreaterThan(x);
    assertEquals(expectedGuests,
                 guests);
    String expectedGroups = """
            Groups:
            P Kitt Judy Cube
            X""";
    String groups = audie.getGroupsOf(k);
    assertEquals(expectedGroups,
                 groups);
    String expectedStrongestRelation = """
            Group with strongest friendly relationship: P Kitt Judy Cube""";
    String strongestRelation = audie.getStrongestRelation();
    assertEquals(expectedStrongestRelation,
                 strongestRelation);
    String expectedWeakestRelation = """
            Group with least friendly relationship: P Kitt Judy Cube""";
    String weakestRelation = audie.getWeakestRelation();
    assertEquals(expectedWeakestRelation,
                 weakestRelation);
  }
}
