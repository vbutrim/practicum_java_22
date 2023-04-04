import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

class ReservationTest {
    @Test
    void shouldOverlapsIfALessOrEqualB() {
        // Given
        // a <= b
        Reservation a = new Reservation(
                LocalDateTime.of(
                        LocalDate.of(2022, 4, 1),
                        LocalTime.of(18, 0)
                ),
                45
        );

        Reservation b = new Reservation(
                LocalDateTime.of(
                        LocalDate.of(2022, 4, 1),
                        LocalTime.of(18, 30)
                ),
                45
        );

        // When
        boolean result = a.overlaps(b);

        // Then
        Assertions.assertTrue(result);
    }
}