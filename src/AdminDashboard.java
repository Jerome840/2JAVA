import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Edition.*;
import affichage.*;


public class AdminDashboard extends JFrame {

    private String pseudo;

    public AdminDashboard() {
        this("Administrateur");
    }

    public AdminDashboard(String pseudo) {
        this.pseudo = pseudo;

        setTitle("Istore - Administration");
        setSize(600, 600);
        setLocation(1400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Panneau administrateur");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel welcome = new JLabel("Bienvenue " + pseudo);
        welcome.setFont(new Font("Arial", Font.PLAIN, 18));
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);


        panel.add(Box.createVerticalStrut(30));
        panel.add(title);
        panel.add(Box.createVerticalStrut(10));
        panel.add(welcome);

        add(panel);

        // Barre de menu
        JMenuBar menuBar = new JMenuBar();

        // Fichier
        JMenu menuFichier = new JMenu("Fichier");
        JMenuItem itemDeconnexion = new JMenuItem("Déconnexion");
        JMenuItem itemQuitter = new JMenuItem("Quitter");

        itemDeconnexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main().setVisible(true);
                dispose();
            }
        });

        itemQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        menuFichier.add(itemDeconnexion);
        menuFichier.add(itemQuitter);

        menuBar.add(menuFichier);

        // Edition
        JMenu menuEdition = new JMenu("Edition");

        JMenuItem itemWhitelist = new JMenuItem("Whitelist");
        JMenuItem itemUtilisateurs = new JMenuItem("Utilisateurs");
        JMenuItem itemMagasins = new JMenuItem("Magasins");
        JMenuItem itemObjets = new JMenuItem("Objets");
        JMenuItem itemInventaires = new JMenuItem("Inventaires");

        itemWhitelist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WhitelistWindow().setVisible(true);
            }
        });

        itemUtilisateurs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UtilisateursWindow().setVisible(true);
            }
        });

        itemMagasins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MagasinsWindow().setVisible(true);
            }
        });

        itemObjets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ObjetsWindow().setVisible(true);
            }
        });

        itemInventaires.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InventairesWindow().setVisible(true);
            }
        });

        menuEdition.add(itemWhitelist);
        menuEdition.add(itemUtilisateurs);
        menuEdition.add(itemMagasins);
        menuEdition.add(itemObjets);
        menuEdition.add(itemInventaires);

        menuBar.add(menuEdition);

        // Affichage
        JMenu menuAffichage = new JMenu("Affichage");

        JMenuItem itemListeWhiteliste = new JMenuItem("Whitelist");
        JMenuItem itemListeUtilisateurs = new JMenuItem("Utilisateurs");
        JMenuItem itemListeMagasins = new JMenuItem("Magasins");
        JMenuItem itemListeObjets = new JMenuItem("Objets");
        JMenuItem itemListeInventaires = new JMenuItem("Inventaires");

        itemListeWhiteliste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListWhitelistWindow().setVisible(true);
            }
        });

        itemListeUtilisateurs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListUtilisateursWindow().setVisible(true);
            }
        });

        itemListeMagasins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListMagasinsWindow().setVisible(true);
            }
        });

        itemListeObjets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListObjetsWindow().setVisible(true);
            }
        });

        itemListeInventaires.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListInventairesWindow().setVisible(true);
            }
        });

        menuAffichage.add(itemListeWhiteliste);
        menuAffichage.add(itemListeUtilisateurs);
        menuAffichage.add(itemListeMagasins);
        menuAffichage.add(itemListeObjets);
        menuAffichage.add(itemListeInventaires);

        menuBar.add(menuAffichage);

        setJMenuBar(menuBar);

    }
}
