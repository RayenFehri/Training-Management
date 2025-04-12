package sy.rf.demo.service;

import sy.rf.demo.entity.Formateur;

import java.util.List;
import java.util.UUID;

public interface FormateurService {
    Formateur createFormateur(Formateur formateur);
    Formateur getFormateurById(UUID id);
    List<Formateur> getAllFormateurs();
    Formateur updateFormateur(UUID id, Formateur formateur);
    void deleteFormateur(UUID id);
}
