public class Main {
    public static void main(String[] args) {
        var aocClient = new AocClient(2024, 1);

        var puzzle = aocClient.fetchPuzzle();

        var solution = DayOnePuzzleSolver.getPartTwoSolution(puzzle);
        System.out.println(solution);
    }
}