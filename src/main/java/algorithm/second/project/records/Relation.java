package algorithm.second.project.records;

/**
 * Relation class to represent the relation between two people.
 *
 * @param from     The person who is related to the other person
 * @param to       The person who is related to the other person
 * @param relation The relation between the two people
 */
public record Relation(String from, String to, int relation) {

}
