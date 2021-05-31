import java.time.LocalDate
import scala.collection.immutable.Vector
import cats.Monoid

object Main extends App {
    final case class Cashflow(date: LocalDate, amount: Float, currency: String)
    final case class CashflowSeq(cashflows: Vector[Cashflow])
    final case class CashflowPV(cashflow: Cashflow, refDate: LocalDate, refRate: Float)
    final case class CashflowPVSeq(cashflowPVs: Vector[CashflowPV])
    val CashflowSeqMonoid: Monoid[CashflowSeq] = new Monoid[CashflowSeq]{
      def empty: CashflowSeq = CashflowSeq(Vector())
      def combine(x: CashflowSeq, y: CashflowSeq): CashflowSeq = CashflowSeq(x.cashflows ++ y.cashflows)
    }
    val mycashflow = Cashflow(java.time.LocalDate.now, 0, "USD")
    val myseq = CashflowSeq(Vector(mycashflow))
    val myseq2 = CashflowSeq(Vector(mycashflow))
    val combined = CashflowSeqMonoid.combine(myseq, myseq2)
    println(combined)
}

