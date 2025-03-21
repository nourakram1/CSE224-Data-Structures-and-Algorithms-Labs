package shortestPathAlgorithms;

import graphDataStructure.Graph;

public class BellmanFord extends ShortestPathFinder {

    public BellmanFord(Graph graph, int source) {
        super(graph, source);
        computeShortestPaths();
    }

    @Override
    protected void computeShortestPaths() {

    }
}
