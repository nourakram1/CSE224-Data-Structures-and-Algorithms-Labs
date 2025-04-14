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
        int V = graph.numberOfVertices();

        for (int i = 0; i < V - 1; i++) {
            boolean anyRelaxed = false;
            for (DirectedEdge edge : edgeList) {
                if (relaxEdge(edge)) {
                    anyRelaxed = true;
                }
            }
            if (!anyRelaxed) {
                break;
            }
        }

        for (DirectedEdge edge : edgeList) {
            if (relaxEdge(edge)) {
                hasNegativeCycle = true;
                break;
            }
        }
    }


    public boolean hasNegativeCycle() {
        return hasNegativeCycle;
    }
}
