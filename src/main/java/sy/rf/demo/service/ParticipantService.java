package sy.rf.demo.service;

import sy.rf.demo.entity.Participant;

import java.util.List;
import java.util.UUID;

public interface ParticipantService {
    Participant createParticipant(Participant participant);
    Participant getParticipantById(UUID id);
    List<Participant> getAllParticipants();
    Participant updateParticipant(UUID id, Participant participant);
    void deleteParticipant(UUID id);
}
