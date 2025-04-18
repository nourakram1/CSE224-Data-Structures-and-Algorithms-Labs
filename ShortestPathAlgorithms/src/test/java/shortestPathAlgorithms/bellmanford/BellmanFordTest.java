package shortestPathAlgorithms.bellmanford;

import graphDataStructure.DirectedEdge;
import graphDataStructure.Graph;
import graphDataStructure.WeightedDigraph;
import org.junit.jupiter.api.Test;
import shortestPathAlgorithms.BellmanFord;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BellmanFordTest {
    private static final double EPS = 1e-9;

    @Test
    void testSimpleGraphDistances() {
        Graph g = new WeightedDigraph(4);
        g.addEdge(new DirectedEdge(0, 1, 1));
        g.addEdge(new DirectedEdge(0, 2, 4));
        g.addEdge(new DirectedEdge(1, 2, 2));
        g.addEdge(new DirectedEdge(2, 3, 1));
        g.addEdge(new DirectedEdge(1, 3, 5));

        BellmanFord bf = new BellmanFord(g, 0);
        assertFalse(bf.hasNegativeCycle());
        assertEquals(0.0, bf.distTo[0], EPS);
        assertEquals(1.0, bf.distTo[1], EPS);
        assertEquals(3.0, bf.distTo[2], EPS);
        assertEquals(4.0, bf.distTo[3], EPS);
    }

    @Test
    void testSimpleGraphPath() {
        Graph g = new WeightedDigraph(4);
        g.addEdge(new DirectedEdge(0, 1, 1));
        g.addEdge(new DirectedEdge(0, 2, 4));
        g.addEdge(new DirectedEdge(1, 2, 2));
        g.addEdge(new DirectedEdge(2, 3, 1));
        g.addEdge(new DirectedEdge(1, 3, 5));

        BellmanFord bf = new BellmanFord(g, 0);
        List<DirectedEdge> path = bf.pathTo(3);
        assertEquals(3, path.size());
        assertEquals("0 -> 1 -> 2 -> 3", pathToString(path, 3));
    }

    @Test
    void testDisconnectedGraph() {
        Graph g = new WeightedDigraph(4);
        g.addEdge(new DirectedEdge(0, 1, 1));
        g.addEdge(new DirectedEdge(2, 3, 1));

        BellmanFord bf = new BellmanFord(g, 0);
        assertFalse(bf.hasNegativeCycle());
        assertEquals(Double.POSITIVE_INFINITY, bf.distTo[2]);
        assertTrue(bf.pathTo(2).isEmpty());
    }

    @Test
    void testNegativeEdge() {
        Graph g = new WeightedDigraph(2);
        g.addEdge(new DirectedEdge(0, 1, -5));

        BellmanFord bf = new BellmanFord(g, 0);
        assertFalse(bf.hasNegativeCycle());
        assertEquals(-5.0, bf.distTo[1], EPS);
    }

    @Test
    void testNegativeCycleDetection() {
        Graph g = new WeightedDigraph(3);
        g.addEdge(new DirectedEdge(0, 1, 1));
        g.addEdge(new DirectedEdge(1, 2, -1));
        g.addEdge(new DirectedEdge(2, 0, -1));

        BellmanFord bf = new BellmanFord(g, 0);
        assertTrue(bf.hasNegativeCycle());
    }

    @Test
    void testMultipleEdges() {
        Graph g = new WeightedDigraph(2);
        g.addEdge(new DirectedEdge(0, 1, 10));
        g.addEdge(new DirectedEdge(0, 1, 5));

        BellmanFord bf = new BellmanFord(g, 0);
        assertEquals(5.0, bf.distTo[1], EPS);
    }

    @Test
    void testPositiveSelfLoopNoCycle() {
        Graph g = new WeightedDigraph(1);
        g.addEdge(new DirectedEdge(0, 0, 10));

        BellmanFord bf = new BellmanFord(g, 0);
        assertFalse(bf.hasNegativeCycle());
        assertEquals(0.0, bf.distTo[0], EPS);
    }

    @Test
    void testZeroWeightSelfLoopNoCycle() {
        Graph g = new WeightedDigraph(1);
        g.addEdge(new DirectedEdge(0, 0, 0));

        BellmanFord bf = new BellmanFord(g, 0);
        assertFalse(bf.hasNegativeCycle());
        assertEquals(0.0, bf.distTo[0], EPS);
    }

    @Test
    void testNegativeWeightSelfLoopCycle() {
        Graph g = new WeightedDigraph(1);
        g.addEdge(new DirectedEdge(0, 0, -10));

        BellmanFord bf = new BellmanFord(g, 0);
        assertTrue(bf.hasNegativeCycle());
    }

    @Test
    void testInvalidVertexAccess() {
        Graph g = new WeightedDigraph(2);
        g.addEdge(new DirectedEdge(0, 1, 1));
        BellmanFord bf = new BellmanFord(g, 0);
        assertThrows(IllegalArgumentException.class, () -> bf.pathTo(-1));
        assertThrows(IllegalArgumentException.class, () -> bf.pathTo(5));
    }

    @Test
    void testSingleNodeGraph() {
        Graph g = new WeightedDigraph(1);
        BellmanFord bf = new BellmanFord(g, 0);
        assertFalse(bf.hasNegativeCycle());
        assertEquals(0.0, bf.distTo[0], EPS);
        assertTrue(bf.pathTo(0).isEmpty());
    }

    // Additional special test cases

    @Test
    void testGraphWithCycle() {
        Graph g = new WeightedDigraph(4);
        g.addEdge(new DirectedEdge(0, 1, 1));
        g.addEdge(new DirectedEdge(1, 2, 1));
        g.addEdge(new DirectedEdge(2, 0, 1));
        g.addEdge(new DirectedEdge(1, 3, 2));

        BellmanFord bf = new BellmanFord(g, 0);
        assertEquals(3.0, bf.distTo[3], EPS);
        List<DirectedEdge> path = bf.pathTo(3);
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

        BellmanFord bf = new BellmanFord(g, 0);
        assertEquals(3.0, bf.distTo[3], EPS);
        String p = pathToString(bf.pathTo(3), 3);
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

        BellmanFord bf = new BellmanFord(g, 0);
        assertEquals(7.0, bf.distTo[4], EPS);
        assertEquals("0 -> 1 -> 3 -> 4", pathToString(bf.pathTo(4), 4));
    }

    @Test
    void testLargeLinearGraph() {
        int n = 20;
        Graph g = new WeightedDigraph(n);
        for (int i = 0; i < n - 1; i++) {
            g.addEdge(new DirectedEdge(i, i + 1, 1));
        }
        BellmanFord bf = new BellmanFord(g, 0);
        assertEquals(n - 1, bf.distTo[n - 1], EPS);
        assertEquals(n - 1, bf.pathTo(n - 1).size());
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
