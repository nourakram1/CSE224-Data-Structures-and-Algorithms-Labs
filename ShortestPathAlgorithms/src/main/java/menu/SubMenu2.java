package menu;

import java.util.List;
import java.util.Scanner;

import graphDataStructure.DirectedEdge;
import graphDataStructure.Graph;
import shortestPathAlgorithms.BellmanFord;
import shortestPathAlgorithms.Dijkstra;
import shortestPathAlgorithms.FloydWarshall;

public class SubMenu2 implements Menu {
    // This class implements the Menu interface and provides a submenu for finding the shortest path to all pairs of nodes.

    @Override
    public void showMenu(Graph graph) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an algorithm to run:");
            System.out.println("1. Dijkstra's Algorithm");
            System.out.println("2. Bellman-Ford Algorithm");
            System.out.println("3. Floyd-Warshal Algorithm");
            System.out.println("4. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Dijkstra dijkstra[] = new Dijkstra[graph.numberOfVertices()];
                    for (int i = 0; i < graph.numberOfVertices(); i++) {
                        dijkstra[i] = new Dijkstra(graph, i);
                    } 
                    System.out.println("Choose an optional:");
                    System.out.println("1. The Cost of the Shortest Path");
                    System.out.println("2. The Shortest Path from the Source to the Target Vertex");
                    System.out.println("Enter your choice: ");
                    int optional = scanner.nextInt();
                    switch (optional) {
                        case 1:
                            System.out.println("Choose the Source Vertex: ");
                            int source = scanner.nextInt();
                            System.out.println("Choose the Target Vertex: ");
                            int target = scanner.nextInt();
                            double cost = dijkstra[source].distTo(target);
                            System.out.println(
                                    "The Cost of the Shortest Path from the Source to the Target Vertex is: " + cost);
                            break;
                        case 2:
                            System.out.println("Choose the Source Vertex: ");
                            source = scanner.nextInt();
                            System.out.println("Choose the Target Vertex: ");
                            target = scanner.nextInt();
                            List<DirectedEdge> path = dijkstra[source].pathTo(target);
                            System.out.println("The Shortest Path from the Source to the Target Vertex is: " + path);
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                case 2:
                    BellmanFord bellmanFord[] = new BellmanFord[graph.numberOfVertices()];
                    for (int i = 0; i < graph.numberOfVertices(); i++) {
                        bellmanFord[i] = new BellmanFord(graph, i);
                    }
                    
                    System.out.println("Choose an optional:");
                    System.out.println("1. The Cost of the Shortest Path");
                    System.out.println("2. The Shortest Path from the Source to the Target Vertex");
                    System.out.println("Enter your choice: ");
                    optional = scanner.nextInt();
                    switch (optional) {
                        case 1:
                            System.out.println("Choose the Source Vertex: ");
                            int source = scanner.nextInt();
                            System.out.println("Choose the Target Vertex: ");
                            int target = scanner.nextInt();
                            double cost = bellmanFord[source].distTo(target);
                            System.out.println(
                                    "The Cost of the Shortest Path from the Source to the Target Vertex is: " + cost);
                            break;
                        case 2:
                            System.out.println("Choose the Source Vertex: ");
                            source = scanner.nextInt();
                            System.out.println("Choose the Target Vertex: ");
                            target = scanner.nextInt();
                            List<DirectedEdge> path = bellmanFord[source].pathTo(target);
                            System.out.println("The Shortest Path from the Source to the Target Vertex is: " + path);
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;
                case 3:
                    FloydWarshall floydWarshall = new FloydWarshall(graph);
                    
                    System.out.println("Choose an optional:");
                    System.out.println("1. The Cost of the Shortest Path");
                    System.out.println("2. The Shortest Path from the Source to the Target Vertex");
                    System.out.println("Enter your choice: ");
                    optional = scanner.nextInt();
                    switch (optional) {
                        case 1:
                            System.out.println("Choose the Source Vertex: ");
                            int source = scanner.nextInt();
                            System.out.println("Choose the Target Vertex: ");
                            int target = scanner.nextInt();
                            List<DirectedEdge> path = floydWarshall.buildShortestPath(source, target);
                            double cost = 0;
                            for (DirectedEdge edge : path) {
                                cost += edge.weight();
                            }
                            
                            System.out.println(
                                    "The Cost of the Shortest Path from the Source to the Target Vertex is: " + cost);
                            break;
                        case 2:
                            System.out.println("Choose the Source Vertex: ");
                            source = scanner.nextInt();
                            System.out.println("Choose the Target Vertex: ");
                            target = scanner.nextInt();
                            path = floydWarshall.buildShortestPath(source, target);
                            System.out.println("The Shortest Path from the Source to the Target Vertex is: " + path);
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;
                case 4:
                    return; // Exit the submenu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }
    
}
