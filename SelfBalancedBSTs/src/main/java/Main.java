import dictionary.Dictionary;
import tree.set.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the dictionary program!");
        System.out.println("Choose the type of tree:");
        System.out.println("1. AVL Tree");
        System.out.println("2. Red-Black Tree");

        Dictionary dictionary;
        Scanner scanner = new Scanner(System.in);

        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1)
            dictionary = new Dictionary(new AVLSet<>());
        else
            dictionary = new Dictionary(new RedBlackSet<>());

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Insert a word");
            System.out.println("2. Search for a word");
            System.out.println("3. Delete a word");
            System.out.println("4. Insert a list of words from a file");
            System.out.println("5. Delete a list of words from a file");
            System.out.println("6. Print Tree");
            System.out.println("7. Exit");

            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1: {
                    System.out.println("Enter a word to insert:");
                    String wordToInsert = scanner.nextLine();
                    dictionary.insert(wordToInsert);
                    break;
                }
                case 2: {
                    System.out.println("Enter a word to search:");
                    String wordToSearch = scanner.nextLine();
                    dictionary.search(wordToSearch);
                    break;
                }
                case 3: {
                    System.out.println("Enter a word to delete:");
                    String wordToDelete = scanner.nextLine();
                    dictionary.delete(wordToDelete);
                    break;
                }
                case 4: {
                    System.out.println("Enter the file path to insert words from:");
                    String filePath = scanner.nextLine();
                    dictionary.batchInsert(Files.readAllLines(Paths.get(filePath)));
                    break;
                }
                case 5: {
                    System.out.println("Enter the file path to delete words from:");
                    String filePath = scanner.nextLine();
                    dictionary.batchDelete(Files.readAllLines(Paths.get(filePath)));
                    break;
                }
                case 6: {
                    System.out.println("Current tree structure:");
                    System.out.println(dictionary);
                    break;
                }
                case 7: {
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;
                }
                default:
                    break;
            }
        }
    }
}