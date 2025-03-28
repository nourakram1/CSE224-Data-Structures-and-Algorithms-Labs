package shortestPathAlgorithms;

import graphDataStructure.DirectedEdge;
import graphDataStructure.Graph;

import java.util.Arrays;

public class FloydWarshall {
    private final double[][] dist;

    public FloydWarshall(Graph graph) {
        int n = graph.numberOfVertices();
        dist = new double[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Double.POSITIVE_INFINITY);
            dist[i][i] = 0;
        }

        for (DirectedEdge edge : graph.edgeList()) {
            dist[edge.source()][edge.target()] = edge.weight();
        }

        computeShortestPaths();
    }

    protected void computeShortestPaths() {
        //TODO
    }
}
