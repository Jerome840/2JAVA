import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inscription extends JFrame {

    public Inscription() {
        setTitle("Inscription");
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
        JTextField champMail = new JTextField(20);
        champMail.setMaximumSize(new Dimension(250, 30));
        mainInscription.add(email);
        mainInscription.add(champMail);

        mainInscription.add(Box.createVerticalStrut(15));

        // Pseudo
        JLabel pseudo = new JLabel("Entrez votre pseudo :");
        JTextField champPseudo = new JTextField(20);
        champPseudo.setMaximumSize(new Dimension(250, 30));
        mainInscription.add(pseudo);
        mainInscription.add(champPseudo);

        mainInscription.add(Box.createVerticalStrut(15));

        //Mot de Passe
        JLabel motDePasse = new JLabel("Entrez votre mot de passe :");
        JTextField champMotDePasse = new JTextField(20);
        champMotDePasse.setMaximumSize(new Dimension(250, 30));
        mainInscription.add(motDePasse);
        mainInscription.add(champMotDePasse);

        mainInscription.add(Box.createVerticalStrut(15));

        // Confirmation de mot de Passe
        JLabel confirmeMotDePasse = new JLabel("Confirmer votre mot de passe :");
        JTextField champConfirmeMotDePasse = new JTextField(20);
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

        // Magasin
        JLabel magasin = new JLabel("Magasin :");
        //liste provisoire
        String[] listeMagasins = {"Lyon", "Paris", "Marseille"};

        JComboBox<String> comboMagasins = new JComboBox<>(listeMagasins);
        comboMagasins.setMaximumSize(new Dimension(250,30));

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
        mainInscription.add(magasin);
        mainInscription.add(comboMagasins);

        add(mainInscription);
    }
}
