package sy.rf.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sy.rf.demo.entity.Participant;
import sy.rf.demo.repository.ParticipantRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    @Override
    public Participant getParticipantById(UUID id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participant non trouvé"));
    }

    @Override
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    @Override
    public Participant updateParticipant(UUID id, Participant updatedParticipant) {
        Participant participant = getParticipantById(id);
        participant.setNom(updatedParticipant.getNom());
        participant.setPrenom(updatedParticipant.getPrenom());
        participant.setEmail(updatedParticipant.getEmail());
        participant.setTel(updatedParticipant.getTel());
        participant.setStructure(updatedParticipant.getStructure());
        participant.setProfil(updatedParticipant.getProfil());
        participant.setFormations(updatedParticipant.getFormations());
        return participantRepository.save(participant);
    }

    @Override
    public void deleteParticipant(UUID id) {
        participantRepository.deleteById(id);
    }
}
