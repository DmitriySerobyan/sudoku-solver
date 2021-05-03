object Main {

  def main(args: Array[String]): Unit = {
    val sudoku4x4 = Array(
      Array(3, 0, 4, 0),
      Array(0, 1, 0, 2),
      Array(0, 4, 0, 3),
      Array(2, 0, 1, 0),
    )
    SudokuPrinter.print(sudoku4x4)
    val solved4x4 = SudokuSolver.solve(sudoku4x4).get
    SudokuPrinter.print(sudoku4x4)

    val sudoku9x9 = Array(
      Array(0, 0, 0, 0, 0, 9, 4, 7, 0),
      Array(0, 0, 2, 0, 3, 0, 0, 9, 8),
      Array(0, 6, 0, 0, 0, 2, 0, 0, 1),
      Array(0, 0, 0, 0, 0, 0, 5, 0, 7),
      Array(0, 7, 0, 0, 0, 0, 0, 6, 0),
      Array(8, 0, 3, 0, 0, 0, 0, 0, 0),
      Array(6, 0, 0, 1, 0, 0, 0, 2, 0),
      Array(7, 4, 0, 0, 6, 0, 9, 0, 0),
      Array(0, 1, 9, 4, 0, 0, 0, 0, 0),
    )
    SudokuPrinter.print(sudoku9x9)
    val solved9x9 = SudokuSolver.solve(sudoku9x9).get
    SudokuPrinter.print(solved9x9)
  }

}
