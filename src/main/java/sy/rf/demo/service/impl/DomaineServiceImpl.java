package sy.rf.demo.service.impl;


import org.springframework.stereotype.Service;
import sy.rf.demo.dto.DomaineDto;
import sy.rf.demo.mappers.DomaineMapper;
import sy.rf.demo.repository.DomaineRepository;
import sy.rf.demo.service.DomaineService;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DomaineServiceImpl implements DomaineService {


    private final  DomaineRepository domaineRepository;

   public DomaineServiceImpl(DomaineRepository domaineRepository) {
        this.domaineRepository = domaineRepository;
    }

    @Override
    public DomaineDto createDomaine(DomaineDto domaine) {
        return DomaineMapper.toDto(domaineRepository.save(DomaineMapper.toEntity(domaine)));
    }

    @Override
    public DomaineDto getDomaineById(Long id) {
        return DomaineMapper.toDto(domaineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Domaine non trouvé")));
    }

    @Override
    public List<DomaineDto> getAllDomaines() {
        return domaineRepository.findAll().stream().map(DomaineMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public DomaineDto updateDomaine( DomaineDto updatedDomaine) {
       if( !domaineRepository.existsById(updatedDomaine.getId())) {
           throw new RuntimeException("Domaine do not exist");
       }
        return DomaineMapper.toDto(domaineRepository.save(DomaineMapper.toEntity(updatedDomaine)));
    }

    @Override
    public void deleteDomaine(Long id) {
        domaineRepository.deleteById(id);
    }
}
