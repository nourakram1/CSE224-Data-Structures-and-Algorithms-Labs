package input;

import graphDataStructure.Graph;
import graphDataStructure.WeightedDigraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Input {

    public Graph readFile(String filePath) {
        Graph g = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int vertices = Integer.parseInt(st.nextToken());
            int edges = Integer.parseInt(st.nextToken());

            g = new WeightedDigraph(vertices);

            for (int i = 0; i < edges; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                g.addEdge(from, to, weight);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return g;
    }
}
