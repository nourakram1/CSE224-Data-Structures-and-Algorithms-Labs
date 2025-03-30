import graphDataStructure.DirectedEdge;
import graphDataStructure.Graph;
import graphDataStructure.WeightedDigraph;
import input.Input;
import shortestPathAlgorithms.Dijkstra;
import shortestPathAlgorithms.SingleSourceShortestPathFinder;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Graph g = new WeightedDigraph(4);
//
//        g.addEdge(new DirectedEdge(1, 2, 10));
//        g.addEdge(new DirectedEdge(2, 1, 4));
//        g.addEdge(2, 0, 10.0);
//        g.addEdge(2, 3, 77);
//
//        Dijkstra x = new Dijkstra(g, 1);
//        List<DirectedEdge> y = x.pathTo(3);
//
//        System.out.println(y);


//        if (args.length == 0) {
//            System.out.println("Usage: java Main <path_to_graph_file>");
//            return;
//        }
        System.out.print("Enter file path: ");
        Scanner scan = new Scanner(System.in);
        String filePath= scan.nextLine();

        Input file = new Input();
        Graph graph = file.readFile(filePath);



        if (graph != null) {
            System.out.println("Graph successfully loaded!");
            System.out.println(graph);
            Dijkstra x = new Dijkstra(graph, 0);
            List<DirectedEdge> y = x.pathTo(3);

            System.out.println(y);
        } else {
            System.out.println("Failed to load the graph.");
        }



    }
}