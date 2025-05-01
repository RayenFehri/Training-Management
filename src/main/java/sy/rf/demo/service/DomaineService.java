package sy.rf.demo.service;

import sy.rf.demo.dto.DomaineDto;
import sy.rf.demo.entity.Domaine;

import java.util.List;
import java.util.UUID;

public interface DomaineService {
    DomaineDto createDomaine(DomaineDto domaine);
    DomaineDto getDomaineById(Long id);
    List<DomaineDto> getAllDomaines();
    DomaineDto updateDomaine( DomaineDto domaine);
    void deleteDomaine(Long id);
}
