package algorithm.second.project.graph;

import algorithm.second.project.graph.classes.Edge;
import algorithm.second.project.graph.classes.Node;
import java.util.Set;

/**
 * This is DirectedGraph class T -> extends Comparable T and DirectedGraph
 * extends Graph T.
 *
 * @param <T> the Generic Parameter.
 */
public class DirectedGraph<T extends Comparable<T>> extends Graph<T> {

  /**
   * This is the constructor of the DirectedGraph.
   */
  public DirectedGraph() {
    super();
  }

  @Override
  public boolean removeNode(Node<T> node) {
    if (!super.containsNode(node)) {
      return false;
    }
    int count = 0;
    for (Node<T> source : super.getAllNodes()) {
      if (source.equals(node)) {
        continue;
      }
      Set<Edge<T>> edges = super.getEdgesNode(source);
      boolean result = edges.removeIf(e -> e.getDestination().equals(node));
      if (result) {
        count++;
      }
    }
    count += super.getEdgesNode(node).size();
    super.getAdjSets().remove(node);
    super.decreaseAnAmountNumEdges(count);
    super.decreaseNumNodes();
    return true;
  }

  @Override
  public boolean addEdge(Integer weight, Node<T> source, Node<T> destination) {
    super.addNode(source);
    super.addNode(destination);
    if (super.addEdgeFromTo(weight,
        source,
        destination)) {
      return false;
    }
    super.increaseNumEdges();
    return true;
  }

  @Override
  public boolean removeEdge(Integer weight, Node<T> source, Node<T> destination) {
    Edge<T> e1 = new Edge<>(weight,
        source,
        destination);
    for (Node<T> node : super.getAllNodes()) {
      Set<Edge<T>> edges = super.getEdgesNode(node);
      boolean contains = edges.stream().anyMatch(e -> e.equals(e1));
      if (!contains) {
        continue;
      }
      super.decreaseNumEdges();
      return edges.removeIf(e -> e.equals(e1));
    }
    return false;
  }

  @Override
  public boolean updateEdge(Integer weight, Node<T> source, Node<T> destination, int newWeight) {
    Edge<T> e1 = new Edge<>(weight,
        source,
        destination);
    for (Node<T> node : getAllNodes()) {
      Set<Edge<T>> edges = super.getEdgesNode(node);
      boolean contains = edges.stream().anyMatch(e -> e.equals(e1));
      if (!contains) {
        continue;
      }
      edges.stream().filter(e -> e.equals(e1)).findFirst().orElseThrow().setWeight(newWeight);
      return true;
    }
    return false;
  }
}
