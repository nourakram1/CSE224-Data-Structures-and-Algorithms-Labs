package shortestPathAlgorithms;

import graphDataStructure.Graph;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijkstra extends ShortestPathFinder {

    private PriorityQueue<Integer> pq;

    public Dijkstra(Graph graph, int source) {
        super(graph, source);
        pq = new PriorityQueue<>(Comparator.comparingDouble(v -> distTo[v]));
        pq.add(source);
        computeShortestPaths();
    }

    @Override
    protected void computeShortestPaths() {
        //TODO
    }
}
