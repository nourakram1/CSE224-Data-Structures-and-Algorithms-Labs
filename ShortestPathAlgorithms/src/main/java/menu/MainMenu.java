package menu;

import java.util.Scanner;

import graphDataStructure.Graph;

public class MainMenu implements Menu {

    @Override
    public void showMenu(Graph graph) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1- Finds shortest path from source to all other nodes");
            System.out.println("2- Finds shortest path between all pair of nodes");
            System.out.println("3- Check the graph if contains a negative cycle");
            System.out.println("4- Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice){
                case 1 -> new SubMenu1().showMenu(graph);
                case 2 -> new SubMenu2().showMenu(graph);
                case 3 -> new SubMenu3().showMenu(graph);
                case 4 -> {
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
    }
}
      
    
}
