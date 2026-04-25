package model;

public class ModelUser {
	private String username;
	private String email;
	private String password;
    // Ajoute d'autres champs si besoin (id, email, etc.)

    public ModelUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    // Ajoute les getters et setters
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}