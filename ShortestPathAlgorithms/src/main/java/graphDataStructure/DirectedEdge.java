package graphDataStructure;

public record DirectedEdge(int source, int target, double weight) {
    @Override
    public String toString() {
        return source + " -> " + target + ", w = " + weight;
    }
}
