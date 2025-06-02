import java.time.LocalDateTime;

class Movie {
    private String name;
    private int availableSeats;
    private int totalSeats;
    private LocalDateTime lastBookingTime;

    public Movie(String name, int seats) {
        this.name = name;
        this.availableSeats = Math.max(0, seats);
        this.totalSeats = this.availableSeats;
        this.lastBookingTime = null;
    }

    public String getName() {
        return name;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public LocalDateTime getLastBookingTime() {
        return lastBookingTime;
    }

    public void bookSeats(int seats) {
        if (seats <= availableSeats && seats > 0) {
            availableSeats -= seats;
            lastBookingTime = LocalDateTime.now();
        }
    }

    public void cancelSeats(int seats) {
        if (seats > 0 && availableSeats + seats <= totalSeats) {
            availableSeats += seats;
        }
    }

    public boolean isSoldOut() {
        return availableSeats == 0;
    }

    public String toString() {
        return name + " (" + availableSeats + "/" + totalSeats + " seats" +
               (isSoldOut() ? ", Sold Out" : "") + ")";
    }
}
