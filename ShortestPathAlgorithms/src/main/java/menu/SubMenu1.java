package menu;

import java.util.List;
import java.util.Scanner;

import graphDataStructure.DirectedEdge;
import graphDataStructure.Graph;
import shortestPathAlgorithms.BellmanFord;
import shortestPathAlgorithms.Dijkstra;
import shortestPathAlgorithms.FloydWarshall;

class SubMenu1 implements Menu {

    @Override
    public void showMenu(Graph graph) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Source Vertex: ");
        int source = scanner.nextInt();
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
                    Dijkstra dijkstra = new Dijkstra(graph, source);
                    System.out.println("Shortest path from source to all other nodes: ");
                    for (int i = 0; i < graph.numberOfVertices(); i++) {
                        List<DirectedEdge> path = dijkstra.pathTo(i);
                        System.out.println("Path to " + i + ": " + path);
                    }
                    System.out.println("Choose an optional:");
                    System.out.println("1. The Cost of the Shortest Path");
                    System.out.println("2. The Shortest Path from the Source to the Target Vertex");
                    System.out.println("Enter your choice: ");
                    int optional = scanner.nextInt();
                    switch (optional) {
                        case 1:
                            System.out.println("Choose the Target Vertex: ");
                            int target = scanner.nextInt();
                            double cost = dijkstra.distTo(target);

                            System.out.println(
                                    "The Cost of the Shortest Path from the Source to the Target Vertex is: " + cost);
                            break;
                        case 2:
                            System.out.println("Choose the Target Vertex: ");
                            target = scanner.nextInt();
                            List<DirectedEdge> path = dijkstra.pathTo(target);
                            System.out.println("The Shortest Path from the Source to the Target Vertex is: " + path);
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                case 2:
                    BellmanFord bellmanFord = new BellmanFord(graph, source);
                    System.out.println("Shortest path from source to all other nodes: ");
                    for (int i = 0; i < graph.numberOfVertices(); i++) {
                        List<DirectedEdge> path = bellmanFord.pathTo(i);
                        System.out.println("Path to " + i + ": " + path);
                    }
                    System.out.println("Choose an optional:");
                    System.out.println("1. The Cost of the Shortest Path");
                    System.out.println("2. The Shortest Path from the Source to the Target Vertex");
                    System.out.println("Enter your choice: ");
                    optional = scanner.nextInt();
                    switch (optional) {
                        case 1:
                            System.out.println("Choose the Target Vertex: ");
                            int target = scanner.nextInt();
                            double cost = bellmanFord.distTo(target);
                            System.out.println(
                                    "The Cost of the Shortest Path from the Source to the Target Vertex is: " + cost);
                            break;
                        case 2:
                            System.out.println("Choose the Target Vertex: ");
                            target = scanner.nextInt();
                            List<DirectedEdge> path = bellmanFord.pathTo(target);
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
