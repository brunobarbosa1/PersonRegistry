package dev.bruno.PersonRegistry.controller;

import dev.bruno.PersonRegistry.dtos.PersonDTO;
import dev.bruno.PersonRegistry.service.PersonService;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<PersonDTO>> findAll() {
        return personService.findAll().isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
                ResponseEntity.ok(personService.findAll());
    }

    @GetMapping()
    public ResponseEntity<PersonDTO> findById(Long id) {
        return personService.findById(id) == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
                ResponseEntity.ok(personService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO newPerson) {
        personService.createPerson(newPerson);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deletePerson(@PathParam("id") Long id) {
        if(personService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
         personService.deleteById(id);
         return ResponseEntity.ok().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        return personService.findById(id) == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
                ResponseEntity.ok(personService.alterPerson(id, personDTO));
    }
}
