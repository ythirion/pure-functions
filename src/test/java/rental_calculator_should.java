import io.vavr.collection.Seq;
import io.vavr.collection.Vector;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class rental_calculator_should {
    private final Seq<Rental> rentals = Vector.of(
            Rental.builder()
                    .amount(1089.90)
                    .date(LocalDate.of(2020, 10, 9))
                    .label("Le Refuge des Loups (LA BRESSE)")
                    .build(),
            Rental.builder()
                    .amount(1276.45)
                    .date(LocalDate.of(2020, 10, 12))
                    .label("Au pied de la Tour (NOUILLORC)")
                    .build(),
            Rental.builder()
                    .amount(670.89)
                    .date(LocalDate.of(2020, 10, 24))
                    .label("Le moulin du bonheur (GLANDAGE)")
                    .build());

    @Test
    public void throws_an_illegal_state_exception_when_no_rentals() {
        assertThrows(IllegalStateException.class, () -> RentalCalculator.calculateRentalAmount.apply(Vector.empty()));
    }

    @Test
    public void calculate_rentals() {
        var amount = RentalCalculator.calculateRentalAmount.apply(rentals);
        assertEquals(3037.24, amount, 0.01);
    }

    @Test
    public void format_statement() {
        var statement = RentalCalculator.formatStatement.apply(rentals);

        assertEquals("2020-10-09 : Le Refuge des Loups (LA BRESSE) | 1089.900000 \n" +
                "2020-10-12 : Au pied de la Tour (NOUILLORC) | 1276.450000 \n" +
                "2020-10-24 : Le moulin du bonheur (GLANDAGE) | 670.890000 \n" +
                "Total amount | 3037.240000", statement);
    }
}