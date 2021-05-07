import scala.util.Random

object SudokuSolver {

  val rand = new Random(System.currentTimeMillis())

  def solve(sudoku: Sudoku): Option[Sudoku] = {
    if (sudoku.isSolved) {
      Some(sudoku)
    } else {
      val notSolvedPoints = sudoku.notSolvedPoints
      val pointToSolve = random(notSolvedPoints)
      val assumptions = makeAssumptions(sudoku, pointToSolve)
      if (assumptions.isEmpty) {
        None
      } else {
        for (assumption <- assumptions) {
          val assumptionSudoku = sudoku.copy(pointToSolve, assumption)
          val solution = solve(assumptionSudoku)
          if (solution.isDefined) {
            return solution
          }
        }
        None
      }
    }
  }

  private def random(a: Array[Point]): Point = {
    val random_index = rand.nextInt(a.length)
    a(random_index)
  }

  private def makeAssumptions(sudoku: Sudoku, point: Point): Set[Int] = {
    val row = sudoku.getRow(point.rowIndex)
    val column = sudoku.getColumn(point.columnIndex)
    val square = sudoku.getSquare(point)
    sudoku.availableValue -- row -- column -- square
  }

}
