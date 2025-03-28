package graphDataStructure;
import java.util.List;

/**
 * Represents a generic directed graph interface that supports weighted edges.
 * <p>
 * This interface defines the essential operations for managing a directed graph,
 * including adding edges, retrieving graph properties, and accessing adjacent vertices.
 */
public interface Graph {

    /**
     * Adds a directed edge between the specified source and target vertices with a given weight.
     * <p>
     * The edge is directed, meaning it goes from {@code source} to {@code target}, and the weight
     * can be positive, negative, or zero depending on the application.
     *
     * @param source the starting vertex of the edge (must be within valid range)
     * @param target the ending vertex of the edge (must be within valid range)
     * @param weight the weight of the edge
     * @throws IllegalArgumentException if {@code source} or {@code target} is out of range
     */
    void addEdge(int source, int target, double weight);

    /**
     * Adds a directed edge to the graph.
     * <p>
     * The provided {@link DirectedEdge} object encapsulates the source, target, and weight of the edge.
     *
     * @param edge the directed edge to be added (must be a valid edge object)
     * @throws IllegalArgumentException if the source vertex of the edge is out of range
     */
    void addEdge(DirectedEdge edge);

    /**
     * Returns the total number of vertices in the graph.
     *
     * @return the number of vertices in the graph
     */
    int numberOfVertices();

    /**
     * Returns the total number of edges in the graph.
     *
     * @return the number of edges in the graph
     */
    int numberOfEdges();

    /**
     * Returns a list of all directed edges in the graph.
     *
     * @return a list containing all edges in the graph
     */
    List<DirectedEdge> edgeList();

    /**
     * Returns a list of directed edges originating from the specified vertex.
     * <p>
     * Each directed edge in the returned list represents an outgoing connection from the given vertex.
     *
     * @param vertex the vertex whose outgoing edges are to be retrieved (must be within valid range)
     * @return a list of directed edges starting from the given vertex
     * @throws IllegalArgumentException if {@code vertex} is out of range
     */
    List<DirectedEdge> outEdges(int vertex);
}
