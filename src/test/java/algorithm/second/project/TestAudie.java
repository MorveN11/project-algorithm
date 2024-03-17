package algorithm.second.project;

import org.junit.jupiter.api.Test;

/**
 * Test class for App.
 */
public class TestAudie {

  @Test
  void test() {
    Audie audie = new Audie("src/test/resources/example-2.txt");
    System.out.println(audie.getGreaterThan(14));
    System.out.println(audie.getGraph());
  }
}
