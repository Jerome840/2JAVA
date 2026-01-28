package Edition;

import dao.*;
import database.ConnexionBDD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WhitelistWindow extends JFrame {

    public WhitelistWindow() {
        setTitle("Istore - Edition Whitelist");
        setSize(600, 600);
        setLocation(1400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Edition Utilisateurs");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(30));
        panel.add(title);
        panel.add(Box.createVerticalStrut(20));

        // Formulaire d'ajout
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Gestion des emails de la whitelist");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(300, 20));
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel2.add(label);
        panel2.add(Box.createVerticalStrut(20));
        panel2.add(emailField);
        panel2.add(Box.createVerticalStrut(20));


        JButton btnAjout = new JButton("Ajouter");
        JButton btnSupprimer = new JButton("Supprimer");
        JButton btnRechercher = new JButton("Rechercher");

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));

        panelButtons.add(btnAjout);
        panelButtons.add(btnSupprimer);
        panelButtons.add(btnRechercher);

        panel2.add(panelButtons);
        panel.add(panel2);
        add(panel);

        // Bouton ajouter
        btnAjout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();

                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(WhitelistWindow.this, "Veuillez entrer un email");
                    return;
                }

                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(WhitelistWindow.this, "Veuillez entrer une adresse email");
                    return;
                }

                WhitelistDAO dao = new WhitelistDAO();
                boolean ajout = dao.addEmail(email);

                if (ajout) {
                    JOptionPane.showMessageDialog(WhitelistWindow.this, "Email ajouté avec succès !");
                    emailField.setText("");
                } else {
                    JOptionPane.showMessageDialog(WhitelistWindow.this, "Erreur : email déja présent ou invalide");
                }
            }
        });

        // Bouton supprimer
        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();

                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(WhitelistWindow.this, "Adresse email invalide");
                    return;
                }
                WhitelistDAO dao = new WhitelistDAO();
                boolean supprime = dao.deleteEmail(email);

                if (supprime) {
                    JOptionPane.showMessageDialog(WhitelistWindow.this, "Email supprimé de la Whithelist");
                    emailField.setText("");
                } else {
                    JOptionPane.showMessageDialog(WhitelistWindow.this, "Email introuvable dans la Whitelist");
                }
            }
        });


        // Bouton Rechercher
        btnRechercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();

                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(WhitelistWindow.this, "Veuillez entrer un email");
                    return;
                }

                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(WhitelistWindow.this, "Adresse email invalide");
                    return;
                }

                WhitelistDAO dao = new WhitelistDAO();
                boolean existe = dao.emailExists(email);

                if (existe) {
                    JOptionPane.showMessageDialog(WhitelistWindow.this, "L'email est présent dans la whitelist");
                } else {
                    JOptionPane.showMessageDialog(WhitelistWindow.this, "L'email n'est PAS dans la whitelist");
                }
            }
        });
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
}
