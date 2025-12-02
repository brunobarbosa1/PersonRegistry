package dev.bruno.PersonRegistry.mappers;

import dev.bruno.PersonRegistry.dtos.AdressDTO;
import dev.bruno.PersonRegistry.model.AdressModel;
import org.springframework.stereotype.Component;

@Component
public class AdressMapper {

    public AdressModel mapsEntityToDto(AdressDTO adressDTO) {
        AdressModel adressModel = new AdressModel();
        adressModel.setAdress(adressDTO.getAdress());
        adressModel.setNumber(adressDTO.getNumber());
        adressModel.setNeighborhood(adressDTO.getNeighborhood());
        return adressModel;
    }

    public AdressDTO mapsDtoToEntity(AdressModel adressModel) {
        AdressDTO adressDTO = new AdressDTO();
        adressDTO.setAdress(adressModel.getAdress());
        adressDTO.setNumber(adressModel.getNumber());
        adressDTO.setNeighborhood(adressModel.getNeighborhood());
        return adressDTO;
    }
}
