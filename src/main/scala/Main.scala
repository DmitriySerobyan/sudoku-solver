object Main {

  def main(args: Array[String]): Unit = {
    val sudoku = Array(
      Array(3, 0, 4, 0),
      Array(0, 1, 0, 2),
      Array(0, 4, 0, 3),
      Array(2, 0, 1, 0),
    )
    SudokuPrinter.print(sudoku)
    val solved = SudokuSolver.solve(sudoku).get
    SudokuPrinter.print(solved)
  }

}
