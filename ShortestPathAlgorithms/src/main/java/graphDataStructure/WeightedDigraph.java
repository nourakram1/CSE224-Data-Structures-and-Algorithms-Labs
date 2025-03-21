package graphDataStructure;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

public class WeightedDigraph implements Digraph{
    @Getter
    private final int vertices;
    @Getter
    private int edges;
    private final List<DirectedEdge>[] adjacencyList;

    @SuppressWarnings("unchecked")
    public WeightedDigraph(int vertices) {
        this.vertices = vertices;
        this.adjacencyList =  (LinkedList<DirectedEdge>[]) new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            this.adjacencyList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int target, double weight) {
        DirectedEdge edge = new DirectedEdge(source, target, weight);
        this.addEdge(edge);
    }

    public void addEdge(DirectedEdge edge) {
        this.edges++;
        this.adjacencyList[edge.source()].add(edge);
    }

    public List<DirectedEdge> adjacentVertices(int vertex) {
        return adjacencyList[vertex];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("V = ").append(vertices).append(", E = ").append(edges).append("\n");
        for(List<DirectedEdge> adj : adjacencyList) {
            for(DirectedEdge edge : adj) {
                s.append(edge).append("\n");
            }
        }
        return s.toString();
    }
}
