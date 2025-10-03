import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec
import CheckerRules._

class ValidMoveTest extends AnyFlatSpec with ChiselScalatestTester {
  
    private def initialBoard: Vector[Piece] =
    Vector.tabulate(32){ i =>
      if (i < 12) Black
      else if (i >= 20) White
      else Empty
    }
  
    behavior of "ChiselCheckers (current: isMoveValid always false)"

    it should "pass" in {
        test(new ChiselCheckers(5)) { dut =>
            dut.io.from.poke(20.U)
            dut.io.to.poke(20.U)
            dut.clock.step()
            dut.io.isMoveValid.expect(false.B)
        }
    }

    it should "fail" in {
        test(new ChiselCheckers(5)) { dut =>
            val b = initialBoard
            val fromSet = Seq(20, 21, 22, 23 ,24)
            for (from <- fromSet; to <- 0 until 32) {
                dut.io.from.poke(from.U)
                dut.io.to.poke(to.U)
                dut.clock.step()
                val ref = isMoveValid(from, to, b)
                if (ref) dut.io.isMoveValid.expect(true.B)
            }

        }
    }
}
