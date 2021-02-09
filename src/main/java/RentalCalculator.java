import java.util.List;
import java.util.stream.Collectors;

public class RentalCalculator {
    public static double calculateRentalAmount(List<Rental> rentals) {
        checkRentals(rentals);

        return rentals.stream()
                .mapToDouble(Rental::getAmount)
                .sum();
    }

    public static String formatStatement(List<Rental> rentals) {
        checkRentals(rentals);

        return rentals.stream()
                .map(RentalCalculator::formatLine)
                .collect(Collectors.joining())
                .concat(String.format("Total amount | %f", calculateRentalAmount(rentals)));
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