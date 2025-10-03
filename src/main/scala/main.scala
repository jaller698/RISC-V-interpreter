import chisel3._
import chisel3.util._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import CheckerRules._
class ChiselCheckers(n: Int) extends Module {
  //i dont use this at all, but i also dont know what to use it for XD
  //Maybe someone smart knows what purpose this serves, i think its mainly tests right?
  val io = IO(new Bundle {
    val from = Input(UInt(n.W)) // A numbered place on the board (default 1-32)
    val to = Input(UInt(n.W)) // A numbered place on the board (default 1-32)
    val reset = Input(Bool())
    val isMoveValid = Output(Bool())
  })
  // Add logic here to check if a move is valid
  io.isMoveValid := false.B
  
  //Actual code starts here
  var board = initialBoard

  printBoard(board)

  //important note, the valid move logic is 0 indexed, while the numbered boards we look at are 1 indexed
  //so you need to -1 the numbers you are looking at on a numbered board
  board=movePiece(8, 12, board)
  board=movePiece(9, 13, board)
  board=movePiece(20, 16, board)


  printBoard(board)



}

object ChiselCheckers extends App {
  println("Generating the Chisel Checkers hardware")
  emitVerilog(new ChiselCheckers(16))
}
