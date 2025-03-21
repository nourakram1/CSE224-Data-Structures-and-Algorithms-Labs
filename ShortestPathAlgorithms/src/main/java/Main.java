import graphDataStructure.DirectedEdge;
import graphDataStructure.Graph;
import graphDataStructure.WeightedDigraph;

public class Main {
    public static void main(String[] args) {
        Graph g = new WeightedDigraph(3);

        g.addEdge(new DirectedEdge(1, 2, 10));
        g.addEdge(new DirectedEdge(2, 1, 4));
        g.addEdge(2, 0, 10.0);
        System.out.println(g);
    }
}