import java.time.Duration;
import java.time.LocalDateTime;

public final class Reservation {
    private final LocalDateTime startTime;
    private final long durationInMins;

    public Reservation(
            LocalDateTime startTime,
            long durationInMins)
    {
        this.startTime = startTime;
        this.durationInMins = durationInMins;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return startTime.plus(Duration.ofMinutes(durationInMins));
    }

    /**
     * [startA, endA]
     * [startB, endB]
     * -> true
     * <p>
     * [startA, endA]
     * [startB, endB]
     * -> false
     * <p>
     * [startB, endB]
     * [startA, endA]
     * -> true
     * <p>
     * startA < startB -> isBefore
     */
    public boolean overlaps(Reservation other) {
        if (!this.getStartTime().isAfter(other.getStartTime())) { // (startA <= startB) = !(startA > startB)
            return overlaps(this, other);
        } else {
            return overlaps(other, this);
        }
    }

    /**
     * a: [startA, endA]
     * b: [startB, endB]
     * <p>
     * startA <= startB
     * <p>
     * [startA, endA]
     * [startB, endB]
     * <p>
     * if endA >= startB -> true
     * else -> false
     * <p>
     * (endA >= startB) = !(endA < startB)
     */
    private static boolean overlaps(Reservation a, Reservation b) {
        return !(a.getEndTime().isBefore(b.getStartTime()));
    }


}
