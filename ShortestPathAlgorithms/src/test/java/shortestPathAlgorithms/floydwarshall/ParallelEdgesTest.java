package shortestPathAlgorithms.floydwarshall;

import graphDataStructure.DirectedEdge;
import graphDataStructure.Graph;
import graphDataStructure.WeightedDigraph;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import shortestPathAlgorithms.FloydWarshall;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


class ParallelEdgesTest {
    private static final double EPS = 1e-9;
    private static FloydWarshall fw;

    @BeforeAll
    static void setUp() throws FileNotFoundException {
        File inputFile = new File("src/test/resources/floyd-warshall/parallel-edges.txt");
        Scanner scanner = new Scanner(inputFile);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Graph graph = new WeightedDigraph(n);
        for (int i = 0; i < m; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(source, destination, weight);
        }
        fw = new FloydWarshall(graph);
    }

    @Test
    void getDist() {
        double[][] expectedDist = {
                {  0, 2, 5, 6 },
                { 11, 0, 3, 4 },
                {  8,10, 0, 1 },
                {  7, 9,12, 0 }
        };
        for (int i = 0; i < expectedDist.length; i++) {
            assertArrayEquals(expectedDist[i], fw.getDist()[i], EPS);
        }
    }

    @Test
    void getShortestPath() {
        List<DirectedEdge> expectedPath = Arrays.asList(
                new DirectedEdge(3, 0, 7),
                new DirectedEdge(0, 1, 2)
        );
        List<DirectedEdge> actualPath = fw.buildShortestPath(3, 1);
        assertEquals(expectedPath, actualPath);
    }

    @Test
    void hasNegativeCycle() {
        assertFalse(fw.hasNegativeCycle());
    }
}