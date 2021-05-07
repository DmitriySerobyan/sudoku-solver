object SudokuPrinter {

  def print(sudoku: Sudoku): Unit = {
    println()
    println(
      sudoku.content
        .map(row => row.map(cell => if (cell == 0) " " else cell.toString))
        .map(row => "|" + row.mkString("|") + "|")
        .mkString("\n")
    )
    println()
  }

}
