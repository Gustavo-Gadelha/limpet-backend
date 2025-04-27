package edu.fafic.limpet.mapper;

import edu.fafic.limpet.dto.PetDTO;
import edu.fafic.limpet.model.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PetMapper {

    PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    PetDTO toDTO(Pet pet);

    Pet toEntity(PetDTO petDTO);
}
