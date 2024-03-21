package algorithm.second.project.graph.classes;

/**
 * This is Edge class T extends -> Comparable T and Edge implements Comparable
 * Edge T.
 *
 * @param <T> The generic parameter.
 */
public class Edge<T extends Comparable<T>> implements Comparable<Edge<T>> {

  private final Node<T> source;

  private final Node<T> destination;

  private Integer weight;

  /**
   * This is the constructor for Edge class. Source -> Destination | Weight.
   *
   * @param weight      the weight of the Edge.
   * @param source      the source of the Edge.
   * @param destination the destination of the Edge.
   */
  public Edge(Integer weight, Node<T> source, Node<T> destination) {
    this.weight = weight;
    this.source = source;
    this.destination = destination;
  }

  /**
   * This method gives the weight of the Edge.
   *
   * @return The Weight.
   */
  public Integer getWeight() {
    return weight;
  }

  /**
   * This method update the weight of the Edge.
   *
   * @param weight The new Weight of the Edge.
   */
  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  /**
   * This method gives the destination of the Edge.
   *
   * @return The Destination.
   */
  public Node<T> getDestination() {
    return destination;
  }

  /**
   * This method gives the source of the Edge.
   *
   * @return The source.
   */
  public Node<T> getSource() {
    return source;
  }

  @Override
  public int hashCode() {
    return this.source.hashCode() + this.destination.hashCode() + this.weight.hashCode();
  }

  @Override
  public boolean equals(Object object) {
    if (object == null) {
      return false;
    }
    if (this.getClass() != object.getClass()) {
      return false;
    }
    if (object == this) {
      return true;
    }
    Edge<?> obj = (Edge<?>) object;
    boolean source = this.source.equals(obj.getSource());
    boolean destination = this.destination.equals(obj.getDestination());
    boolean weight = this.weight.equals(obj.getWeight());
    return source && destination && weight;
  }

  @Override
  public String toString() {
    return "%s -> %s | weight: %s".formatted(this.source.toString(),
        this.destination.toString(),
        this.weight);
  }

  @Override
  public int compareTo(Edge<T> o) {
    int weightComparison = o.getWeight().compareTo(this.weight);
    if (weightComparison != 0) {
      return weightComparison;
    }
    int sourceComparison = o.getSource().compareTo(this.source);
    if (sourceComparison != 0) {
      return sourceComparison;
    }
    return o.getDestination().compareTo(this.destination);
  }
}
