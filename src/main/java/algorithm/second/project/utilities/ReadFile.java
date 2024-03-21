package algorithm.second.project.utilities;

import algorithm.second.project.exceptions.ErrorReadingFileException;
import algorithm.second.project.exceptions.NotFileFoundException;
import algorithm.second.project.records.Relation;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

/**
 * ReadFile class to read the file and store the neighbors and relations.
 */
public class ReadFile {

  private final BufferedReader bufferedReader;

  private final List<String> neighbors;

  private final List<Relation> relations;

  /**
   * Constructor to initialize the bufferedReader and the lists.
   *
   * @param path The path of the file to read
   */
  public ReadFile(String path) {
    try {
      this.bufferedReader = new BufferedReader(new FileReader(path));
      this.relations = new LinkedList<>();
      this.neighbors = new LinkedList<>();
      this.readNeighbors();
    } catch (FileNotFoundException e) {
      throw new NotFileFoundException();
    }
  }

  public List<String> getNeighbors() {
    return this.neighbors;
  }

  public List<Relation> getRelations() {
    return this.relations;
  }

  /**
   * Reads the neighbors from the file and populates the neighbors and relations
   * lists.
   * The algorithm used in this method is a linear scan of the file, splitting
   * each line into
   * separate components and adding them to the appropriate lists.
   * Time Complexity: O(n), where n is the number of lines in the file.
   *
   * @throws ErrorReadingFileException if there is an error reading the file.
   */
  private void readNeighbors() {
    try {
      String line;
      while ((line = this.bufferedReader.readLine()) != null) {
        String[] split = line.split(" ");
        if (split.length == 1) {
          this.neighbors.add(split[0]);
          continue;
        }
        String from = split[0];
        String to = split[1];
        int relation = Integer.parseInt(split[2]);
        this.relations.add(new Relation(from,
            to,
            relation));
      }
    } catch (Exception e) {
      throw new ErrorReadingFileException();
    }
  }
}
