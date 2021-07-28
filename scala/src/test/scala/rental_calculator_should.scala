package com.ythirion

import RentalCalculator.{calculateRental, formatStatement}

import org.scalatest.flatspec.AnyFlatSpec

import java.time.LocalDate
import scala.util.Try

class rental_calculator_should extends AnyFlatSpec {
  val rentals = List(
    new Rental(LocalDate.of(2020, 10, 9), "Le Refuge des Loups (LA BRESSE)", 1089.90),
    new Rental(LocalDate.of(2020, 10, 12), "Au pied de la Tour (NOUILLORC)", 1276.45),
    new Rental(LocalDate.of(2020, 10, 24), "Le moulin du bonheur (GLANDAGE)", 670.89),
  )

  "Calculate rentals on a list" should "return the total amount" in {
    val result = calculateRental(rentals)

    assert(result.isSuccess)
    assert(3037.24 == BigDecimal(result.get).setScale(2, BigDecimal.RoundingMode.HALF_UP))
  }

  "Format statement on a list" should "produce a formatted statement" in {
    val statement = formatStatement(rentals)

    assert(statement.isSuccess)
    assert(statement.get ==
      """2020-10-09 : Le Refuge des Loups (LA BRESSE) | 1089.90
        |2020-10-12 : Au pied de la Tour (NOUILLORC) | 1276.45
        |2020-10-24 : Le moulin du bonheur (GLANDAGE) | 670.89
        |Total amount | 3037.24""".stripMargin)
  }

  it should "produce IllegalStateException when rentals is empty on calculation" in {
    assertExceptionCase(calculateRental)
  }

  it should "produce IllegalStateException when rentals is empty on statement formatting" in {
    assertExceptionCase(formatStatement)
  }

  private def assertExceptionCase(f: List[Rental] => Try[Any]): Unit = {
    val result = f(Nil)

    assert(result.isFailure)
    assert(result.failed.get.isInstanceOf[IllegalStateException])
  }
}