package graphDataStructure;

import lombok.Getter;
import java.util.LinkedList;
import java.util.List;

/**
 * A weighted directed graph implemented using an adjacency list.
 * <p>
 * Each vertex maintains a list of outgoing edges. The graph allows adding edges
 * with specified weights and retrieving adjacent vertices.
 * Vertices are zero-based indexed (0 to {@code vertices - 1}).
 */
public class WeightedDigraph implements Graph {

    /** The total number of vertices in the graph. */
    @Getter
    private final int vertices;

    /** The total number of edges in the graph. */
    @Getter
    private int edges;

    /**
     * The adjacency list representation of the graph.
     * Each index represents a vertex and stores a list of outgoing directed edges.
     */
    private final List<DirectedEdge>[] adjacencyList;

    /**
     * Constructs a weighted directed graph with the specified number of vertices.
     * <p>
     * Initializes an empty adjacency list for each vertex.
     *
     * @param vertices the number of vertices in the graph (must be non-negative)
     * @throws IllegalArgumentException if {@code vertices} is negative
     */
    @SuppressWarnings("unchecked")
    public WeightedDigraph(int vertices) {
        if (vertices < 0) {
            throw new IllegalArgumentException("Number of vertices must be non-negative.");
        }
        this.vertices = vertices;
        this.adjacencyList = (LinkedList<DirectedEdge>[]) new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            this.adjacencyList[i] = new LinkedList<>();
        }
    }

    /**
     * Adds a directed edge from the given source vertex to the target vertex with a specified weight.
     * <p>
     * Internally constructs a {@link DirectedEdge} object and delegates to {@link #addEdge(DirectedEdge)}.
     *
     * @param source the starting vertex of the edge (must be within valid range)
     * @param target the ending vertex of the edge (must be within valid range)
     * @param weight the weight of the edge
     * @throws IllegalArgumentException if source or target is out of range
     */
    public void addEdge(int source, int target, double weight) {
        validateVertex(source);
        validateVertex(target);
        addEdge(new DirectedEdge(source, target, weight));
    }

    /**
     * Adds a directed edge to the graph.
     * <p>
     * Increases the edge count and appends the edge to the adjacency list
     * of the source vertex.
     *
     * @param edge the directed edge to be added (must be a valid edge object)
     * @throws IllegalArgumentException if the source vertex of the edge is out of range
     */
    public void addEdge(DirectedEdge edge) {
        validateVertex(edge.source());
        this.edges++;
        this.adjacencyList[edge.source()].add(edge);
    }

    /**
     * Returns the list of outgoing edges from the specified vertex.
     *
     * @param vertex the vertex whose outgoing edges are requested
     * @return a list of outgoing directed edges
     * @throws IllegalArgumentException if {@code vertex} is out of range
     */
    public List<DirectedEdge> adjacentVertices(int vertex) {
        validateVertex(vertex);
        return adjacencyList[vertex];
    }

    /**
     * Returns a string representation of the graph.
     * <p>
     * The output includes the total number of vertices and edges, followed by
     * a list of all directed edges in the graph.
     *
     * @return a formatted string representation of the graph
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("V = ").append(vertices).append(", E = ").append(edges).append("\n");
        for (int i = 0; i < vertices; i++) {
            for (DirectedEdge edge : adjacencyList[i]) {
                s.append(edge).append("\n");
            }
        }
        return s.toString();
    }

    /**
     * Returns a list of all directed edges in the graph.
     *
     * @return a list containing all edges in the graph
     */
    public List<DirectedEdge> edges() {
        List<DirectedEdge> edgeList = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            edgeList.addAll(adjacencyList[i]);
        }
        return edgeList;
    }

    /**
     * Validates that the given vertex index is within the valid range [0, vertices - 1].
     *
     * @param vertex the vertex index to validate
     * @throws IllegalArgumentException if the vertex index is out of range
     */
    private void validateVertex(int vertex) {
        if (vertex < 0 || vertex >= vertices) {
            throw new IllegalArgumentException("Vertex " + vertex + " is out of bounds.");
        }
    }
}
