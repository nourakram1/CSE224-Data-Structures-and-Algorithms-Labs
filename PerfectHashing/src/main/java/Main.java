
import java.util.Scanner;
import dictionary.Dictionary;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcom to the Dictionary program!");
        System.out.println("Choose the type of Hashing:");
        System.out.println("1. Two-level Hashing");
        System.out.println("2. One-level Hashing");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        boolean twoLevel = choice == 1;
        Dictionary dictionary = new Dictionary(twoLevel);
        while (true) {
           System.out.println("Choose an option:");
            System.out.println("1. Insert a word");
            System.out.println("2. Search for a word");
            System.out.println("3. Delete a word");
            System.out.println("4. Insert a list of words from a file");
            System.out.println("5. Delet a list of words from a file");
            System.out.println("6. Exit");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (option) {
                case 1:
                    System.out.println("Enter a word to insert:");
                    String wordToInsert = scanner.nextLine();
                    dictionary.insert(wordToInsert);
                    break;
                case 2:
                    System.out.println("Enter a word to search:");
                    String wordToSearch = scanner.nextLine();
                    dictionary.search(wordToSearch);
                    break;
                case 3:
                    System.out.println("Enter a word to delete:");
                    String wordToDelete = scanner.nextLine();
                    dictionary.delete(wordToDelete);
                    break;
                case 4:
                    System.out.println("Enter the file path to insert words from:");
                    String filePathToInsert = scanner.nextLine();
                    dictionary.batchInsert(filePathToInsert);
                    break;
                case 5:
                    System.out.println("Enter the file path to delete words from:");
                    String filePathToDelete = scanner.nextLine();
                    dictionary.batchDelete(filePathToDelete);
                    break;
                case 6:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;
            
                default:
                    break;
            }
        }
        
    }
}