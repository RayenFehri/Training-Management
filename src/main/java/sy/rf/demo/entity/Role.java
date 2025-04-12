    package sy.rf.demo.entity;


    import jakarta.persistence.*;
    import lombok.Data;
    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

    import java.util.UUID;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Entity
    @Table(name = "role")
    @Data
    public class Role {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        private String nom; // par exemple: "simple utilisateur", "responsable", "administrateur"


        public Role(){}
        public Role(UUID id, String nom) {
            this.id = id;
            this.nom = nom;
        }


        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }
    }

