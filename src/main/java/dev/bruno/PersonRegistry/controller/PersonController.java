package dev.bruno.PersonRegistry.controller;

import dev.bruno.PersonRegistry.dtos.person.CreatePersonDTO;
import dev.bruno.PersonRegistry.dtos.person.ListPersonDTO;
import dev.bruno.PersonRegistry.dtos.person.UpdatePersonDTO;
import dev.bruno.PersonRegistry.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
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
            description = "Essa rota cria uma pessoa com o seu respectivo endereço e insere no Banco de Dados."
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
            description = "Essa rota lista todas as pessoas criadas"
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
            @ApiResponse(responseCode = "404", description = "Nenhuma pesssa encontrada/cadastrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ListPersonDTO> personGetById(@PathVariable Long id) {
        return personService.personFindById(id) == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
                ResponseEntity.ok(personService.personFindById(id));
    }

    @Operation(
            summary = "Deleta uma pessoa por id",
            description = "Essa rota deleta uma pessoa por id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoas deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @DeleteMapping
    public ResponseEntity<Void> deletePerson(

            @Parameter(description = "Usuário insere o id da pessoa que deseja deletar")
            @PathParam("id") Long id
    ) {

        if(personService.personFindById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
         personService.personDeleteById(id);
         return ResponseEntity.ok().build();
        }
    }

    @Operation(
            summary = "Atualiza uma pessoa (nome ou endereço)",
            description = "Essa rota atualiza o nome ou endereço de uma pessoa"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoas atualizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Pessoa não encontrada com id")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UpdatePersonDTO> updatePerson(
            @Parameter(description = "Usuário insere o id da pessoa que deseja deletar")
            @PathVariable Long id,
            @Parameter(description = "Usuário insere o novo nome ou endereço para atualizar")
            @RequestBody UpdatePersonDTO updatePersonDTO
    ) {
        return personService.personFindById(id) == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
                ResponseEntity.ok(personService.updatePerson(id, updatePersonDTO));
    }
}
