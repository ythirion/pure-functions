package com.ythirion

class RentalCalculator(val rentals: List[Rental]) {
  private var _amount = .0
  private var _calculated = false

  def amount = _amount

  def calculated = _calculated

  def calculateRental: String = {
    if (rentals.isEmpty) throw new IllegalStateException("No rentals !!!")
    val result = new StringBuilder

    for (rental <- rentals) {
      if (!_calculated) this._amount += rental.amount
      result.append(formatLine(rental, _amount))
    }
    result.append(f"Total amount | ${this._amount}%.2f")
    _calculated = true

    result.toString
  }

  private def formatLine(rental: Rental, amount: Double) =
    f"${rental.date} : ${rental.label} | ${rental.amount}%.2f\n"
}