package algorithm.second.project.utilities;

import algorithm.second.project.graph.Graph;
import algorithm.second.project.graph.UndirectedGraph;
import algorithm.second.project.graph.classes.Node;
import algorithm.second.project.records.Relation;
import java.util.List;

/**
 * This is the FileGraphParser class.
 */
public class FileGraphParser {

  private final ReadFile file;

  public FileGraphParser(String path) {
    this.file = new ReadFile(path);
  }

  /**
   * This method parse the graph from the file.
   *
   * @return The Graph.
   */
  public Graph<String> parseGraph() {
    Graph<String> graph = new UndirectedGraph<>();
    List<String> neighbors = this.file.getNeighbors();
    List<Relation> relations = this.file.getRelations();
    for (String neighbor : neighbors) {
      graph.addNode(neighbor);
    }
    for (Relation relation : relations) {
      graph.addEdge(relation.relation(),
                    new Node<>(relation.from()),
                    new Node<>(relation.to()));
    }
    return graph;
  }
}
