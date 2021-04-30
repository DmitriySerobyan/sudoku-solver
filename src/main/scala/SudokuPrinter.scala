object SudokuPrinter {

  def print(sudoku: Array[Array[Int]]): Unit = {
    println()
    println(
      sudoku
        .map(row => row.map(cell => if (cell == 0) " " else cell.toString))
        .map(row => "|" + row.mkString("|") + "|")
        .mkString("\n")
    )
    println()
  }

}
