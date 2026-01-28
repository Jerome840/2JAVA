package com.istore.models;

import java.util.ArrayList;
import java.util.List;


 //Gère la collection d'articles pour un magasin donné.

public class Inventory {
    private int id;
    private List<Item> items;


     // Constructeur de l'inventaire.

    public Inventory(int id) {
        this.id = id;
        this.items = new ArrayList<>();
    }

    // --- Méthodes de gestion ---


     // Permet à l'Admin d'ajouter un nouvel article à l'inventaire.

    public void addItem(Item item) {
        this.items.add(item);
    }


     //Permet à l'Admin de supprimer un article.

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    // --- Getters ---

    public int getId() { return id; }

    //Retourne la liste des articles.

    public List<Item> getItems() {
        return items;
    }
}

