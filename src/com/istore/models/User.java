package com.istore.models;

public class User {
    private int id;
    private String email;
    private String pseudo;
    private String password; // Sécurité : mot de passe haché
    private Role role;
    private Store assignedStore;

    public User(int id, String email, String pseudo, String password, Role role) {
        this.id = id;
        this.email = email;
        this.pseudo = pseudo;
        this.password = password;
        this.role = role;
        this.assignedStore = null; // Par défaut, aucun magasin assigné
    }

    // --- Getters ---
    public int getId() { return id; }
    public String getEmail() { return email; }
    public String getPseudo() { return pseudo; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }
    public Store getAssignedStore() { return assignedStore; }

    // --- Setters ---
    public void setPseudo(String pseudo) { this.pseudo = pseudo; }
   public void setPasswordHash(String password) { this.password = password; }
    public void setRole(Role role) { this.role = role; } // Utile pour l'Admin

    public void setAssignedStore(Store assignedStore) {
        this.assignedStore = assignedStore; // Correction ici
    }
}