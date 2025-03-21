package shortestPathAlgorithms;

import graphDataStructure.DirectedEdge;
import graphDataStructure.Graph;

import java.util.Arrays;

public class FloydWarshall extends ShortestPathFinder {
    private final double[][] dist;

    public FloydWarshall(Graph graph) {
        super(graph, -1);
        int n = graph.getVertices();
        dist = new double[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Double.POSITIVE_INFINITY);
            dist[i][i] = 0;
        }

        for (DirectedEdge edge : graph.edges()) {
            dist[edge.source()][edge.target()] = edge.weight();
        }

        computeShortestPaths();
    }

    @Override
    protected void computeShortestPaths() {
        //TODO
    }
}
