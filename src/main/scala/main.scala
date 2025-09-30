import chisel3._
import chisel3.util._

class ChiselCheckers(n: Int) extends Module {
  val io = IO(new Bundle {
    val from = Input(UInt(n.W)) // A numbered place on the board (default 1-32)
    val to = Input(UInt(n.W)) // A numbered place on the board (default 1-32)
    val reset = Input(Bool())
    val isMoveValid = Output(Bool())
  })
  val sEmpty :: sWhite :: sWhiteKing :: sBlack :: sBlackKing :: Nil = Enum(5)
  val board_size = 32

  val board = RegInit(VecInit(Seq.fill(board_size)(sEmpty)))

  io.isMoveValid := false.B
}

object ChiselCheckers extends App {
  println("Generating the Chisel Checkers hardware")
  emitVerilog(new ChiselCheckers(16))
}
