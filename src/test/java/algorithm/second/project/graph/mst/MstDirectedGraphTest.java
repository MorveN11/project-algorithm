package algorithm.second.project.graph.mst;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import algorithm.second.project.graph.DirectedGraph;
import algorithm.second.project.graph.Graph;
import algorithm.second.project.graph.classes.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MstDirectedGraphTest {

  private static final Node<Integer> node1 = new Node<>(1);

  private static final Node<Integer> node2 = new Node<>(2);

  private static final Node<Integer> node3 = new Node<>(3);

  private static final Node<Integer> node4 = new Node<>(4);

  private static final Node<Integer> node5 = new Node<>(5);

  private static final Node<Integer> node6 = new Node<>(6);

  private static Graph<Integer> graph;

  @BeforeEach
  void setup() {
    graph = new DirectedGraph<>();
    assertTrue(graph.addEdge(3,
        node1,
        node2));
    assertTrue(graph.addEdge(5,
        node1,
        node5));
    assertTrue(graph.addEdge(2,
        node5,
        node6));
    assertTrue(graph.addEdge(7,
        node6,
        node4));
    assertTrue(graph.addEdge(9,
        node4,
        node3));
    assertTrue(graph.addEdge(5,
        node2,
        node3));
    assertTrue(graph.addEdge(6,
        node2,
        node5));
    assertTrue(graph.addEdge(3,
        node3,
        node6));
  }

  @Test
  void testKruskalAlgorithm() {
    Graph<Integer> expectedGraph = new DirectedGraph<>();
    expectedGraph.addEdge(5,
        node3,
        node2);
    expectedGraph.addEdge(9,
        node4,
        node3);
    expectedGraph.addEdge(6,
        node5,
        node2);
    expectedGraph.addEdge(5,
        node5,
        node1);
    expectedGraph.addEdge(7,
        node6,
        node4);
    assertEquals(6,
        graph.getNumNodes());
    assertEquals(8,
        graph.getNumEdges());
    Mst<Integer> mst = new Mst<>(graph);
    System.out.println(mst.getGraph());
    assertEquals(6,
        mst.getGraph().getNumNodes());
    assertEquals(5,
        mst.getGraph().getNumEdges());
    assertEquals(32,
        mst.getMaximumCost());
    assertEquals(expectedGraph,
        mst.getGraph());
  }
}
