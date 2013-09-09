package com.github.benwhitehead.tutorials.sudoku;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ben Whitehead
 */
public class SudokuSolverTest {

    @Test
    public void puzzle() throws Exception {
        final int[][] puzzle = getMatrixFromResourcePath("/puzzle.txt");
        final SudokuSolver solver = new SudokuSolver(puzzle);
        solver.solve();
    }

    @Test
    public void solve_1() throws Exception {
        final int[][] puzzle = getMatrixFromResourcePath("/start-1.txt");
        final int[][] solution = getMatrixFromResourcePath("/solution-1.txt");
        final SudokuSolver solver = new SudokuSolver(puzzle);
        assertThat(solver.solve()).isEqualTo(solution);
    }

    /**
     * puzzle based off: http://dingo.sbs.arizona.edu/~sandiway/sudoku/examples.html challenge2
     * @throws Exception
     */
    @Test
    public void solve_difficult() throws Exception {
        final int[][] puzzle = getMatrixFromResourcePath("/start-difficult.txt");
        final int[][] solution = getMatrixFromResourcePath("/solution-difficult.txt");
        final SudokuSolver solver = new SudokuSolver(puzzle);
        assertThat(solver.solve()).isEqualTo(solution);
    }

    @Test
    public void solveTop95() throws Exception {
        final InputStream stream = this.getClass().getResourceAsStream("/top95");
        final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        while ((line = reader.readLine()) != null) {
            final int[][] matrixFromLine = createMatrixFromLine(line.trim());
            final SudokuSolver solver = new SudokuSolver(matrixFromLine);
            solver.solve();
        }
    }

    private int[][] getMatrixFromResourcePath(final String resourcePath) throws IOException {
        final InputStream inputStream = this.getClass().getResourceAsStream(resourcePath);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return createMatrixFromLine(reader.readLine().trim());
    }

    private int[][] createMatrixFromLine(final String line) {
        assertThat(line.length()).isEqualTo(81);

        final int[][] matrix = new int[9][9];
        for (int i = 0; i < 81; i++) {
            int j = i % 9;
            final int value = Character.getNumericValue(line.charAt(i));
            matrix[i / 9][j] = value == -1 ? 0 : value;
        }
        return matrix;
    }
}
