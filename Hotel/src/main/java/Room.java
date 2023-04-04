import java.util.List;

public class Room {
    private final List<Reservation> reservations;

    public Room(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public boolean isFree(Reservation reservationToCheck) {
        for (Reservation existingReservation : reservations) {
            if (existingReservation.overlaps(reservationToCheck)) {
                return false;
            }
        }

        return true;
    }

}
