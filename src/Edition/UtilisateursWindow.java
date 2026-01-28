package Edition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dao.*;

public class UtilisateursWindow extends JFrame {

    private JTextField emailField;
    private JTextArea infoArea;
    private User utilisateurTrouve;

    public UtilisateursWindow() {

        setTitle("Istore - Affichage Utilisateurs");
        setSize(600, 600);
        setLocation(1400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Gestion des utilisateurs");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(30));
        panel.add(title);
        panel.add(Box.createVerticalStrut(20));

        // email
        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(300, 20));
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(emailField);
        panel.add(Box.createVerticalStrut(20));

        // Boutons

        JButton btnRechercher = new JButton("Rechercher");
        JButton btnModifier = new JButton("Modifier");
        JButton btnSupprimer = new JButton("Supprimer");

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelButtons.add(btnRechercher);
        panelButtons.add(btnModifier);
        panelButtons.add(btnSupprimer);

        panel.add(panelButtons);
        panel.add(Box.createVerticalStrut(20));

        infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(infoArea);

        panel.add(scroll);
        add(panel);

        btnRechercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rechercherUtilisateur();
            }
        });

        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifierUtilisateur();
            }
        });

        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supprimerUtilisateur();
            }
        });



        }

        private void rechercherUtilisateur() {
            String email = emailField.getText().trim();

            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(UtilisateursWindow.this, "Veuillez entrer une adresse email");
                return;
            }

            UserDAO dao = new UserDAO();
            utilisateurTrouve = dao.getUserByEmail(email);

            if (utilisateurTrouve == null) {
                infoArea.setText("Aucun utilisateur trouvé.");
            } else {
                infoArea.setText(
                        "ID : " + utilisateurTrouve.getId() + "\n" +
                        "Email : " + utilisateurTrouve.getEmail() + "\n" +
                        "Pseudo : " + utilisateurTrouve.getPseudo() + "\n" +
                        "Rôle : " + utilisateurTrouve.getRole() + "\n" +
                        "Store ID : " + utilisateurTrouve.getStoreId()
                );
            }
        }

        private void modifierUtilisateur() {
            if (utilisateurTrouve == null) {
                JOptionPane.showMessageDialog(UtilisateursWindow.this, "Aucun utilisateur trouvé.");
                return;
            }

            JTextField pseudoField = new JTextField(utilisateurTrouve.getPseudo());
            JTextField roleField = new JTextField(utilisateurTrouve.getRole());
            JTextField storeField = new JTextField(String.valueOf(utilisateurTrouve.getStoreId()));

            JPanel panel = new JPanel(new GridLayout(3, 2));
            panel.add(new JLabel("Pseudo : "));
            panel.add(pseudoField);
            panel.add(new JLabel("Rôle : "));
            panel.add(roleField);
            panel.add(new JLabel("Store ID :"));
            panel.add(storeField);

            int result = JOptionPane.showConfirmDialog(
                    this, panel, "Modifier l'utilisateur", JOptionPane.OK_CANCEL_OPTION
            );

            if (result == JOptionPane.OK_CANCEL_OPTION) {

                utilisateurTrouve.setPseudo(pseudoField.getText());
                utilisateurTrouve.setRole(roleField.getText());
                utilisateurTrouve.setStoreId(Integer.parseInt(storeField.getText()));

                UserDAO dao = new UserDAO();
                boolean update = dao.updateUser(utilisateurTrouve);

                if (update) {
                    JOptionPane.showMessageDialog(UtilisateursWindow.this, "Utilisateur mis à jour !");
                    rechercherUtilisateur();
                } else {
                    JOptionPane.showMessageDialog(UtilisateursWindow.this, "Erreur lors de la mise à jour");
                }
            }
        }

        private void supprimerUtilisateur () {
            if (utilisateurTrouve == null) {
                JOptionPane.showMessageDialog(UtilisateursWindow.this, "Aucun utilisateur sélectionné.");
                return;
            }

            UserDAO dao = new UserDAO();
            boolean delete = dao.deleteUserByEmail(utilisateurTrouve.getEmail());

            if (delete) {
                JOptionPane.showMessageDialog(UtilisateursWindow.this, "Utilisateur supprimé !");
                infoArea.setText("");
                utilisateurTrouve = null;
            } else {
                JOptionPane.showMessageDialog(UtilisateursWindow.this, "Erreur lors de la suppression");
            }
        }
}
