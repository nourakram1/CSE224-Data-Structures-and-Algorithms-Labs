package shortestPathAlgorithms;

import graphDataStructure.DirectedEdge;
import graphDataStructure.Graph;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijkstra extends SingleSourceShortestPathFinder {
    private final PriorityQueue<Integer> pq;

    public Dijkstra(Graph graph, int source) {
        super(graph, source);
        pq = new PriorityQueue<>(Comparator.comparingDouble(v -> distTo[v]));
        computeShortestPaths();
    }

    @Override
    protected void computeShortestPaths() {
        pq.add(this.source);
        while(!pq.isEmpty()) {
            int currentNode = pq.poll();
            for(DirectedEdge edge : graph.outEdges(currentNode)) {
                if(relaxEdge(edge)) {
                    pq.add(edge.target());
                }
            }
        }
    }
}
