import io.vavr.Function1;
import io.vavr.collection.Seq;

public class RentalCalculator {

    public static Function1<Seq<Rental>, String> formatStatement =
            (rentals) -> rentals.foldLeft("", (statement, rental) -> statement + RentalCalculator.formatLine.apply(rental))
                    .concat(String.format("Total amount | %f", RentalCalculator.calculateRentalAmount.apply(rentals)));

    public static Function1<Seq<Rental>, Double> calculateRentalAmount =
            (rentals) -> {
                checkRentals(rentals);
                return rentals.map(Rental::getAmount).sum().doubleValue();
            };

    private static Function1<Rental, String> formatLine = rental ->
            String.format("%tF : %s | %f %n",
                    rental.getDate(),
                    rental.getLabel(),
                    rental.getAmount());

    private static void checkRentals(Seq<Rental> rentals) {
        if (rentals.isEmpty()) {
            throw new IllegalStateException("No rentals !!!");
        }
    }
}