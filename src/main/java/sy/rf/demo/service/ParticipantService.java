package sy.rf.demo.service;

import sy.rf.demo.dto.ParticipantDto;
import sy.rf.demo.entity.Participant;

import java.util.List;
import java.util.UUID;

public interface ParticipantService {
    ParticipantDto createParticipant(ParticipantDto participant);
    ParticipantDto getParticipantById(Long id);
    List<ParticipantDto> getAllParticipants();
    ParticipantDto updateParticipant( ParticipantDto participant);
    void deleteParticipant(Long id);
}
