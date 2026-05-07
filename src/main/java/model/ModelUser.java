package model;

/**
 * Modèle représentant un utilisateur dans l'application Polysio
 */
public class ModelUser {
    private int id;
    private String username;
    private String email;
    private String password;
    private ModelUserRole role = ModelUserRole.UTILISATEUR; // Je définis la valeur par défaut du role ICI
    private String profilepicture;
    public Object setProfilepicture;
    // --- CONSTRUCTEURS ---

    // Constructeur vide (souvent utile pour certains frameworks)
    public ModelUser() {}

    // Constructeur complet (utilisé lors de la récupération depuis la BDD / Connexion)
    public ModelUser(int id, String username, String email, String password, ModelUserRole roleP) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = roleP;
        this.profilepicture = "/images/avatars/avatar.jpg";
    }

    // Constructeur pour l'inscription (quand on n'a pas encore d'ID)
    public ModelUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;

    }

    // --- GETTERS ---
    
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // --- SETTERS ---
    // Les setters permettent de mettre à jour l'objet en mémoire (Session)
    // après avoir fait une modification en Base de Données.

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public ModelUserRole getRole() {
		return role;
	}

	public void setRole(ModelUserRole role) {
		this.role = role;
	}

	public String getProfilepicture() {
		return profilepicture;
	}

	public void setProfilepicture(String profilepicture) {
		this.profilepicture = profilepicture;
	}

	public Object getSetProfilepicture() {
		return setProfilepicture;
	}

	public void setSetProfilepicture(Object setProfilepicture) {
		this.setProfilepicture = setProfilepicture;
	}

}