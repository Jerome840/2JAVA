package dao;

public class User {
    private int id;
    private String email;
    private String pseudo;
    private String password;
    private String role;
    private Integer storeId;

    public User(int id, String email, String pseudo, String password, String role, Integer storeId) {
        this.id = id;
        this.email = email;
        this.pseudo = pseudo;
        this.password = password;
        this.role = role;
        this.storeId = storeId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
