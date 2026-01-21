import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    public Main() {
        setTitle("Itsore - Connexion/Inscription");
        setSize(600, 600);
        setLocation(1400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Element vide
        mainPanel.add(Box.createVerticalStrut(50));

        JLabel message = new JLabel("Bienvenue sur l'application Istore");
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(message);

        mainPanel.add(Box.createVerticalStrut(60));

        JTextField champMail = new JTextField(20);
        champMail.setMaximumSize(new Dimension(200, 30));
        champMail.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(champMail);

        mainPanel.add(Box.createVerticalStrut(60));

        JPanel ligneButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));

        JButton connexion = new JButton("Connexion");
        JButton inscription = new JButton("Inscription");

        inscription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inscription pageInscription = new Inscription();
                pageInscription.setVisible(true);
                dispose();
            }
        });

        ligneButtons.add(connexion);
        ligneButtons.add(inscription);

        mainPanel.add(ligneButtons);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main fenetrePrincipale = new Main();
            fenetrePrincipale.setVisible(true);
        });
    }
}
