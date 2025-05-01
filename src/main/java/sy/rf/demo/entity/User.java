package sy.rf.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


@Entity
@Table(name = "users", schema = "auth")
@Data
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nom;
    private String prenom;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "encrypted_password", nullable = false)
    private String password;

    // Relation Many-to-One avec Role
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role", nullable = false)
    private Role role;

    // Constructeurs, getters et setters

    public User() {
    }

    public User(UUID id,String nom,String prenom, String email, String password, Role role) {
        this.id = id;
        this.nom=nom;
        this.prenom=prenom;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

