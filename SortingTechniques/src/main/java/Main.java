import input.FileReader;
import input.Parser;
import sort.Sort;
import sort.SortFactory;
import sort.SortType;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Read input file
        String filePath = args[0];
        String inputString;
        try {
            inputString = FileReader.readFile(filePath);
        }
        catch (IOException e) {
            System.out.println("Error reading file");
            return;
        }

        // Parse input
        Integer[] arr = Parser.parseIntegers(inputString, ",");

        // Display available sorting algorithms
        System.out.println("\nPlease choose one of the following sorting algorithms:");
        for (SortType sortType : SortType.values()) {
            System.out.println(sortType.getCode() + ". " + sortType.getName());
        }

        // Get chosen algorithm
        Scanner scanner = new Scanner(System.in);
        int algorithm = Integer.parseInt(scanner.nextLine());

        // sort.Sort
        Sort<Integer> sort = SortFactory.getSort(SortType.getByCode(algorithm));
        sort.sort(arr, false);

        // Display post-sort options
        System.out.println("\nPlease choose one of the following options:");
        System.out.println(
                "1. Display sorted array\n" +
                "2. Display intermediate arrays"
        );

        int displayOption = Integer.parseInt(scanner.nextLine());
        switch (displayOption) {
            case 1:
                System.out.println("\nSorted Array:\n" + Arrays.toString(arr));
                return;
            case 2:
                List<Integer[]> intermediateArrays = sort.getIntermediateArrays();
                int numIntermediateArrays = intermediateArrays.size();

                System.out.println();
                for (int i = 1; i <= numIntermediateArrays; i++ ) {
                    System.out.printf("Intermediate Array %d: %s%n", i, Arrays.toString(intermediateArrays.get(i - 1)));
                }
                return;
            default:
                throw new IllegalArgumentException("Invalid display option");
        }
    }
}