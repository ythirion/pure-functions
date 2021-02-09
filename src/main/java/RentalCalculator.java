import lombok.Getter;

import java.util.List;

@Getter
public class RentalCalculator {
    private final List<Rental> rentals;

    public RentalCalculator(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public double calculateRentalAmount() {
        checkRentals(this.rentals);
        double amount = 0;

        for (var rental : rentals) {
            amount += rental.getAmount();
        }
        return amount;
    }

    public String formatStatement() {
        checkRentals(this.rentals);

        var result = new StringBuilder();

        for (var rental : rentals) {
            result.append(formatLine(rental));
        }
        result.append(String.format("Total amount | %f", this.calculateRentalAmount()));

        return result.toString();
    }

    private static String formatLine(Rental rental) {
        return String.format("%tF : %s | %f \n",
                rental.getDate(),
                rental.getLabel(),
                rental.getAmount());
    }

    private static void checkRentals(List<Rental> rentals) {
        if (rentals.isEmpty()) {
            throw new IllegalStateException("No rentals !!!");
        }
    }
}