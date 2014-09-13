package io.github.benwhitehead.sudoku.finagle.server

import com.github.benwhitehead.tutorials.sudoku.SudokuSolver
import com.twitter.finagle.Service
import com.twitter.finagle.http.Method
import com.twitter.finagle.http.path.{->, /, Root}
import com.twitter.util.Future
import io.finch.response.Ok
import io.finch._
import io.github.benwhitehead.finch.SimpleEndpoint

/**
 * @author Ben Whitehead
 */
object Sudoku extends SimpleEndpoint {

  def route = {
    case Method.Get -> Root / puzzle => service(puzzle) ! SudokuPuzzleTextRenderer ! SudokuResponse
  }

  type matrix = Array[Array[Int]]
  def service(puzzle: String) = new Service[HttpRequest, matrix] {
    def apply(request: HttpRequest): Future[matrix] = {
      val matrix = puzzleToMatrix(puzzle)
      val solver = new SudokuSolver(matrix)
      Future.value(solver.solve())
    }
  }

  def puzzleToMatrix(puzzle: String): matrix = {
    assert(puzzle.length == 81)
      val rows = for {
        i <- 0 until 9
      } yield {
        val row = (0 until 9) map { case j =>
          val index = (i * 9) + j
          Character.getNumericValue(puzzle.charAt(index)) match {
            case -1 => 0
            case i: Int => i
          }
        }
        row.toArray
      }
    rows.toArray
  }

  object SudokuPuzzleTextRenderer extends Service[matrix, String] {
    override def apply(request: matrix): Future[String] = {
      request.flatten.mkString("").toFuture
    }
  }

  object SudokuResponse extends Service[String, HttpResponse] {
    def apply(request: String): Future[HttpResponse] = {
      Ok(request).toFuture
    }
  }

}
