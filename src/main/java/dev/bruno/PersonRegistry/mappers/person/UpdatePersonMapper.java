package dev.bruno.PersonRegistry.mappers.person;

import dev.bruno.PersonRegistry.dtos.person.UpdatePersonDTO;
import dev.bruno.PersonRegistry.model.AdressModel;
import dev.bruno.PersonRegistry.model.PersonModel;
import org.springframework.stereotype.Component;

@Component
public class UpdatePersonMapper {

    public PersonModel entityToDto(UpdatePersonDTO updatePersonDTO) {
        return new PersonModel(
                updatePersonDTO.name(),
                updatePersonDTO.adress()
        );
    }

    public void merge(PersonModel personModel, UpdatePersonDTO updatePersonDTO) {
        if (updatePersonDTO.name() != null) {
            personModel.setName(updatePersonDTO.name());
        }
        if (updatePersonDTO.adress() != null) {
            personModel.setAdress(updatePersonDTO.adress());
        }
    }

    public UpdatePersonDTO dtoToEntity(PersonModel personModel) {
        return new UpdatePersonDTO(
                personModel.getName(),
                personModel.getAdressModel()
        );
    }
}
