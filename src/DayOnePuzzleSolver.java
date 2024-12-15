import java.util.*;

public class DayOnePuzzleSolver {
    private static List<List<Integer>> getPuzzleColumns(String initialPuzzle) {
        String[] rows = initialPuzzle.split("\\n");
        List<Integer> firstColumn = new ArrayList<>();
        List<Integer> secondColumn = new ArrayList<>();

        for(String line : rows) {
            String[] cols = line.split("\\s+");
            int num1 = Integer.parseInt(cols[0]);
            int num2 = Integer.parseInt(cols[1]);
            firstColumn.add(num1);
            secondColumn.add(num2);
        }

        return List.of(firstColumn, secondColumn);
    }

    private static void sortColumns(List<List<Integer>> columns) {
        columns.get(0).sort(Integer::compareTo);
        columns.get(1).sort(Integer::compareTo);
    }

    private static List<Integer> calculateDifferences(List<List<Integer>> columns) {
        List<Integer> firstColumn = columns.get(0);
        List<Integer> secondColumn = columns.get(1);

        List<Integer> differences = new ArrayList<>();
        for(int i = 0; i < firstColumn.size(); i++) {
            differences.add(Math.abs(firstColumn.get(i) - secondColumn.get(i)));
        }

        return differences;
    }

    private static List<Integer> calculateSimilarityScores(List<List<Integer>> columns) {
        List<Integer> firstColumn = columns.get(0);
        List<Integer> secondColumn = columns.get(1);
        Map<Integer, Integer> numberRepeatedTimesMap = new HashMap<>();
        for (int number : secondColumn) {
            int repeatedTimes = numberRepeatedTimesMap.getOrDefault(number, 0);
            numberRepeatedTimesMap.put(number, repeatedTimes + 1);
        }

        List<Integer> similarityScores = new ArrayList<>();
        for (int number : firstColumn) {
            int repeatedTimes = numberRepeatedTimesMap.getOrDefault(number, 0);
            similarityScores.add(number * repeatedTimes);
        }

        return similarityScores;
    }

    public static Integer getPartOneSolution(String initialPuzzle) {
        List<List<Integer>> columns = getPuzzleColumns(initialPuzzle);
        sortColumns(columns);


        var differences = calculateDifferences(columns);


        return differences.stream().reduce(0, Integer::sum);
    }

    public static Integer getPartTwoSolution(String initialPuzzle) {
        List<List<Integer>> columns = getPuzzleColumns(initialPuzzle);
        sortColumns(columns);


        var similarityScores = calculateSimilarityScores(columns);


        return similarityScores.stream().reduce(0, Integer::sum);
    }
}
