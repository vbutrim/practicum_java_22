import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

class RoomTest {
    @Test
    void shouldIsFree() {
        // Given
        Room room = new Room(
                List.of(
                        // [18:00; 18:45]
                        new Reservation(
                                LocalDateTime.of(
                                        LocalDate.of(2022, 4, 1),
                                        LocalTime.of(18, 0)
                                ),
                                45
                        ),
                        // [19:00; 19:45]
                        new Reservation(
                                LocalDateTime.of(
                                        LocalDate.of(2022, 4, 1),
                                        LocalTime.of(19, 0)
                                ),
                                45
                        )
                )
        );

        // [20:00; 20:45]
        Reservation toCheck = new Reservation(
                LocalDateTime.of(
                        LocalDate.of(2022, 4, 1),
                        LocalTime.of(20, 0)
                ),
                45
        );

        // When
        boolean result = room.isFree(toCheck);

        // Then
        Assertions.assertTrue(result);
    }

    @Test
    void shouldNotIsFree() {
        // Given
        Room room = new Room(
                List.of(
                        // [18:00; 18:45]
                        new Reservation(
                                LocalDateTime.of(
                                        LocalDate.of(2022, 4, 1),
                                        LocalTime.of(18, 0)
                                ),
                                45
                        ),
                        // [19:00; 19:45]
                        new Reservation(
                                LocalDateTime.of(
                                        LocalDate.of(2022, 4, 1),
                                        LocalTime.of(19, 0)
                                ),
                                45
                        )
                )
        );

        // [19:30; 19:45]
        Reservation toCheck = new Reservation(
                LocalDateTime.of(
                        LocalDate.of(2022, 4, 1),
                        LocalTime.of(19, 30)
                ),
                15
        );

        // When
        boolean result = room.isFree(toCheck);

        // Then
        Assertions.assertFalse(result);
    }

}