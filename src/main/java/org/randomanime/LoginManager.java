package org.randomanime;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class LoginManager {
    private static JButton loginButton;
    public static void loginUI()  {
        JPanel backgroundPanel = getJPanel();

        JFrame frame = new JFrame("Login Page");
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("src/main/java/org/randomanime/images/logo.png").getImage());



        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(new Color(0, 0, 0, 150)); // semi-transparent black
        loginPanel.setBorder(BorderFactory.createEmptyBorder(25, 40, 30, 20));
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setPreferredSize(new Dimension(400, 300));



        ImageIcon logoIcon = new ImageIcon("src/main/java/org/randomanime/images/logo.png");
        Image logoImage = logoIcon.getImage().getScaledInstance(220,40, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(Box.createVerticalStrut(10)); // spacing

        // Username
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(new Font("Poppins",Font.BOLD,18));
        usernameLabel.setForeground(Color.WHITE);


        JTextField usernameField = new JTextField(20);
        usernameField.setMaximumSize(new Dimension(400, 50));
        usernameField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        usernameField.setBackground(Color.WHITE);
        usernameField.setFocusTraversalKeysEnabled(false);
        usernameField.setCaretColor(Color.BLACK);


        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        usernamePanel.setOpaque(false);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        // Password
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(new Font("Poppins",Font.BOLD,18));
        passwordLabel.setForeground(Color.WHITE);



        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setMaximumSize(new Dimension(400, 50));
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        passwordField.setBackground(Color.white);
        passwordField.setFocusTraversalKeysEnabled(false);
        passwordField.setCaretColor(Color.BLACK);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.setOpaque(false);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setFocusable(false);
        loginButton.setBackground(new Color(68, 112, 177));
        loginButton.setFont(new Font("Poppins",Font.PLAIN,15));
        loginButton.setForeground(Color.white);
        loginButton.setPreferredSize(new Dimension(300,30));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            char[] passwordArray = passwordField.getPassword();//gets the password which is array of charcters
            String password = String.valueOf(passwordArray);

            try {
                if(authenticateUser(username,password)){
                    AnimeFrame.AnimeFrameUI();
                    frame.dispose();

                }
                else{
                    usernameField.setText("");
                    passwordField.setText("");

                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });


        // Add components to login panel
        loginPanel.add(logoLabel);
        loginPanel.add(Box.createVerticalStrut(40));
        loginPanel.add(usernamePanel);
        loginPanel.add(passwordPanel);
        loginPanel.add(Box.createVerticalStrut(15));
        loginPanel.add(loginButton);

        backgroundPanel.add(loginPanel, gbc);
        frame.setContentPane(backgroundPanel); // Set background panel as content pane
        frame.setVisible(true);
    }

    private static JPanel getJPanel() {
        ImageIcon backgroundPath = new ImageIcon("src/main/java/org/randomanime/images/loginbackground.jpg");
        Image background = backgroundPath.getImage();

        // Create custom panel that paints the background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());
        return backgroundPanel;
    }


    public static boolean authenticateUser(String username,String password) throws JsonProcessingException {
        Map<String,String> loginCreds = User.fetchUsers();

        return loginCreds.containsKey(username) && loginCreds.get(username).equals(password);


    }

}
