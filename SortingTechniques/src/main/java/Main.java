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
        SortType sortType = SortType.getByCode(Integer.parseInt(scanner.nextLine()));

        // Display post-sort options
        System.out.println("\nPlease choose one of the following options:");
        System.out.println(
                "1. Display sorted array\n" +
                "2. Display intermediate arrays"
        );

        int displayOption = Integer.parseInt(scanner.nextLine());
        boolean showSteps = displayOption == 2;
        scanner.close();

        // Sort
        Sort<Integer> sorter = SortFactory.getSort(sortType, showSteps);
        sorter.sort(arr);

        switch (displayOption) {
            case 1:
                System.out.println("\nSorted Array:\n" + Arrays.toString(arr));
                return;
            case 2:
                List<Integer[]> intermediateArrays = sorter.getIntermediateArrays();
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