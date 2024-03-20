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

  private final Set<Node<String>> guests;

  private final Map<Node<String>, List<Node<String>>> groups;

  private List<Node<String>> strongestRelation;

  private List<Node<String>> weakestRelation;

  private Graph<String> graph;

  /**
   * This is the constructor of the class.
   *
   * @param path The path of the file.
   * @param x    The minimum number of relations.
   * @param k    The number of groups.
   */
  public Audie(String path, int x, int k) {
    this.graph = new Mst<>(new FileGraphParser(path).parseGraph()).getGraph();
    this.strongestRelation = new LinkedList<>();
    this.weakestRelation = new LinkedList<>();
    this.guests = this.getGreaterThanAlgorithm(x);
    this.groups = this.getGroupsOfAlgorithm(k);
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

  /**
   * This method gives the nodes of the graph that has a relation greater than x.
   *
   * @param x The number to compare the edges.
   * @return The nodes of the graph.
   */
  private Set<Node<String>> getGreaterThanAlgorithm(int x) {
    removeEdgesLessThan(x);
    removeEmptyNodes();
    return this.graph.getAllNodes();
  }

  /**
   * This method gives the nodes of the graph that has a relation greater than x.
   *
   * @return The nodes of the graph.
   */
  public String getGreaterThan() {
    StringBuilder result = new StringBuilder();
    result.append("Guests:\n");
    this.guests.forEach(node -> result.append(node).append(" "));
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

  /**
   * This method gives the groups of the graph.
   *
   * @param k The number of groups.
   * @return The groups of the graph.
   */
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
      Node<String> prevDestination = nodes.get(destination);
      for (Node<String> node : groups.get(prevDestination)) {
        nodes.put(node,
                  nodes.get(source));
        groups.get(nodes.get(source)).add(node);
      }
      groups.remove(prevDestination);
      if (firstRelation) {
        this.strongestRelation = groups.get(nodes.get(source));
        firstRelation = false;
      }
      if (groups.size() == k) {
        this.weakestRelation = groups.get(nodes.get(source));
        break;
      }
    }
    if (groups.size() != k) {
      return null;
    }
    return groups;
  }

  /**
   * This method gives the groups of the graph.
   *
   * @return The groups of the graph.
   */
  public String getGroupsOf() {
    StringBuilder result = new StringBuilder();
    result.append("Groups:\n");
    if (this.groups == null) {
      return result.append("It is not possible").toString();
    }
    this.groups.forEach((key, value) -> {
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
    return getRelation(this.strongestRelation,
                       "Group with strongest friendly relationship: ");
  }

  /**
   * This method gives the weakest relation of the graph.
   *
   * @return The weakest relation of the graph.
   */
  public String getWeakestRelation() {
    return getRelation(this.weakestRelation,
                       "Group with least friendly relationship: ");
  }

  private String getRelation(List<Node<String>> relation, String message) {
    StringBuilder result = new StringBuilder();
    result.append(message);
    if (relation.isEmpty()) {
      return result.append("None").toString();
    }
    relation.forEach(node -> result.append(node).append(" "));
    result.delete(result.length() - 1,
                  result.length());
    return result.toString();
  }

  @Override
  public String toString() {
    if (this.groups == null) {
      return """
              %s
              %s
              """.formatted(getGreaterThan(),
                            getGroupsOf());
    }
    return """
            %s
            %s
            %s
            %s
            """.formatted(getGreaterThan(),
                          getGroupsOf(),
                          getStrongestRelation(),
                          getWeakestRelation());
  }
}
