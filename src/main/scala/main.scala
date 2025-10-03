import chisel3._
import chisel3.util._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import CheckerRules._
class ChiselCheckers(n: Int) extends Module {
  val io = IO(new Bundle {
    val from = Input(UInt(n.W)) // A numbered place on the board (default 1-32)
    val to = Input(UInt(n.W)) // A numbered place on the board (default 1-32)
    val reset = Input(Bool())
    val isMoveValid = Output(Bool())
  })

  private def initialBoard: Vector[Piece] =
    Vector.tabulate(32) { i =>
      if (i < 12) Black
      else if (i >= 20) White
      else Empty
    }

  val board = initialBoard

  def printBoard() = {
    for (i <- 0 until 8) {
      for (y <- 0 until 4) {
        val tmp = board(y + (4 * i))
        tmp match {
          case White     => print("W")
          case Black     => print("B")
          case WhiteKing => print("7")
          case BlackKing => print("8")
          case Empty     => print(" ")
        }

      }
      print("\n")
    }

  }
  printBoard()
  /*
  val sEmpty :: sWhite :: sWhiteKing :: sBlack :: sBlackKing :: Nil = Enum(5)
  val board_size = 32

  val board = RegInit(VecInit(Seq.fill(board_size)(sEmpty)))

  // Set up initial board state
  for (i <- 0 until 12) {
    board(i) := sBlack
  }
  for (i <- 20 until 32) {
    board(i) := sWhite
  }*/

  // Add logic here to check if a move is valid
  io.isMoveValid := false.B
}

object ChiselCheckers extends App {
  println("Generating the Chisel Checkers hardware")
  emitVerilog(new ChiselCheckers(16))
}
