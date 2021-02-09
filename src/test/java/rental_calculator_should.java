import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class rental_calculator_should {
    private final List<Rental> rentals = List.of(
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
        RentalCalculator calculator = new RentalCalculator(Collections.emptyList());
        assertThrows(IllegalStateException.class, calculator::calculateRental);
    }

    @Test
    public void calculate_rentals() {
        RentalCalculator calculator = new RentalCalculator(rentals);
        calculator.calculateRental();

        assertTrue(calculator.isCalculated());
        assertEquals(3037.24, calculator.getAmount(), 0.01);
    }
}