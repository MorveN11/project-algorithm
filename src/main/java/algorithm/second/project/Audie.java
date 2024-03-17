package algorithm.second.project;

import algorithm.second.project.graph.Graph;
import algorithm.second.project.graph.classes.Node;
import algorithm.second.project.graph.max.MaxGraph;
import algorithm.second.project.graph.mst.Mst;
import algorithm.second.project.utilities.FileGraphParser;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * This is the Audie class.
 */
public class Audie {

  private final List<List<String>> groups;

  private Graph<String> graph;

  public Audie(String path) {
    this.graph = new Mst<>(new FileGraphParser(path).parseGraph()).getGraph();
    this.groups = new LinkedList<>();
  }

  public Graph<String> getGraph() {
    return this.graph;
  }

  /**
   * This method gives the groups of the graph.
   *
   * @param x The number to compare the edges.
   * @return The groups of the graph.
   */
  public Set<Node<String>> getGreaterThan(int x) {
    removeEdgesLessThan(x);
    removeEmptyNodes();
    return this.graph.getAllNodes();
  }

  public List<List<String>> getGroups() {
    return this.groups;
  }

  private void removeEdgesLessThan(int x) {
    this.graph.getAllEdges().forEach(edge -> {
      if (edge.getWeight() <= x) {
        this.graph.removeEdge(edge.getWeight(),
                              edge.getSource(),
                              edge.getDestination());
      }
    });
  }

  private void removeEmptyNodes() {
    this.graph.getAllNodes().removeIf(node -> this.graph.getEdgesNode(node).isEmpty());
    this.graph = new MaxGraph<>(this.graph).getGraph();
  }
}
