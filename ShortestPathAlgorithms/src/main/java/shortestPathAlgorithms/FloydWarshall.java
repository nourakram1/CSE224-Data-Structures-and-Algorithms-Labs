package shortestPathAlgorithms;

import graphDataStructure.DirectedEdge;
import graphDataStructure.Graph;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class FloydWarshall {
    private final int n;
    private final DirectedEdge[][] incomingEdge;
    @Getter
    private final double[][] dist;

    public FloydWarshall(Graph graph) {
        n = graph.numberOfVertices();
        dist = new double[n][n];
        incomingEdge = new DirectedEdge[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Double.POSITIVE_INFINITY);
            dist[i][i] = 0;
            incomingEdge[i][i] = null;
        }

        for (DirectedEdge edge : graph.edgeList()) {
            if (dist[edge.source()][edge.target()] > edge.weight()) {
                dist[edge.source()][edge.target()] = edge.weight();
                incomingEdge[edge.source()][edge.target()] = edge;
            }
        }

        computeShortestPaths();
    }

    protected void computeShortestPaths() {
        for (int k = 0; k < n; k++) {
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    if (dist[u][v] > dist[u][k] + dist[k][v]) {
                        dist[u][v] = dist[u][k] + dist[k][v];
                        incomingEdge[u][v] = incomingEdge[k][v];
                    }
                }
            }
        }
    }

    public List<DirectedEdge> buildShortestPath(int src, int dst) {
        if (isPartOfNegativeCycle(src, dst)) {
            return null;
        }
        List<DirectedEdge> path = new ArrayList<>();
        int current = dst;
        while (incomingEdge[src][current] != null) {
            path.addFirst(incomingEdge[src][current]);
            current = incomingEdge[src][current].source();
        }
        return path;
    }

    public boolean hasNegativeCycle() {
        return IntStream.range(0, n).anyMatch(i -> dist[i][i] < 0);
    }

    private boolean isPartOfNegativeCycle(int src, int dst) {
        return IntStream.range(0, n).anyMatch(i ->
                                                dist[i][i] < 0
                                                && dist[src][i] < Double.POSITIVE_INFINITY
                                                && dist[dst][i] < Double.POSITIVE_INFINITY);
    }
}
