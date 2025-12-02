package dev.bruno.PersonRegistry.mappers;

import dev.bruno.PersonRegistry.dtos.PersonDTO;
import dev.bruno.PersonRegistry.model.PersonModel;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public PersonModel mapsEntityToDto(PersonDTO personDTO) {
        PersonModel personModel = new PersonModel();
        personModel.setName(personDTO.getName());
        personModel.setEmail(personDTO.getEmail());
        personModel.setAdress(personDTO.getAdress());
        return personModel;
    }

    public PersonDTO mapsDtoToEntity(PersonModel personModel) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(personModel.getName());
        personDTO.setEmail(personModel.getEmail());
        personDTO.setAdress(personModel.getAdress());
        return personDTO;
    }
}
