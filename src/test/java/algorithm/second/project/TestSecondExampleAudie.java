package algorithm.second.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test the second example of the project.
 */
public class TestSecondExampleAudie {

  @Test
  void testCase1() {
    Audie audie = new Audie("docs/example-2.txt");
    int x = 10;
    int k = 4;
    String expectedGuests = """
            Guests:
            A B C M N R X""";
    String guests = audie.getGreaterThan(x);
    assertEquals(expectedGuests,
                 guests);
    String expectedGroups = """
            Groups:
            B A
            R C M
            X
            N""";
    String groups = audie.getGroupsOf(k);
    assertEquals(expectedGroups,
                 groups);
    String expectedStrongestRelation = """
            Group with strongest friendly relationship: R C M""";
    String strongestRelation = audie.getStrongestRelation();
    assertEquals(expectedStrongestRelation,
                 strongestRelation);
    String expectedWeakestRelation = """
            Group with least friendly relationship: B A""";
    String weakestRelation = audie.getWeakestRelation();
    assertEquals(expectedWeakestRelation,
                 weakestRelation);
  }

  @Test
  void testCase2() {
    Audie audie = new Audie("docs/example-2.txt");
    int x = 10;
    int k = 3;
    String expectedGuests = """
            Guests:
            A B C M N R X""";
    String guests = audie.getGreaterThan(x);
    assertEquals(expectedGuests,
                 guests);
    String expectedGroups = """
            Groups:
            B A
            R C M N
            X""";
    String groups = audie.getGroupsOf(k);
    assertEquals(expectedGroups,
                 groups);
    String expectedStrongestRelation = """
            Group with strongest friendly relationship: R C M N""";
    String strongestRelation = audie.getStrongestRelation();
    assertEquals(expectedStrongestRelation,
                 strongestRelation);
    String expectedWeakestRelation = """
            Group with least friendly relationship: R C M N""";
    String weakestRelation = audie.getWeakestRelation();
    assertEquals(expectedWeakestRelation,
                 weakestRelation);
  }

  @Test
  void testCase3() {
    Audie audie = new Audie("docst/example-2.txt");
    int x = 14;
    int k = 3;
    String expectedGuests = """
            Guests:
            C M R""";
    String guests = audie.getGreaterThan(x);
    assertEquals(expectedGuests,
                 guests);
    String expectedGroups = """
            Groups:
            R
            C
            M""";
    String groups = audie.getGroupsOf(k);
    assertEquals(expectedGroups,
                 groups);
    String expectedStrongestRelation = """
            Group with strongest friendly relationship: None""";
    String strongestRelation = audie.getStrongestRelation();
    assertEquals(expectedStrongestRelation,
                 strongestRelation);
    String expectedWeakestRelation = """
            Group with least friendly relationship: None""";
    String weakestRelation = audie.getWeakestRelation();
    assertEquals(expectedWeakestRelation,
                 weakestRelation);
  }
}
