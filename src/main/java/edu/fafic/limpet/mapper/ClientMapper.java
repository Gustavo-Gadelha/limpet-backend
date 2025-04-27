package edu.fafic.limpet.mapper;

import edu.fafic.limpet.dto.ClientDTO;
import edu.fafic.limpet.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDTO toDTO(Client client);

    Client toEntity(ClientDTO clientDTO);
}
