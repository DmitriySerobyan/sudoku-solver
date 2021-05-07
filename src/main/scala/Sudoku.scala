import scala.math.sqrt

case class Sudoku(content: Array[Array[Int]]) {
  val squareSide: Int = sqrt(content.length).toInt
  val availableValue: Set[Int] = (1 to content.length).toSet

  def copy(point: Point, value: Int): Sudoku = {
    val newContent = content.map { row => row.clone }
    newContent(point.rowIndex)(point.columnIndex) = value
    Sudoku(newContent)
  }

  def isSolved: Boolean = {
    content.forall(row => !row.contains(0))
  }


  def notSolvedPoints: Array[Point] = {
    val result = scala.collection.mutable.ArrayBuffer.empty[Point]
    content.zipWithIndex.foreach { case (row, rowIndex) =>
      row.zipWithIndex.foreach { case (cell, columnIndex) =>
        if (cell == 0) {
          result.addOne(Point(rowIndex, columnIndex))
        }
      }
    }
    result.toArray
  }

  def getRow(index: Int): Array[Int] = {
    content(index)
  }

  def getColumn(index: Int): Array[Int] = {
    val result = scala.collection.mutable.ArrayBuffer.empty[Int]
    content.foreach { row =>
      row.zipWithIndex.foreach { case (cell, columnIndex) =>
        if (columnIndex == index) {
          result.addOne(cell)
        }
      }
    }
    result.toArray
  }

  def getSquare(point: Point): Array[Int] = {
    val squareRowIndex = point.rowIndex / squareSide
    val squareColumnIndex = point.columnIndex / squareSide

    val squareTopRow = squareRowIndex * squareSide
    val squareBottomRow = squareTopRow + squareSide - 1

    val squareLeftColumn = squareColumnIndex * squareSide
    val squareRightColumn = squareLeftColumn + squareSide - 1

    val result = scala.collection.mutable.ArrayBuffer.empty[Int]
    content.zipWithIndex.foreach { case (row, rowIndex) =>
      if(squareTopRow <= rowIndex && rowIndex <= squareBottomRow) {
        row.zipWithIndex.foreach { case (cell, columnIndex) =>
          if (squareLeftColumn <= columnIndex && columnIndex <= squareRightColumn) {
            result.addOne(cell)
          }
        }
      }
    }
    result.toArray
  }

}
