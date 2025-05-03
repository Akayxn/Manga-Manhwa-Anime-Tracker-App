package org.randomanime;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AnimeFrame {

    public static void AnimeFrameUI() throws IOException {

        // Get anime info (Title and image URL)
        ArrayList<String> animeInfo = AnimeApi.getRandomAnime();
        System.out.println(animeInfo);

        if (animeInfo.isEmpty()) {
            System.out.println("No anime data found.");
            return;
        }

        // Load image from URL
        URL imageURL;
        try {
            imageURL = new URL(animeInfo.get(0)); // Assume the first item is an image URL
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }

        ImageIcon imageIcon = new ImageIcon(ImageIO.read(imageURL));

        // Create JFrame
        JFrame frame = new JFrame("Anime Random");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(new ImageIcon("src/main/java/org/randomanime/images/logo.png").getImage());

        // Load background image
        File backgroundFile = new File("src/main/java/org/randomanime/images/loginbackground.jpg");
        Image backgroundImage = ImageIO.read(backgroundFile);

        // Main panel (for content)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // === Image Panel (Top) ===
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(500, 300));
        imageLabel.setIcon(imageIcon);  // Set image to the label
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // === Center Panel (Title + Buttons) ===
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel(animeInfo.get(1));  // Assume title is in animeInfo.get(1)
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);  // Make button panel transparent



        // === Bottom Random Button ===
        JButton randomButton = new JButton("Search 🔎");
        randomButton.setPreferredSize(new Dimension(200, 50));
        randomButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        randomButton.setFocusable(false);
        randomButton.setForeground(Color.white);
        randomButton.setBackground(Color.BLUE);
        randomButton.addActionListener(e -> {
            ArrayList<String> randomAnime = AnimeApi.getRandomAnime();
            if (randomAnime.isEmpty()) return;

            try {
                URL randomImageUrl = new URL(randomAnime.get(0));
                ImageIcon animeImage = new ImageIcon(ImageIO.read(randomImageUrl));
                imageLabel.setIcon(animeImage);
                titleLabel.setText(randomAnime.get(1));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        buttonPanel.add(randomButton);

        // Add to centerPanel
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(buttonPanel);



        // Set content pane with background drawing
        frame.setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        });

        // Add image label and center panel to the main panel
        mainPanel.add(imageLabel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

}

