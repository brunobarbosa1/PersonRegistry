package dev.bruno.PersonRegistry.dtos;

import dev.bruno.PersonRegistry.model.PersonModel;
import lombok.Builder;
import java.util.List;

@Builder
public class AdressDTO {

    private String adress;
    private short number;
    private String neighborhood;
    private List<PersonModel> person;

    public AdressDTO(){
    }

    public AdressDTO(String adress, short number, String neighborhood,  List<PersonModel> person) {

        this.adress = adress;
        this.number = number;
        this.neighborhood = neighborhood;
        this.person = person;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public short getNumber() {
        return number;
    }

    public void setNumber(short number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public List<PersonModel> getPerson() {
        return person;
    }

    public void setPerson(List<PersonModel> person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "AdressModel{" +
                ", adress='" + adress + '\'' +
                ", number=" + number +
                ", neighborhood='" + neighborhood + '\'' +
                ", person=" + person +
                '}';
    }
}
