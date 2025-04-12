package sy.rf.demo.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sy.rf.demo.entity.Participant;
import sy.rf.demo.service.ParticipantService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/participants")
@RequiredArgsConstructor
public class ParticipantRestController {

    @Autowired
    private ParticipantService participantService;

    @PostMapping
    public Participant create(@RequestBody Participant participant) {
        return participantService.createParticipant(participant);
    }

    @GetMapping("/{id}")
    public Participant getOne(@PathVariable UUID id) {
        return participantService.getParticipantById(id);
    }

    @GetMapping
    public List<Participant> getAll() {
        return participantService.getAllParticipants();
    }

    @PutMapping("/{id}")
    public Participant update(@PathVariable UUID id, @RequestBody Participant participant) {
        return participantService.updateParticipant(id, participant);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        participantService.deleteParticipant(id);
    }
}
