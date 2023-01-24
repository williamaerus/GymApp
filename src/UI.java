import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
// import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
// import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
// import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UI {
    public static void main(String[] args) {


        File f = new File("db/gym.db");

                // Checking if the specified file exists or not
                if (f.exists()){
        
                // Show if the file exists
                    System.out.println("DB exists");}
                else{
        
                // Show if the file does not exists
                    System.out.println("DB Doesn't Exists");
                    Users.CreateDB();}
            




        // Create JFrame and set basic properties
        JFrame frame = new JFrame("Gym Database");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Create main JPanel and set layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Create JPanel for logo and add logo image
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.WHITE);
        logoPanel.setPreferredSize(new Dimension(200, 200));
        try {
            File logoFile = new File("img/logo.png");
            if (logoFile.exists()) {
                Image logoImage = ImageIO.read(logoFile);
                logoImage = logoImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
                logoPanel.add(logoLabel);
            } else {
                System.out.println("Logo image not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create JPanel for buttons and set layout
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();

        // Create and customize "Add User" button
        JButton addUserButton = new JButton("Add User");
        addUserButton.setPreferredSize(new Dimension(150, 50));
        addUserButton.setFont(new Font("Arial", Font.PLAIN, 18));
        addUserButton.setBackground(new Color(0, 150, 136));
        addUserButton.setForeground(Color.WHITE);
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Users.addUser();
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(addUserButton, c);
        
        // Create and customize "View Users" button
        JButton viewUsersButton = new JButton("View Users");
        viewUsersButton.setPreferredSize(new Dimension(150, 50));
        viewUsersButton.setFont(new Font("Arial", Font.PLAIN, 18));
        viewUsersButton.setBackground(new Color(0, 150, 136));
        viewUsersButton.setForeground(Color.WHITE);
        viewUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Users.readDataFromDB();
            }
        });
        c.gridx = 1;
        c.gridy = 0;
        buttonPanel.add(viewUsersButton, c);

        // // Create and customize "Delete User" button
        // JButton deleteUserButton = new JButton("Delete User");
        // deleteUserButton.setPreferredSize(new Dimension(150, 50));
        // deleteUserButton.setFont(new Font("Arial", Font.PLAIN, 18));
        // deleteUserButton.setBackground(new Color(0, 150, 136));
        // deleteUserButton.setForeground(Color.WHITE);
        // deleteUserButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         Users.deleteUser();
        //     }
        // });
        // c.gridx = 0;
        // c.gridy = 1;
        // buttonPanel.add(deleteUserButton, c);

        // // Create and customize "Update User" button
        // JButton updateUserButton = new JButton("Update User");
        // updateUserButton.setPreferredSize(new Dimension(150, 50));
        // updateUserButton.setFont(new Font("Arial", Font.PLAIN, 18));
        // updateUserButton.setBackground(new Color(0, 150, 136));
        // updateUserButton.setForeground(Color.WHITE);
        // updateUserButton.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // Users.updateUser();
        // }
        // });
        // c.gridx = 1;
        // c.gridy = 1;
        // buttonPanel.add(updateUserButton, c);
            // Add logoPanel and buttonPanel to mainPanel
    mainPanel.add(logoPanel, BorderLayout.NORTH);
    mainPanel.add(buttonPanel, BorderLayout.CENTER);

    // Add mainPanel to frame and set frame visible
    frame.add(mainPanel);
    frame.setVisible(true);
}
}

