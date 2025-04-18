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
    private final int[][] predecessor;
    @Getter
    private final double[][] dist;

    public FloydWarshall(Graph graph) {
        n = graph.numberOfVertices();
        dist = new double[n][n];
        predecessor = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Double.POSITIVE_INFINITY);
            dist[i][i] = 0;
            predecessor[i][i] = -1;
        }

        for (DirectedEdge edge : graph.edgeList()) {
            if (dist[edge.source()][edge.target()] > edge.weight()) {
                dist[edge.source()][edge.target()] = edge.weight();
                predecessor[edge.source()][edge.target()] = edge.source();
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
                        predecessor[u][v] = predecessor[k][v];
                    }
                }
            }
        }
    }

    public List<Integer> getShortestPath(int src, int dst) {
        List<Integer> path = new ArrayList<>();
        int current = dst;
        while (current != -1) {
            path.addFirst(current);
            current = predecessor[src][current];
        }
        return path;
    }

    public boolean hasNegativeCycle() {
        return IntStream.range(0, n).anyMatch(i -> dist[i][i] < 0);
    }
}
