package com.istore.auth;

import com.istore.models.User;
import com.istore.models.Role;
import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private List<User> users = new ArrayList<>();
    private List<String> whitelist = new ArrayList<>();

    public AuthService() {
        // On ajoute un admin par défaut pour pouvoir tester l'application
        String adminEmail = "admin@istore.com";
        whitelist.add(adminEmail);
        users.add(new User(1, adminEmail, "SuperAdmin", PasswordHasher.hash("admin123"), Role.ADMIN));
    }


     //Ajoute un email à la liste autorisée (Seul l'Admin pourra appeler ça plus tard).

    public void addToWhitelist(String email) {
        if (!whitelist.contains(email)) {
            whitelist.add(email);
        }
    }


     //Tente d'inscrire un nouvel employé.
     // Respecte la règle : "L'email doit être dans la whitelist".

    public boolean register(String email, String password, String pseudo) {
        if (!whitelist.contains(email)) {
            System.out.println("Inscription refusée : Email non autorisé.");
            return false;
        }

        // On ne stocke que le hash !
        String securePassword = PasswordHasher.hash(password);
        User newUser = new User(users.size() + 1, email, pseudo, securePassword, Role.EMPLOYEE);
        users.add(newUser);
        return true;
    }


     // Vérifie les identifiants lors de la connexion.

    public User login(String email, String password) {
        String attemptHash = PasswordHasher.hash(password);

        for (User u : users) {
            if (u.getEmail().equals(email) && u.getPasswordHash().equals(attemptHash)) {
                return u; // Connexion réussie
            }
        }
        return null; // Échec de connexion
    }
}
