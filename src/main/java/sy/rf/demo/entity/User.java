    package sy.rf.demo.entity;

    import jakarta.persistence.*;
    import lombok.*;


    @Entity
    @Table(name = "users")
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class User {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nom;
        private String prenom;

        @Column(name = "email", unique = true, nullable = false)
        private String email;

        @Column(name = "encrypted_password", nullable = false)
        private String password;
        @Enumerated(EnumType.STRING)
        private Role role;


    }

