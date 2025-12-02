package dev.bruno.PersonRegistry.dtos;

import dev.bruno.PersonRegistry.model.AdressModel;
import lombok.Builder;

@Builder
public class PersonDTO {

    private String name;
    private String email;
    private AdressModel adress;

    public PersonDTO() {
    }

    public PersonDTO(String name, String email, AdressModel adress) {
        this.name = name;
        this.email = email;
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AdressModel getAdress() {
        return adress;
    }

    public void setAdress(AdressModel adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", adress=" + adress +
                '}';
    }
}
