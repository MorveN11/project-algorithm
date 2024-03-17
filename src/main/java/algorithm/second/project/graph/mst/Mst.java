package algorithm.second.project.graph.mst;

import algorithm.second.project.graph.DirectedGraph;
import algorithm.second.project.graph.Graph;
import algorithm.second.project.graph.UndirectedGraph;
import algorithm.second.project.graph.classes.Edge;
import algorithm.second.project.graph.classes.Node;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * This is Kruskal class T extends comparable T.
 *
 * @param <T> T is the Generic Parameter.
 */
public class Mst<T extends Comparable<T>> {

  private final Graph<T> graph;

  private Map<Node<T>, Node<T>> subsets;

  private Integer maximumCost;

  /**
   * This is the Constructor of Kruskal class.
   *
   * @param graph The graph to convert into a minimum Spanning Tree.
   */
  public Mst(Graph<T> graph) {
    this.subsets = new TreeMap<>();
    this.maximumCost = 0;
    this.graph = kruskal(graph);
  }

  /**
   * This class gives the cost of the Spanning Tree.
   *
   * @return The minimum cost of the Spanning Tree.
   */
  public Integer getMaximumCost() {
    return maximumCost;
  }

  /**
   * This method gives the graph of the MST.
   *
   * @return The Graph.
   */
  public Graph<T> getGraph() {
    return this.graph;
  }

  private Map<Node<T>, Node<T>> fillSubset(Graph<T> graph) {
    Map<Node<T>, Node<T>> tmpSubsets = new TreeMap<>();
    for (Node<T> node : graph.getAllNodes()) {
      tmpSubsets.put(node,
                     node);
    }
    return tmpSubsets;
  }

  private Graph<T> cleanGraph(Graph<T> graph) {
    Set<Edge<T>> visited = new TreeSet<>();
    Graph<T> cleanGraph = new DirectedGraph<>();
    for (Edge<T> edge : graph.getAllEdges()) {
      if (visited.contains(edge)) {
        continue;
      }
      Node<T> source = edge.getSource();
      Node<T> destination = edge.getDestination();
      Integer weight = edge.getWeight();
      Edge<T> imageEdge = new Edge<>(weight,
                                     destination,
                                     source);
      visited.add(edge);
      visited.add(imageEdge);
      cleanGraph.addEdge(weight,
                         source,
                         destination);
    }
    return cleanGraph;
  }

  private Graph<T> convertToUndirectGraph(Graph<T> graphToConvert) {
    Graph<T> undirectedGraph = new UndirectedGraph<>();
    for (Edge<T> edge : graphToConvert.getAllEdges()) {
      undirectedGraph.addEdge(edge.getWeight(),
                              edge.getSource(),
                              edge.getDestination());
    }
    return undirectedGraph;
  }

  private Node<T> find(Node<T> node) {
    if (subsets.get(node) == node) {
      return node;
    }
    return find(subsets.get(node));
  }

  private void union(Node<T> source, Node<T> destination) {
    Node<T> rootSource = find(source);
    Node<T> rootDestination = find(destination);
    subsets.replace(rootSource,
                    rootDestination);
  }

  private Graph<T> kruskal(Graph<T> graphToConvert) {
    Graph<T> mst = graphToConvert instanceof DirectedGraph ? new DirectedGraph<>() :
            new UndirectedGraph<>();
    Graph<T> cleanGraph = cleanGraph(convertToUndirectGraph(graphToConvert));
    subsets = fillSubset(cleanGraph);
    for (Edge<T> edge : cleanGraph.getAllEdges()) {
      if (find(edge.getSource()) != find(edge.getDestination())) {
        mst.addEdge(edge.getWeight(),
                    edge.getSource(),
                    edge.getDestination());
        union(edge.getSource(),
              edge.getDestination());
        maximumCost += edge.getWeight();
      }
    }
    return mst;
  }

  @Override
  public String toString() {
    return this.graph.toString() + "Maximum Cost: " + this.getMaximumCost();
  }
}
