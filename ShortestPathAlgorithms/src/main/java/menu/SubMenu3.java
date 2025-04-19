package menu;

import java.util.Scanner;

import graphDataStructure.Graph;
import shortestPathAlgorithms.BellmanFord;
import shortestPathAlgorithms.FloydWarshall;

public class SubMenu3 implements Menu {
    // This class implements the Menu interface and provides a submenu for Checking for any negative cycle.
    @Override
    public void showMenu(Graph graph) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an algorithm to run:");
            System.out.println("1. Bellman-Ford Algorithm");
            System.out.println("2. Floyd-Warshal Algorithm");
            System.out.println("3. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    BellmanFord bellmanFord = new BellmanFord(graph, 0);
                    if (bellmanFord.hasNegativeCycle()) {
                        System.out.println("The graph contains a negative cycle.");
                    } else {
                        System.out.println("The graph does not contain a negative cycle.");
                    }
                    
                    break;
                case 2:
                    FloydWarshall floydWarshall = new FloydWarshall(graph);
                    if (floydWarshall.hasNegativeCycle()) {
                        System.out.println("The graph contains a negative cycle.");
                    } else {
                        System.out.println("The graph does not contain a negative cycle.");
                    }
                    
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }
    
}
