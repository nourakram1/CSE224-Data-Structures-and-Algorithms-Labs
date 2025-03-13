import input.FileReader;
import input.Parser;
import sort.lang.ComparableNumber;
import sort.Sort;
import sort.SortFactory;
import sort.SortType;
import sort.comparison.ComparisonSortType;
import sort.noncomparison.NonComparisonSortType;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Read input file
        if (args.length == 0) {
            System.out.println("No arguments given");
            return;
        }
        String filePath = args[0];
        String inputString;
        inputString = FileReader.readFile(filePath);
        if (inputString == null) return;

        // Parse input
        ComparableNumber[] arr = Parser.parseNumbers(inputString, ",");

        // Display available sorting algorithms
        System.out.println("\nPlease choose one of the following sorting algorithms:");
        for (ComparisonSortType comparisonSortType : ComparisonSortType.values()) {
            System.out.println(comparisonSortType.getCode() + ". " + comparisonSortType.getName());
        }
        for (NonComparisonSortType nonComparisonSortType : NonComparisonSortType.values()) {
            System.out.println(nonComparisonSortType.getCode() + ". " + nonComparisonSortType.getName());
        }

        // Get selected algorithm
        Scanner scanner = new Scanner(System.in);
        SortType sortType = SortType.getSortType(Integer.parseInt(scanner.nextLine()));

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
        Sort<ComparableNumber> sorter = (sortType instanceof ComparisonSortType) ?
                SortFactory.getComparisonSort((ComparisonSortType) sortType, showSteps)
                : SortFactory.getNonComparisonSort(0, (NonComparisonSortType) sortType, showSteps);
        sorter.sort(arr);

        switch (displayOption) {
            case 1:
                System.out.println("\nSorted Array:\n" + Arrays.toString(arr));
                return;
            case 2:
                List<ComparableNumber[]> intermediateArrays = sorter.getIntermediateArrays();
                int numIntermediateArrays = intermediateArrays.size();

                System.out.println();
                for (int i = 1; i <= numIntermediateArrays; i++) {
                    System.out.printf("Intermediate Array %d: %s%n", i, Arrays.toString(intermediateArrays.get(i - 1)));
                }
                return;
            default:
                throw new IllegalArgumentException("Invalid display option");
        }
    }
}