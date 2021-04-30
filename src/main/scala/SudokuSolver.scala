import scala.util.Random

object SudokuSolver {

  def solve(sudoku: Array[Array[Int]]): Option[Array[Array[Int]]] = {
    if (isSolved(sudoku)) {
      Some(sudoku)
    } else {
      val notSolvedPoints = getNotSolvedPoints(sudoku)
      val pointToSolve = random(notSolvedPoints)
      val assumptions = makeAssumptions(sudoku, pointToSolve)
      if (assumptions.isEmpty) {
        None
      } else {
        for (assumption <- assumptions) {
          val assumptionSudoku = copy(sudoku, pointToSolve, assumption)
          val solution = solve(assumptionSudoku)
          if (solution.isDefined) {
            return solution
          }
        }
        None
      }
    }
  }

  private def isSolved(sudoku: Array[Array[Int]]): Boolean = {
    sudoku.forall(row => !row.contains(0))
  }

  private def random(a: Array[(Int, Int)]): (Int, Int) = {
    val rand = new Random(System.currentTimeMillis())
    val random_index = rand.nextInt(a.length)
    a(random_index)
  }

  private def getNotSolvedPoints(sudoku: Array[Array[Int]]): Array[(Int, Int)] = {
    val result = scala.collection.mutable.ArrayBuffer.empty[(Int, Int)]
    sudoku.zipWithIndex.foreach { case (row, rowIndex) =>
      row.zipWithIndex.foreach { case (cell, columnIndex) =>
        if (cell == 0) {
          result.addOne((rowIndex, columnIndex))
        }
      }
    }
    result.toArray
  }

  private def makeAssumptions(sudoku: Array[Array[Int]], point: (Int, Int)): Set[Int] = {
    val row = getRow(sudoku, point._1)
    val column = getColumn(sudoku, point._2)
    val availableValue = Set(1, 2, 3, 4)
    availableValue -- row.toSet -- column.toSet
  }

  private def getRow(sudoku: Array[Array[Int]], index: Int): Array[Int] = {
    sudoku(index)
  }

  private def getColumn(sudoku: Array[Array[Int]], index: Int): Array[Int] = {
    val result = scala.collection.mutable.ArrayBuffer.empty[Int]
    sudoku.foreach { row =>
      row.zipWithIndex.foreach { case (cell, columnIndex) =>
        if (columnIndex == index) {
          result.addOne(cell)
        }
      }
    }
    result.toArray
  }

  private def copy(sudoku: Array[Array[Int]], point: (Int, Int), value: Int): Array[Array[Int]] = {
    val result = sudoku.map { row => row.clone }
    result(point._1)(point._2) = value
    result
  }

}
