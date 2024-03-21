package algorithm.second.project.graph.max;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import algorithm.second.project.graph.DirectedGraph;
import algorithm.second.project.graph.Graph;
import algorithm.second.project.graph.UndirectedGraph;
import algorithm.second.project.graph.classes.Edge;
import algorithm.second.project.graph.classes.Node;

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
    PriorityQueue<Graph<T>> graphs = new PriorityQueue<>();
    for (Node<T> node : graph.getAllNodes()) {
      Graph<T> graph = dfs(node,
          this.graph instanceof DirectedGraph ? new DirectedGraph<>() : new UndirectedGraph<>());
      graphs.add(graph);
    }
    return graphs.peek();
  }

  private Graph<T> dfs(Node<T> node, Graph<T> dfsGraph) {
    visited.add(node);
    PriorityQueue<Edge<T>> maxHeap = new PriorityQueue<>();
    maxHeap.addAll(graph.getEdgesNode(node));
    while (!maxHeap.isEmpty()) {
      Edge<T> maxEdge = maxHeap.poll();
      if (!visited.contains(maxEdge.getDestination())) {
        dfsGraph.addEdge(maxEdge.getWeight(),
            node,
            maxEdge.getDestination());
        dfs(maxEdge.getDestination(),
            dfsGraph);
      }
    }
    return dfsGraph;
  }
}
