package com.ythirion

object RentalCalculator {
  def calculateRental(rentals: List[Rental]): Double = {
    checkRentals(rentals)

    rentals
      .map(r => r.amount)
      .sum
  }

  def formatStatement(rentals: List[Rental]): String = {
    checkRentals(rentals)

    rentals.foldLeft("")((statement, rental) => statement + formatLine(rental))
      .concat(formatTotal(rentals))
  }

  private def checkRentals(rentals: List[Rental]) = {
    if (rentals.isEmpty) throw new IllegalStateException("No rentals !!!")
  }

  private def formatLine(rental: Rental) =
    f"${rental.date} : ${rental.label} | ${rental.amount}%.2f\n"

  private def formatTotal(rentals: List[Rental]) =
    f"Total amount | ${calculateRental(rentals)}%.2f"
}