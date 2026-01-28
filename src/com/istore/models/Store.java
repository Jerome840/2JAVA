package com.istore.models;

import java.util.ArrayList;
import java.util.List;


 // Représente un magasin iStore.
 // Relie l'inventaire des produits et les employés autorisés.

public class Store {
    private int id;
    private String name;
    private Inventory inventory;
    private List<User> employees;

    public Store(int id, String name) {
        this.id = id;
        this.name = name;
        // Chaque magasin a son propre inventaire unique
        this.inventory = new Inventory(id);
        this.employees = new ArrayList<>();
    }

    //  Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public Inventory getInventory() { return inventory; }
    public List<User> getEmployees() { return employees; }

    //  Méthodes de gestion


     // Ajoute un utilisateur à la liste des employés du magasin et met également à jour la référence du magasin chez l'utilisateur.

    public void addEmployee(User user) {
        if (user != null && !employees.contains(user)) {
            employees.add(user);
            user.setAssignedStore(this); // Liaison bidirectionnelle
        }
    }


     // Retire un utilisateur de la liste des employés.

    public void removeEmployee(User user) {
        if (employees.remove(user)) {
            user.setAssignedStore(null);
        }
    }

    @Override
    public String toString() {
        return name; // Indispensable pour l'affichage Swing (JList ou JComboBox)
    }
}