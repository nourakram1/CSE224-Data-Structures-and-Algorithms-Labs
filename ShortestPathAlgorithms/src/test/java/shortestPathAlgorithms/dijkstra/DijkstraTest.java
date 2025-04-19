package shortestPathAlgorithms.dijkstra;

import graphDataStructure.DirectedEdge;
import graphDataStructure.Graph;
import graphDataStructure.WeightedDigraph;
import org.junit.jupiter.api.Test;
import shortestPathAlgorithms.Dijkstra;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {
    private static final double EPS = 1e-9;

    @Test
    void testSimpleGraphDistances() {
        Graph g = new WeightedDigraph(4);
        g.addEdge(new DirectedEdge(0, 1, 1));
        g.addEdge(new DirectedEdge(0, 2, 4));
        g.addEdge(new DirectedEdge(1, 2, 2));
        g.addEdge(new DirectedEdge(2, 3, 1));
        g.addEdge(new DirectedEdge(1, 3, 5));

        Dijkstra d = new Dijkstra(g, 0);
        assertEquals(0.0, d.distTo(0), EPS);
        assertEquals(1.0, d.distTo(1), EPS);
        assertEquals(3.0, d.distTo(2), EPS);
        assertEquals(4.0, d.distTo(3), EPS);
    }

    @Test
    void testSimpleGraphPath() {
        Graph g = new WeightedDigraph(4);
        g.addEdge(new DirectedEdge(0, 1, 1));
        g.addEdge(new DirectedEdge(0, 2, 4));
        g.addEdge(new DirectedEdge(1, 2, 2));
        g.addEdge(new DirectedEdge(2, 3, 1));
        g.addEdge(new DirectedEdge(1, 3, 5));

        Dijkstra d = new Dijkstra(g, 0);
        List<DirectedEdge> path = d.pathTo(3);
        assertEquals(3, path.size());
        assertEquals("0 -> 1 -> 2 -> 3", pathToString(path, 3));
    }

    @Test
    void testDisconnectedGraph() {
        Graph g = new WeightedDigraph(4);
        g.addEdge(new DirectedEdge(0, 1, 1));
        g.addEdge(new DirectedEdge(2, 3, 1));

        Dijkstra d = new Dijkstra(g, 0);
        assertEquals(Double.POSITIVE_INFINITY, d.distTo(2));
        assertTrue(d.pathTo(2).isEmpty());
    }

    @Test
    void testMultipleEdges() {
        Graph g = new WeightedDigraph(2);
        g.addEdge(new DirectedEdge(0, 1, 10));
        g.addEdge(new DirectedEdge(0, 1, 5));

        Dijkstra d = new Dijkstra(g, 0);
        assertEquals(5.0, d.distTo(1), EPS);
    }

    @Test
    void testSelfLoop() {
        Graph g = new WeightedDigraph(1);
        g.addEdge(new DirectedEdge(0, 0, 10));

        Dijkstra d = new Dijkstra(g, 0);
        assertEquals(0.0, d.distTo(0), EPS);
        assertTrue(d.pathTo(0).isEmpty());
    }

    @Test
    void testZeroWeightEdge() {
        Graph g = new WeightedDigraph(3);
        g.addEdge(new DirectedEdge(0, 1, 0));
        g.addEdge(new DirectedEdge(1, 2, 2));

        Dijkstra d = new Dijkstra(g, 0);
        assertEquals(0.0, d.distTo(1), EPS);
        assertEquals(2.0, d.distTo(2), EPS);
    }

    @Test
    void testNegativeEdgeThrows() {
        Graph g = new WeightedDigraph(2);
        g.addEdge(new DirectedEdge(0, 1, -1));
        assertThrows(IllegalArgumentException.class, () -> new Dijkstra(g, 0));
    }

    @Test
    void testPathToSelf() {
        Graph g = new WeightedDigraph(3);
        g.addEdge(new DirectedEdge(0, 1, 1));
        Dijkstra d = new Dijkstra(g, 0);
        assertTrue(d.pathTo(0).isEmpty());
    }

    @Test
    void testInvalidVertexAccess() {
        Graph g = new WeightedDigraph(3);
        g.addEdge(new DirectedEdge(0, 1, 1));
        Dijkstra d = new Dijkstra(g, 0);
        assertThrows(IllegalArgumentException.class, () -> d.pathTo(-1));
        assertThrows(IllegalArgumentException.class, () -> d.pathTo(5));
    }

    @Test
    void testSingleNodeGraph() {
        Graph g = new WeightedDigraph(1);
        Dijkstra d = new Dijkstra(g, 0);
        assertEquals(0.0, d.distTo(0), EPS);
        assertTrue(d.pathTo(0).isEmpty());
    }

    // Non-trivial additional test cases

    @Test
    void testGraphWithCycle() {
        Graph g = new WeightedDigraph(4);
        g.addEdge(new DirectedEdge(0, 1, 1));
        g.addEdge(new DirectedEdge(1, 2, 1));
        g.addEdge(new DirectedEdge(2, 0, 1));
        g.addEdge(new DirectedEdge(1, 3, 2));

        Dijkstra d = new Dijkstra(g, 0);
        assertEquals(3.0, d.distTo(3), EPS);
        List<DirectedEdge> path = d.pathTo(3);
        assertEquals(2, path.size());
        assertEquals("0 -> 1 -> 3", pathToString(path, 3));
    }

    @Test
    void testMultipleEqualPaths() {
        Graph g = new WeightedDigraph(4);
        g.addEdge(new DirectedEdge(0, 1, 1));
        g.addEdge(new DirectedEdge(1, 3, 2));
        g.addEdge(new DirectedEdge(0, 2, 1));
        g.addEdge(new DirectedEdge(2, 3, 2));

        Dijkstra d = new Dijkstra(g, 0);
        assertEquals(3.0, d.distTo(3), EPS);
        String p = pathToString(d.pathTo(3), 3);
        assertTrue(p.equals("0 -> 1 -> 3") || p.equals("0 -> 2 -> 3"));
    }

    @Test
    void testComplexGraph() {
        Graph g = new WeightedDigraph(5);
        g.addEdge(new DirectedEdge(0, 1, 2));
        g.addEdge(new DirectedEdge(0, 2, 5));
        g.addEdge(new DirectedEdge(1, 2, 1));
        g.addEdge(new DirectedEdge(1, 3, 2));
        g.addEdge(new DirectedEdge(2, 3, 1));
        g.addEdge(new DirectedEdge(3, 4, 3));
        g.addEdge(new DirectedEdge(2, 4, 6));

        Dijkstra d = new Dijkstra(g, 0);
        assertEquals(7.0, d.distTo(4), EPS);
        assertEquals("0 -> 1 -> 3 -> 4", pathToString(d.pathTo(4), 4));
    }

    @Test
    void testLargeLinearGraph() {
        int n = 20;
        Graph g = new WeightedDigraph(n);
        for (int i = 0; i < n - 1; i++) {
            g.addEdge(new DirectedEdge(i, i + 1, 1));
        }
        Dijkstra d = new Dijkstra(g, 0);
        assertEquals(n - 1, d.distTo(n- 1), EPS);
        assertEquals(n - 1, d.pathTo(n - 1).size());
    }

    private String pathToString(List<DirectedEdge> path, int dest) {
        if (path.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (DirectedEdge e : path) {
            sb.append(e.source()).append(" -> ");
        }
        sb.append(dest);
        return sb.toString();
    }
}
