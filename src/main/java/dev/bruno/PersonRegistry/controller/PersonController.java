package dev.bruno.PersonRegistry.controller;

import dev.bruno.PersonRegistry.dtos.person.CreatePersonDTO;
import dev.bruno.PersonRegistry.dtos.person.ListPersonDTO;
import dev.bruno.PersonRegistry.dtos.person.UpdatePersonDTO;
import dev.bruno.PersonRegistry.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Cadastro de Pessoas")

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @Operation(
            summary = "Cria uma pessoa",
            description = "Essa rota cria uma pessoa com o seu respectivo endereço."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação de uma pessoa")
    })
    @PostMapping
    public ResponseEntity<CreatePersonDTO> createPerson(@RequestBody CreatePersonDTO newPerson) {
        personService.createPerson(newPerson);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @Operation(
            summary = "Lista pessoas cadastradas",
            description = "Essa rota lista todas as pessoas cadastradas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoas listadas com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma pesssa cadastrada")
    })
    @GetMapping
    public ResponseEntity<List<ListPersonDTO>> personGetAll() {

        return personService.personFindAll().isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(personService.personFindAll());
    }


    @Operation(
            summary = "Lista uma pessoa por id",
            description = "Essa rota lista uma pessoa por id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoas encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ListPersonDTO> personGetById(@PathVariable Long id) {

        return ResponseEntity.ok(personService.personFindById(id));
    }


    @Operation(
            summary = "Deleta uma pessoa por id",
            description = "Essa rota deleta uma pessoa por id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoas deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {

         personService.personDeleteById(id);
         return ResponseEntity.ok().build();
    }


    @Operation(
            summary = "Atualiza uma pessoa",
            description = "Essa rota atualizam apenas o nome e o endereço de uma pessoa"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoas atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UpdatePersonDTO> updatePerson(@PathVariable Long id, @RequestBody UpdatePersonDTO updatePersonDTO) {

        return ResponseEntity.ok(personService.updatePerson(id, updatePersonDTO));
    }
}
