package dev.bruno.PersonRegistry.mappers.adress;

import dev.bruno.PersonRegistry.dtos.adress.UpdateAdressDTO;
import dev.bruno.PersonRegistry.model.AdressModel;
import org.springframework.stereotype.Component;

@Component
public class UpdateAdressMapper {

    public AdressModel entityToDto(UpdateAdressDTO updateAdressDTO) {
        return new AdressModel(
                updateAdressDTO.adress(),
                updateAdressDTO.number(),
                updateAdressDTO.neighborhood()
        );
    }

    public void merge(AdressModel adressModel, UpdateAdressDTO updateAdressDTO) {
        if (updateAdressDTO.adress() != null) {
            adressModel.setAdress(updateAdressDTO.adress());
        }
        if (updateAdressDTO.neighborhood() != null) {
            adressModel.setNeighborhood(updateAdressDTO.neighborhood());
        }
        if (updateAdressDTO.number() != 0){
            adressModel.setNumber(updateAdressDTO.number());
        }
    }

    public UpdateAdressDTO dtoToEntity(AdressModel adressModel) {
        return new UpdateAdressDTO(
                adressModel.getAdress(),
                adressModel.getNumber(),
                adressModel.getNeighborhood()
        );
    }
}
