package com.ythirion

import java.lang.System.lineSeparator
import scala.util.{Failure, Success, Try}

object RentalCalculator {
  def calculateRental(rentals: List[Rental]): Try[Double] = {
    checkRentals(rentals)
      .map(r => r.map(_.amount).sum)
  }

  def formatStatement(rentals: List[Rental]): Try[String] = {
    checkRentals(rentals)
      .map(r =>
        r.foldLeft("")((statement, rental) => statement + formatLine(rental))
          .concat(formatTotal(rentals)))
  }

  private def checkRentals(rentals: List[Rental]): Try[List[Rental]] =
    if (rentals.isEmpty) Failure(new IllegalStateException("No rentals !!!"))
    else Success(rentals)

  private def formatLine(rental: Rental): String =
    f"${rental.date} : ${rental.label} | ${rental.amount}%.2f${lineSeparator()}"

  private def formatTotal(rentals: List[Rental]): String =
    f"Total amount | ${calculateRental(rentals).getOrElse(0d)}%.2f"
}