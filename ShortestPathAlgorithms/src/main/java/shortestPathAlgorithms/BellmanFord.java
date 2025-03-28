package shortestPathAlgorithms;

import graphDataStructure.DirectedEdge;
import graphDataStructure.Graph;

import java.util.List;

public class BellmanFord extends SingleSourceShortestPathFinder {
    private final List<DirectedEdge> edgeList;
    private boolean hasNegativeCycle = false;

    public BellmanFord(Graph graph, int source) {
        super(graph, source);
        edgeList = graph.edgeList();
        computeShortestPaths();
    }

    @Override
    protected void computeShortestPaths() {
        for (int i = 0; i < graph.numberOfVertices() - 1; i++) {
            boolean relaxed = false;
            for (DirectedEdge edge : edgeList) {
                relaxed = relaxed || relaxEdge(edge);
                if (!relaxed) break;
            }

            for (DirectedEdge edge : edgeList) {
                if (relaxEdge(edge)) {
                    this.hasNegativeCycle = true;
                    break;
                }
            }
        }
    }

    public boolean hasNegativeCycle() {
        return hasNegativeCycle;
    }
}
