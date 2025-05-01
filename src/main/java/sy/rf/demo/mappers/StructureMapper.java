package sy.rf.demo.mappers;


import sy.rf.demo.dto.StructureDto;
import sy.rf.demo.entity.Structure;

public class StructureMapper {

    public static StructureDto toDto(Structure structure) {
        if (structure == null) return null;

        return StructureDto.builder()
                .id(structure.getId())
                .libelle(structure.getLibelle())
                .build();
    }

    public static Structure toEntity(StructureDto structureDto) {
        if (structureDto == null) return null;

        return Structure.builder()
                .id(structureDto.getId())
                .libelle(structureDto.getLibelle())
                .build();
    }
}

