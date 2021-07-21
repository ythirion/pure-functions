package com.ythirion

object RentalCalculator {
  def calculateRental(rentals: List[Rental]): Double = {
    checkRentals(rentals)
    var amount = 0d

    for (rental <- rentals) {
      amount += rental.amount
    }
    amount
  }

  def formatStatement(rentals: List[Rental]): String = {
    checkRentals(rentals)
    val result = new StringBuilder

    for (rental <- rentals) {
      result.append(formatLine(rental))
    }
    result.append(f"Total amount | ${calculateRental(rentals)}%.2f")
    result.toString
  }

  private def checkRentals(rentals: List[Rental]) = {
    if (rentals.isEmpty) throw new IllegalStateException("No rentals !!!")
  }

  private def formatLine(rental: Rental) =
    f"${rental.date} : ${rental.label} | ${rental.amount}%.2f\n"
}