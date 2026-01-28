import dao.UserDAO;
import dao.WhitelistDAO;
import dao.User;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inscription extends JFrame {

    public Inscription() {
        setTitle("Istore - Inscription");
        setSize(600, 600);
        setLocation(1400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainInscription = new JPanel();
        mainInscription.setLayout(new BoxLayout(mainInscription, BoxLayout.Y_AXIS));

        mainInscription.add(Box.createVerticalStrut(30));

        JLabel message2 = new JLabel("Bienvenue sur la page d'inscription");
        message2.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainInscription.add(message2);

        mainInscription.add(Box.createVerticalStrut(30));

        //Email
        JLabel email = new JLabel("Entrez votre adresse e-mail : ");
        email.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField champMail = new JTextField(20);
        champMail.setAlignmentX(Component.CENTER_ALIGNMENT);
        champMail.setMaximumSize(new Dimension(250, 30));

        mainInscription.add(email);
        mainInscription.add(champMail);

        mainInscription.add(Box.createVerticalStrut(15));

        // Pseudo
        JLabel pseudo = new JLabel("Entrez votre pseudo :");
        pseudo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField champPseudo = new JTextField(20);
        champPseudo.setAlignmentX(Component.CENTER_ALIGNMENT);
        champPseudo.setMaximumSize(new Dimension(250, 30));

        mainInscription.add(pseudo);
        mainInscription.add(champPseudo);

        mainInscription.add(Box.createVerticalStrut(15));

        //Mot de Passe
        JLabel motDePasse = new JLabel("Entrez votre mot de passe :");
        motDePasse.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField champMotDePasse = new JTextField(20);
        champMotDePasse.setAlignmentX(Component.CENTER_ALIGNMENT);
        champMotDePasse.setMaximumSize(new Dimension(250, 30));

        mainInscription.add(motDePasse);
        mainInscription.add(champMotDePasse);

        mainInscription.add(Box.createVerticalStrut(15));

        // Confirmation de mot de Passe
        JLabel confirmeMotDePasse = new JLabel("Confirmer votre mot de passe :");
        confirmeMotDePasse.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField champConfirmeMotDePasse = new JTextField(20);
        champConfirmeMotDePasse.setAlignmentX(Component.CENTER_ALIGNMENT);
        champConfirmeMotDePasse.setMaximumSize(new Dimension(250, 30));

        mainInscription.add(confirmeMotDePasse);
        mainInscription.add(champConfirmeMotDePasse);

        mainInscription.add(Box.createVerticalStrut(20));

        // Role
        JLabel role = new JLabel("Rôle : ");
        JCheckBox admin = new JCheckBox("Admin");
        JCheckBox user = new JCheckBox("User");

        ButtonGroup groupRole = new ButtonGroup();
        groupRole.add(admin);
        groupRole.add(user);

        mainInscription.add(role);
        mainInscription.add(admin);
        mainInscription.add(user);

        mainInscription.add(Box.createVerticalStrut(20));

        //liste provisoire
        String[] listeMagasins = {"Lyon", "Paris", "Marseille"};

        // Magasin
        JLabel magasin = new JLabel("Magasin :");
        magasin.setAlignmentX(Component.CENTER_ALIGNMENT);

        JComboBox<String> comboMagasins = new JComboBox<>(listeMagasins);
        comboMagasins.setMaximumSize(new Dimension(250,30));
        comboMagasins.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel magasinPanel = new JPanel();
        magasinPanel.setLayout(new BoxLayout(magasinPanel, BoxLayout.Y_AXIS));
        magasinPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        magasinPanel.setMaximumSize(new Dimension(300, 70));
        magasinPanel.setPreferredSize(new Dimension(300 ,70));
        magasinPanel.setMinimumSize(new Dimension(300 ,70));

        magasinPanel.add(magasin);
        magasinPanel.add(comboMagasins);

        // n'affiche pas

        magasinPanel.setVisible(true);

        magasin.setVisible(false);
        comboMagasins.setVisible(false);

        user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                magasin.setVisible(true);
                comboMagasins.setVisible(true);
            }
        });

        admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                magasin.setVisible(false);
                comboMagasins.setVisible(false);
            }
        });

        mainInscription.add(magasinPanel);

        mainInscription.add(Box.createVerticalStrut(30));

        JPanel ligneMagasin = new JPanel();
        ligneMagasin.setLayout(new BoxLayout(ligneMagasin, BoxLayout.Y_AXIS));
        ligneMagasin.setMaximumSize(new Dimension(300, 70));
        ligneMagasin.setPreferredSize(new Dimension(300, 70));

        // Ajout d'une ligne de buttons
        JPanel ligneButtonsInscription = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));

        // Ajout du bouton Retour
        JButton retour = new JButton("Retour");

        // Action associée
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main().setVisible(true);
                dispose();
            }
        });

        // Ajout du bouton Inscription
        JButton inscription = new JButton("Inscription");

        // Action associée
        inscription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String mail = champMail.getText();
                String pseudo = champPseudo.getText().trim();
                String mdp = champMotDePasse.getText().trim();
                String mdp2 = champConfirmeMotDePasse.getText().trim();

                if (!mail.endsWith("@supinfo.com")) {
                    JOptionPane.showMessageDialog(null, "adresse mail non valide, veuillez utilisez votre adresse \"@supinfo\" ");
                return;
                }

                if (mail.isEmpty() || pseudo.isEmpty() || mdp.isEmpty() || mdp2.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
                    return;
                }
                if (!mdp.equals(mdp2)) {
                    JOptionPane.showMessageDialog(null, "Les mots de passe ne correspondent pas");
                    return;
                }

                WhitelistDAO whitelistDAO= new WhitelistDAO();
                if (!whitelistDAO.emailExists(mail)) {
                    JOptionPane.showMessageDialog(null, "Cet email n'est pas autorisé");
                    return;
                }

                UserDAO userDAO = new UserDAO();
                if (userDAO.emailExists(mail)) {
                    JOptionPane.showMessageDialog(null, "Cet email est déja utilisé");
                    return;
                }

                String role = admin.isSelected() ? "admin" : "user";

                Integer storeId = null;
                if (user.isSelected()) {
                    storeId = comboMagasins.getSelectedIndex() + 1;
                }

                User user = new User(0, mail, pseudo, mdp, role, storeId);
                boolean create = userDAO.addUser(user);

                if (create) {
                    JOptionPane.showMessageDialog(null, "Inscription réussie !");
                    new Main().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null,"Erreur lors de l'inscription");
                }

            }
        });

        // Ajout des boutons à la ligne
        ligneButtonsInscription.add(retour);
        ligneButtonsInscription.add(inscription);

        // Ajout de la ligne à la fenêtre
        mainInscription.add(ligneButtonsInscription);

        add(mainInscription);
    }
}
