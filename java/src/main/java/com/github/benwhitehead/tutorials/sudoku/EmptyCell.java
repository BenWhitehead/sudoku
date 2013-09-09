package com.github.benwhitehead.tutorials.sudoku;

/**
 * @author Ben Whitehead
 */
final class EmptyCell implements CellValue {
    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public String toString() {
        return ".";
    }
}
