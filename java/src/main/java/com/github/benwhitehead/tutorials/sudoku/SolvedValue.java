package com.github.benwhitehead.tutorials.sudoku;

import lombok.Getter;

/**
 * @author Ben Whitehead
 */
@Getter
final class SolvedValue implements CellValue {

    private final int value;

    public SolvedValue(final int value) {
        this.value = value;
    }

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
