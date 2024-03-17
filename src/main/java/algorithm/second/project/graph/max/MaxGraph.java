package algorithm.second.project.graph.max;

import algorithm.second.project.graph.DirectedGraph;
import algorithm.second.project.graph.Graph;
import algorithm.second.project.graph.UndirectedGraph;
import algorithm.second.project.graph.classes.Edge;
import algorithm.second.project.graph.classes.Node;
import java.util.HashSet;
import java.util.Set;

/**
 * This is MaxGraph class T -> extends Comparable T.
 *
 * @param <T> T is the Generic Parameter.
 */
public class MaxGraph<T extends Comparable<T>> {

  private final Set<Node<T>> visited;

  private Graph<T> graph;

  /**
   * This is the Constructor of the MaxGraph class.
   *
   * @param graph The graph to convert into a maximum Graph.
   */
  public MaxGraph(Graph<T> graph) {
    this.graph = graph;
    this.visited = new HashSet<>();
    this.graph = getMaxGraph();
  }

  public Graph<T> getGraph() {
    return this.graph;
  }

  private Graph<T> getMaxGraph() {
    Graph<T> maxGraph = this.graph instanceof DirectedGraph ? new DirectedGraph<>() :
            new UndirectedGraph<>();
    int maxWeight = 0;
    for (Node<T> node : graph.getAllNodes()) {
      Graph<T> tmpGraph = dfs(node,
                              this.graph instanceof DirectedGraph ? new DirectedGraph<>() :
                                      new UndirectedGraph<>());
      int weight = getWeight(tmpGraph);
      if (weight > maxWeight) {
        maxWeight = weight;
        maxGraph = tmpGraph;
      }
    }
    return maxGraph;
  }

  private Graph<T> dfs(Node<T> node, Graph<T> dfsGraph) {
    visited.add(node);
    for (Edge<T> neighbor : graph.getEdgesNode(node)) {
      if (!visited.contains(neighbor.getDestination())) {
        dfsGraph.addEdge(neighbor.getWeight(),
                         node,
                         neighbor.getDestination());
        dfs(neighbor.getDestination(),
            dfsGraph);
      }
    }
    return dfsGraph;
  }

  private int getWeight(Graph<T> graph) {
    int weight = 0;
    for (Edge<T> edge : graph.getAllEdges()) {
      weight += edge.getWeight();
    }
    return weight;
  }
}
