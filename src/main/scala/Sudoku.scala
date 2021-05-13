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
    for {
      (row, rowIndex) <- content.zipWithIndex
      (cell, columnIndex) <- row.zipWithIndex
      if (cell == 0)
    } yield Point(rowIndex, columnIndex)
  }

  def getRow(index: Int): Array[Int] = {
    content(index)
  }

  def getColumn(index: Int): Array[Int] = {
    for {
      row <- content
      (cell, columnIndex) <- row.zipWithIndex
      if (columnIndex == index)
    } yield cell
  }

  def getSquare(point: Point): Array[Int] = {
    val squareRowIndex = point.rowIndex / squareSide
    val squareColumnIndex = point.columnIndex / squareSide

    val squareTopRow = squareRowIndex * squareSide
    val squareBottomRow = squareTopRow + squareSide - 1

    val squareLeftColumn = squareColumnIndex * squareSide
    val squareRightColumn = squareLeftColumn + squareSide - 1

    for {
      (row, rowIndex) <- content.zipWithIndex
      if (squareTopRow <= rowIndex && rowIndex <= squareBottomRow)
      (cell, columnIndex) <- row.zipWithIndex
      if (squareLeftColumn <= columnIndex && columnIndex <= squareRightColumn)
    } yield cell
  }

}
