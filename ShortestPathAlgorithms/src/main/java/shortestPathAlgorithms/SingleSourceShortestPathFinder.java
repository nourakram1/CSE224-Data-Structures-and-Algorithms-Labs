package shortestPathAlgorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import graphDataStructure.DirectedEdge;
import graphDataStructure.Graph;

/**
 * An abstract class providing a framework for shortest path algorithms in a weighted directed graph.
 * <p>
 * This class is designed to support:
 * <ul>
 *     <li><b>Dijkstra's Algorithm</b>: Works for graphs with non-negative edge weights.</li>
 *     <li><b>Bellman-Ford Algorithm</b>: Handles negative weights and detects negative cycles.</li>
 *     <li><b>Floyd-Warshall Algorithm</b>: Computes shortest paths between all pairs of vertices.</li>
 * </ul>
 * <p>
 * It maintains:
 * <ul>
 *     <li>{@code distTo[]} – Stores the shortest known distance from the source to each vertex.</li>
 *     <li>{@code edgeTo[]} – Stores the last edge used in the shortest path tree.</li>
 * </ul>
 * <p>
 * Subclasses must implement the {@code computeShortestPaths()} method to define the specific algorithm logic.
 */
public abstract class SingleSourceShortestPathFinder {

    /** Stores the last edge in the shortest path from the source to each vertex. */
    protected DirectedEdge[] edgeTo;

    /** Stores the shortest known distance from the source vertex to each vertex. */
    private final double[] distTo;

    /** The directed graph on which the shortest path algorithm operates. */
    protected Graph graph;

    /** The source vertex for shortest path computations. */
    protected int source;

    /**
     * Constructs a shortest path solver for a given graph and source vertex.
     * Initializes {@code distTo} with {@code Double.POSITIVE_INFINITY}, except for the source vertex set to 0.
     *
     * @param graph  the graph on which to compute shortest paths
     * @param source the source vertex from which shortest paths are computed
     * @throws IllegalArgumentException if {@code source} is out of valid range
     */
    public SingleSourceShortestPathFinder(Graph graph, int source) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null.");
        }
        if (source < 0 || source >= graph.numberOfVertices()) {
            throw new IllegalArgumentException("Source vertex " + source + " is out of bounds.");
        }

        this.graph = graph;
        this.source = source;
        int V = graph.numberOfVertices();
        this.distTo = new double[V];
        this.edgeTo = new DirectedEdge[V];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[source] = 0;
    }

    /**
     * Computes the shortest paths from the source vertex to all other vertices.
     * This method must be implemented by subclasses according to their respective algorithm.
     */
    protected abstract void computeShortestPaths();

    /**
     * Attempts to relax a directed edge, updating the shortest path estimate if a shorter path is found.
     * This method is used in shortest path algorithms such as Dijkstra and Bellman-Ford.
     *
     * @param edge the directed edge to relax
     * @return {@code true} if the edge was relaxed (i.e., the shortest path estimate improved), {@code false} otherwise
     */
    protected boolean relaxEdge(DirectedEdge edge) {
        int source = edge.source();
        int target = edge.target();
        double weight = edge.weight();

        if (distTo[source] == Double.POSITIVE_INFINITY) {
            return false;
        }

        double newDist = distTo[source] + weight;
        if (newDist < distTo[target]) {
            distTo[target] = newDist;
            edgeTo[target] = edge;
            return true;
        }
        return false;
    }

    public double distTo(int v) {
        return distTo[v];
    }

    /**
     * Returns the shortest path from the source vertex to the specified vertex.
     * If no path exists, an empty list is returned.
     *
     * @param vertex the destination vertex
     * @return a list of edges representing the shortest path from source to the given vertex,
     *         or an empty list if no path exists
     * @throws IllegalArgumentException if {@code vertex} is out of valid range
     */
    public List<DirectedEdge> pathTo(int vertex) {
        if (vertex < 0 || vertex >= graph.numberOfVertices())
            throw new IllegalArgumentException("Vertex " + vertex + " is out of bounds.");
        if (distTo[vertex] == Double.POSITIVE_INFINITY)
            return Collections.emptyList();

        Deque<DirectedEdge> path = new ArrayDeque<>();
        for (DirectedEdge e = edgeTo[vertex]; e != null; e = edgeTo[e.source()]) {
            path.addFirst(e);
        }
        return new ArrayList<>(path);
    }

public DirectedEdge[] getEdgeTo() {
        return edgeTo;
    }

}
