import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private static HashMap<String, User> users = new HashMap<>();

    public LoginPage() {
        setTitle("Login");
        setSize(300, 200);
        setLayout(new GridLayout(4, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginBtn);
        add(registerBtn);

        loginBtn.addActionListener(e -> login());
        registerBtn.addActionListener(e -> {
            dispose();
            new RegisterPage(users);
        });

        setVisible(true);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (users.containsKey(username) && users.get(username).checkPassword(password)) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            dispose();
            new MovieTicketBookingGUI();  // Proceed to booking
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
