import dao.*;

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

        mainPanel.add(Box.createVerticalStrut(80));

        JLabel email = new JLabel("Entrez votre adresse e-mail : ");
        email.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField champMail = new JTextField(20);
        champMail.setMaximumSize(new Dimension(200, 30));
        champMail.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(email);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(champMail);

        mainPanel.add(Box.createVerticalStrut(30));

        JLabel motdepasse = new JLabel("Entrez votre mot de passe : ");
        motdepasse.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField champMotdepasse = new JPasswordField(20);
        champMotdepasse.setMaximumSize(new Dimension(200, 30));
        champMotdepasse.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(motdepasse);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(champMotdepasse);

        mainPanel.add(Box.createVerticalStrut(80));

        JPanel ligneButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));

        JButton connexion = new JButton("Connexion");

        connexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = champMail.getText();
                String mdp = new String(champMotdepasse.getPassword());

                UserDAO dao = new UserDAO();
                User user = dao.login(email, mdp);

                if (user !=null) {
                    if (user.getRole().equals("admin")) {
                        JOptionPane.showMessageDialog(null, "connexion réussie");
                        new AdminDashboard(user.getPseudo()).setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Vous êtes connecté en tant qu'utilisateur ");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Email ou mot de passe incorrect");
                }
            }
        });


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
