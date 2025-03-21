package graphDataStructure;

import java.util.List;

public interface Digraph {
    void addEdge(int source, int target, double weight);
    void addEdge(DirectedEdge edge);
    int getVertices();
    int getEdges();
    List<DirectedEdge> adjacentVertices(int vertex);
}
