import java.time.LocalDateTime;
import java.util.UUID;

class Booking {
    private String movieName;
    private int bookedSeats;
    private LocalDateTime bookingTime;
    private String bookingId;

    public Booking(String movieName, int bookedSeats) {
        this.movieName = movieName;
        this.bookedSeats = bookedSeats;
        this.bookingTime = LocalDateTime.now();
        this.bookingId = UUID.randomUUID().toString();
    }

    public String getDetails() {
        return "🎥 Movie: " + movieName + " | 🎟️ Tickets: " + bookedSeats + " | 🕒 Time: " + bookingTime;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public String toString() {
        return "Booking ID: " + bookingId + " | " + getDetails();
    }
}
