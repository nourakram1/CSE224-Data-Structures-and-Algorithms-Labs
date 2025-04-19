import graphDataStructure.Graph;
import graphDataStructure.WeightedDigraph;
import shortestPathAlgorithms.Dijkstra;
import shortestPathAlgorithms.SingleSourceShortestPathFinder;


public class Main {
    public static void main(String[] args) {
        Graph g = new WeightedDigraph(7);

        g.addEdge(0, 1, 2);
        g.addEdge(0, 2, 7);
        g.addEdge(0, 4, 12);
        g.addEdge(1, 3, 2);
        g.addEdge(2, 1, 3);
        g.addEdge(2, 3, -1);
        g.addEdge(2, 4, 2);
        g.addEdge(3, 5, 2);
        g.addEdge(4, 6, -7);
        g.addEdge(4, 0, -4);
        g.addEdge(5, 6, 2);
        g.addEdge(6, 3, 1);

        // A0 B1 C2 D3 E4 F5 G6
        SingleSourceShortestPathFinder x = new Dijkstra(g, 0);
    }
}