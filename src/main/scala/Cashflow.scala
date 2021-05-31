import java.time.LocalDate
import scala.collection.immutable.Vector
import cats.Monoid

final case class Cashflow(date: LocalDate, amount: Float, currency: String)
final case class CashflowSeq(cashflows: Vector[Cashflow])
final case class CashflowPV(cashflow: Cashflow, refDate: LocalDate, refRate: Float)
final case class CashflowPVSeq(cashflowPVs: Vector[CashflowPV])
val CashflowSeqMonoid: Monoid[CashflowSeq] = new Monoid[CashflowSeq]{
  def empty: CashflowSeq = CashflowSeq(Vector())
  def combine(x: CashflowSeq, y: CashflowSeq): CashflowSeq = CashflowSeq(x.cashflows ++ y.cashflows)
}