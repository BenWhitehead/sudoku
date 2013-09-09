package com.github.benwhitehead.tutorials.sudoku;

import lombok.Getter;

/**
 * @author Ben Whitehead
 */
@Getter
final class DefinedValue implements CellValue {

    private final int value;

    public DefinedValue(final int value) {
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
