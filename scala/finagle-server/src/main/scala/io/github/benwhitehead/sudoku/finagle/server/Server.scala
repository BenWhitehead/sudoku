package io.github.benwhitehead.sudoku.finagle.server

import io.finch.Endpoint
import io.github.benwhitehead.finch.FinchServer

/**
 * @author Ben Whitehead
 */
object Server extends FinchServer {
  def endpoint = {
    Sudoku orElse Endpoint.NotFound
  }
}
