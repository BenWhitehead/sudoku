#!/bin/bash

function _http {
  http --timeout 120 --print=b :7070/$1
}

function sudoku() {
  echo -e "> $1\n< $(_http $1)" &
}

function main {
  pids=""
  puzzles=$(cat top95)
  for puzzle in ${puzzles}; do
    sudoku ${puzzle}
    pids="${pids} $!"
  done

  for pid in ${pids}; do
      wait ${pid}
  done
  echo "Complete"
}

main
