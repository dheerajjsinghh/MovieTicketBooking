import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MovieTicketBookingGUI extends JFrame {
    private JComboBox<Movie> movieDropdown;
    private JTextField ticketField;
    private JTextArea bookingDetails;
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Booking> bookings = new ArrayList<>();
    private int totalTicketsBooked = 0;

    public MovieTicketBookingGUI() {
        setTitle("🎬 Movie Ticket Booking System");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        movies.add(new Movie("Avengers: Endgame", 50));
        movies.add(new Movie("Inception", 40));
        movies.add(new Movie("The Dark Knight", 30));
        movies.add(new Movie("Interstellar", 25));
        movies.add(new Movie("Titanic", 20));

        JLabel selectMovieLabel = new JLabel("Select Movie:");
        movieDropdown = new JComboBox<>();
        for (Movie movie : movies) {
            movieDropdown.addItem(movie);
        }

        JLabel ticketLabel = new JLabel("Enter Number of Tickets:");
        ticketField = new JTextField();

        JButton bookButton = new JButton("Book Ticket");
        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookTicket();
            }
        });

        bookingDetails = new JTextArea();
        bookingDetails.setEditable(false);
        bookingDetails.setLineWrap(true);
        bookingDetails.setWrapStyleWord(true);
        bookingDetails.setFont(new Font("Arial", Font.PLAIN, 13));

        add(selectMovieLabel);
        add(movieDropdown);
        add(ticketLabel);
        add(ticketField);
        add(bookButton);
        add(new JScrollPane(bookingDetails));

        setVisible(true);
    }

    private void bookTicket() {
        int selectedIndex = movieDropdown.getSelectedIndex();
        if (selectedIndex < 0 || selectedIndex >= movies.size()) {
            JOptionPane.showMessageDialog(this, "Please select a movie!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Movie selectedMovie = movies.get(selectedIndex);
        String ticketText = ticketField.getText();

        try {
            int tickets = Integer.parseInt(ticketText);
            if (tickets <= 0 || tickets > selectedMovie.getAvailableSeats()) {
                JOptionPane.showMessageDialog(this, "Invalid number of tickets!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            selectedMovie.bookSeats(tickets);
            totalTicketsBooked += tickets;
            Booking booking = new Booking(selectedMovie.getName(), tickets);
            bookings.add(booking);

            movieDropdown.insertItemAt(selectedMovie, selectedIndex);
            movieDropdown.removeItemAt(selectedIndex + 1);

            bookingDetails.append(booking.getDetails() + "\nTotal Tickets Booked So Far: " + totalTicketsBooked + "\n\n");

            if (selectedMovie.isSoldOut()) {
                movieDropdown.removeItem(selectedMovie);
                JOptionPane.showMessageDialog(this, "This movie is now sold out!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }

            JOptionPane.showMessageDialog(this, "✅ Booking Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            ticketField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new MovieTicketBookingGUI();
    }
}
