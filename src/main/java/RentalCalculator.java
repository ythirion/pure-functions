import java.util.List;

public class RentalCalculator {
    public static double calculateRentalAmount(List<Rental> rentals) {
        checkRentals(rentals);

        double amount = 0;

        for (var rental : rentals) {
            amount += rental.getAmount();
        }
        return amount;
    }

    public static String formatStatement(List<Rental> rentals) {
        checkRentals(rentals);

        var result = new StringBuilder();

        for (var rental : rentals) {
            result.append(formatLine(rental));
        }
        result.append(String.format("Total amount | %f", calculateRentalAmount(rentals)));

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