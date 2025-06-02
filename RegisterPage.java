import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class RegisterPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public RegisterPage(HashMap<String, User> users) {
        setTitle("Register");
        setSize(300, 200);
        setLayout(new GridLayout(4, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton registerBtn = new JButton("Register");

        add(new JLabel("Choose Username:"));
        add(usernameField);
        add(new JLabel("Choose Password:"));
        add(passwordField);
        add(registerBtn);

        registerBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (!users.containsKey(username)) {
                users.put(username, new User(username, password));
                JOptionPane.showMessageDialog(this, "Registration successful!");
                dispose();
                new LoginPage();  // Go back to login
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}
