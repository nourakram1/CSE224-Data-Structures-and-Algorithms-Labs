package graphDataStructure;

/**
 * Represents a weighted directed edge in a graph.
 * <p>
 * Each edge consists of a source vertex, a target vertex, and an associated weight.
 * The edge is immutable and is represented using a Java record.
 *
 * @param source the starting vertex of the edge
 * @param target the ending vertex of the edge
 * @param weight the weight of the edge
 */
public record DirectedEdge(int source, int target, double weight) {

    /**
     * Returns a string representation of the directed edge in the format:
     * {@code source -> target, w = weight}.
     *
     * @return a formatted string representation of the edge
     */
    @Override
    public String toString() {
        return source + " -> " + target + ", w = " + weight;
    }
}
