package com.ythirion

class RentalCalculator(val rentals: List[Rental]) {
  private var _amount = .0
  private var _calculated = false

  def amount = _amount

  def calculated = _calculated

  def calculateRental: Double = {
    if (rentals.isEmpty) throw new IllegalStateException("No rentals !!!")

    if (!_calculated) {
      for (rental <- rentals) {
        this._amount += rental.amount
      }
      _calculated = true
    }
    _amount
  }

  def formatStatement: String = {
    if (rentals.isEmpty) throw new IllegalStateException("No rentals !!!")
    val result = new StringBuilder

    for (rental <- rentals) {
      result.append(formatLine(rental, _amount))
    }
    result.append(f"Total amount | ${calculateRental}%.2f")
    result.toString
  }

  private def formatLine(rental: Rental, amount: Double) =
    f"${rental.date} : ${rental.label} | ${rental.amount}%.2f\n"
}