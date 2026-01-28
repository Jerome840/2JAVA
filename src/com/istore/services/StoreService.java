package com.istore.services;

import com.istore.models.Store;
import com.istore.models.User;
import java.util.ArrayList;
import java.util.List;

public class StoreService {
    private List<Store> allStores = new ArrayList<>();


     //Seul l'Admin peut appeler cette méthode pour créer un nouveau magasin.

    public Store createStore(int id, String name) {
        Store newStore = new Store(id, name);
        allStores.add(newStore);
        return newStore;
    }


     //Retourne la liste de tous les magasins (pour l'affichage Admin).

    public List<Store> getAllStores() {
        return allStores;
    }


     //Recherche un magasin par son nom (utile pour l'UI).

    public Store findStoreByName(String name) {
        for (Store s : allStores) {
            if (s.getName().equalsIgnoreCase(name)) return s;
        }
        return null;
    }
}
