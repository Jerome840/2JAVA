package com.istore.services;

import com.istore.models.Item;
import com.istore.models.Store;

public class InventoryService {


     // Ajoute ou met à jour un article dans le magasin spécifié.

    public void upsertItem(Store store, Item item) {
        // Si l'article n'est pas déjà dans l'inventaire, on l'ajoute
        if (!store.getInventory().getItems().contains(item)) {
            store.getInventory().addItem(item);
        }
    }


     // Modifie la quantité d'un article.
     //La sécurité (quantité >= 0) est déjà gérée par la classe Item.

    public void updateStock(Item item, int newQuantity) {
        try {
            item.setQuantity(newQuantity);
        } catch (IllegalArgumentException e) {
            // Ici, on pourrait logger l'erreur ou la propager à l'UI
            System.out.println("Erreur de stock : " + e.getMessage());
        }
    }
}
