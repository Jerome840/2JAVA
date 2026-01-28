package com.istore.auth;

import java.security.MessageDigest;
import java.util.Base64;

public class PasswordHasher {

     // Transforme un mot de passe en hash SHA-256.

    public static String hash(String password) {
        try {
            // Utilisation de l'algorithme SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes("UTF-8"));

            // On convertit les octets en texte lisible (Base64) pour le stockage
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (Exception e) {
            throw new RuntimeException("Erreur critique lors du hachage", e);
        }
    }
}