package sy.rf.demo.service.impl;


import org.springframework.stereotype.Service;
import sy.rf.demo.dto.ParticipantDto;
import sy.rf.demo.entity.Participant;
import sy.rf.demo.mappers.ParticipantMapper;
import sy.rf.demo.mappers.ProfileMapper;
import sy.rf.demo.mappers.StructureMapper;
import sy.rf.demo.repository.ParticipantRepository;
import sy.rf.demo.service.ParticipantService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantServiceImpl implements ParticipantService {


    private final  ParticipantRepository participantRepository;

   public  ParticipantServiceImpl(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }
    @Override
    public ParticipantDto createParticipant(ParticipantDto participantDto) {
       Participant participant = Participant.builder()
               .email(participantDto.getEmail())
               .nom(participantDto.getNom())
               .prenom(participantDto.getPrenom())
               .formations(participantDto.getFormations())
               .tel(participantDto.getTel())
               .profil(ProfileMapper.toEntity(participantDto.getProfil()))
               .structure(StructureMapper.toEntity(participantDto.getStructure()))
               .build();
        return ParticipantMapper.toDto(participantRepository.save(participant));
    }

    @Override
    public ParticipantDto getParticipantById(Long id) {
        return ParticipantMapper.toDto(participantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participant non trouvé")));
    }

    @Override
    public List<ParticipantDto> getAllParticipants() {
        return participantRepository.findAll().stream().map(ParticipantMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ParticipantDto updateParticipant( ParticipantDto participantDto) {
        Participant participant = Participant.builder()
                .id(participantDto.getId())
                .email(participantDto.getEmail())
                .nom(participantDto.getNom())
                .prenom(participantDto.getPrenom())
                .formations(participantDto.getFormations())
                .tel(participantDto.getTel())
                .profil(ProfileMapper.toEntity(participantDto.getProfil()))
                .structure(StructureMapper.toEntity(participantDto.getStructure()))
                .build();
        return ParticipantMapper.toDto(participantRepository.save(participant));
    }

    @Override
    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }
}
