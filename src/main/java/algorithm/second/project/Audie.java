package algorithm.second.project;

import algorithm.second.project.graph.Graph;
import algorithm.second.project.graph.classes.Edge;
import algorithm.second.project.graph.classes.Node;
import algorithm.second.project.graph.max.MaxGraph;
import algorithm.second.project.graph.mst.Mst;
import algorithm.second.project.utilities.FileGraphParser;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is the Audie class.
 */
public class Audie {

  private List<Node<String>> strongestRelation;

  private List<Node<String>> weakestRelation;

  private Graph<String> graph;

  /**
   * This is the constructor of the class.
   *
   * @param path The path of the file.
   */
  public Audie(String path) {
    this.graph = new Mst<>(new FileGraphParser(path).parseGraph()).getGraph();
    this.strongestRelation = new LinkedList<>();
    this.weakestRelation = new LinkedList<>();
  }

  public Graph<String> getGraph() {
    return this.graph;
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

  private Set<Node<String>> getGreaterThanAlgorithm(int x) {
    removeEdgesLessThan(x);
    removeEmptyNodes();
    return this.graph.getAllNodes();
  }

  /**
   * This method gives the nodes of the graph that has a relation greater than x.
   *
   * @param x The number to compare the edges.
   * @return The nodes of the graph.
   */
  public String getGreaterThan(int x) {
    Set<Node<String>> nodes = getGreaterThanAlgorithm(x);
    StringBuilder result = new StringBuilder();
    result.append("Guests:\n");
    nodes.forEach(node -> result.append(node).append(" "));
    result.delete(result.length() - 1,
                  result.length());
    return result.toString();
  }

  private Map<Node<String>, List<Node<String>>> groupsByNode() {
    Map<Node<String>, List<Node<String>>> groups = new HashMap<>();
    this.graph.getAllNodes().forEach(node -> {
      List<Node<String>> group = new LinkedList<>();
      group.add(node);
      groups.put(node,
                 group);
    });
    return groups;
  }

  private Map<Node<String>, Node<String>> getNodes() {
    Map<Node<String>, Node<String>> nodes = new HashMap<>();
    this.graph.getAllNodes().forEach(node -> nodes.put(node,
                                                       node));
    return nodes;
  }

  private Map<Node<String>, List<Node<String>>> getGroupsOfAlgorithm(int k) {
    Map<Node<String>, List<Node<String>>> groups = groupsByNode();
    if (groups.size() == k) {
      return groups;
    }
    Map<Node<String>, Node<String>> nodes = getNodes();
    Set<Edge<String>> visitedEdges = new HashSet<>();
    boolean firstRelation = true;
    for (Edge<String> edge : this.graph.getAllEdges()) {
      Node<String> source = edge.getSource();
      Node<String> destination = edge.getDestination();
      if (visitedEdges.contains(edge) || nodes.get(source) == nodes.get(destination)) {
        continue;
      }
      Edge<String> imageEdge = new Edge<>(edge.getWeight(),
                                          edge.getDestination(),
                                          edge.getSource());
      visitedEdges.add(edge);
      visitedEdges.add(imageEdge);
      for (Node<String> node : groups.get(nodes.get(destination))) {
        nodes.put(node,
                  nodes.get(source));
        groups.get(nodes.get(source)).add(node);
      }
      groups.remove(destination);
      if (firstRelation) {
        this.strongestRelation = groups.get(nodes.get(source));
        firstRelation = false;
      }
      if (groups.size() == k) {
        this.weakestRelation = groups.get(nodes.get(source));
        break;
      }
    }
    return groups;
  }

  /**
   * This method gives the groups of the graph.
   *
   * @param k The number of groups.
   * @return The groups of the graph.
   */
  public String getGroupsOf(int k) {
    StringBuilder result = new StringBuilder();
    result.append("Groups:\n");
    Map<Node<String>, List<Node<String>>> groups = getGroupsOfAlgorithm(k);
    if (groups.size() != k) {
      this.strongestRelation = new LinkedList<>();
      this.weakestRelation = new LinkedList<>();
      return result.append("It is not possible").toString();
    }
    groups.forEach((key, value) -> {
      value.forEach(node -> result.append(node).append(" "));
      result.delete(result.length() - 1,
                    result.length());
      result.append("\n");
    });
    result.delete(result.length() - 1,
                  result.length());
    return result.toString();
  }

  /**
   * This method gives the strongest relation of the graph.
   *
   * @return The strongest relation of the graph.
   */
  public String getStrongestRelation() {
    StringBuilder result = new StringBuilder();
    result.append("Group with strongest friendly relationship: ");
    if (this.strongestRelation.isEmpty()) {
      return result.append("None").toString();
    }
    this.strongestRelation.forEach(node -> result.append(node).append(" "));
    result.delete(result.length() - 1,
                  result.length());
    return result.toString();
  }

  /**
   * This method gives the weakest relation of the graph.
   *
   * @return The weakest relation of the graph.
   */
  public String getWeakestRelation() {
    StringBuilder result = new StringBuilder();
    result.append("Group with least friendly relationship: ");
    if (this.weakestRelation.isEmpty()) {
      return result.append("None").toString();
    }
    this.weakestRelation.forEach(node -> result.append(node).append(" "));
    result.delete(result.length() - 1,
                  result.length());
    return result.toString();
  }
}
