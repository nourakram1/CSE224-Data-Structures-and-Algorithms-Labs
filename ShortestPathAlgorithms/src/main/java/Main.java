import java.util.Scanner;

import graphDataStructure.Graph;
import input.Input;
import menu.MainMenu;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Shortest Path Algorithms Program!");
        System.out.print("Enter file path: ");
        Scanner scan = new Scanner(System.in);
        String filePath= scan.nextLine();

        Input file = new Input();
        Graph graph = file.readFile(filePath);



        if (graph != null) {
            System.out.println("Graph successfully loaded!");
            System.out.println(graph);
            try {
                new MainMenu().showMenu(graph);
            } catch (Exception e) {
                System.out.println("An error occurred while processing the graph: " + e.getMessage());
            } finally {
                scan.close();
            }
            
            
        } else {
            System.out.println("Failed to load the graph.");
        }



    }
}