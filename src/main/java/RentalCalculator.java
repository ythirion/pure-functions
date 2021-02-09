import lombok.Getter;

import java.util.List;

@Getter
public class RentalCalculator {
    private double amount;
    private boolean calculated;

    private final List<Rental> rentals;

    public RentalCalculator(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public double calculateRental() {
        checkRentals();

        if (!calculated) {
            for (var rental : rentals) {
                this.amount += rental.getAmount();
            }
            calculated = true;
        }
        return amount;
    }

    public String formatStatement() {
        checkRentals();

        var result = new StringBuilder();

        for (var rental : rentals) {
            if (!calculated)
                this.amount += rental.getAmount();

            result.append(formatLine(rental));
        }
        calculated = true;

        result.append(String.format("Total amount | %f", this.amount));

        return result.toString();
    }

    private static String formatLine(Rental rental) {
        return String.format("%tF : %s | %f \n",
                rental.getDate(),
                rental.getLabel(),
                rental.getAmount());
    }

    private void checkRentals() {
        if (rentals.isEmpty()) {
            throw new IllegalStateException("No rentals !!!");
        }
    }
}