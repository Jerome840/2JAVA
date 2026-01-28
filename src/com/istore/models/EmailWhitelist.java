package com.istore.models;

package com.istore.auth;

import java.util.HashSet;
import java.util.Set;

public class EmailWhitelist {
    private Set<String> authorizedEmails;

    public EmailWhitelist() {
        this.authorizedEmails = new HashSet<>();
    }

    // Seul l'Admin peut ajouter des emails
    public void addEmail(String email) {
        authorizedEmails.add(email.toLowerCase());
    }

    // Vérification obligatoire avant la création de compte
    public boolean isWhitelisted(String email) {
        return authorizedEmails.contains(email.toLowerCase());
    }
}