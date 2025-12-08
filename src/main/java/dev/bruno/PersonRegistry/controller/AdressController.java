package dev.bruno.PersonRegistry.controller;

import dev.bruno.PersonRegistry.dtos.adress.CreateAdressDTO;
import dev.bruno.PersonRegistry.dtos.adress.ListAdressDTO;
import dev.bruno.PersonRegistry.dtos.adress.UpdateAdressDTO;
import dev.bruno.PersonRegistry.service.AdressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Cadastro de Endereços")

@RestController
@RequestMapping("/adress")
public class AdressController {

    private final AdressService adressService;

    public AdressController(AdressService adressService) {
        this.adressService = adressService;
    }

    @Operation(
            summary = "Lista os endereços cadastrados",
            description = "Essa rota lista todos os endereços criados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereços listados com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum endereço encontrado")
    })
    @GetMapping
    public ResponseEntity<List<ListAdressDTO>> adressGetAll() {
        return adressService.adressGetAll().isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(adressService.adressGetAll());
    }

    @Operation(
            summary = "Lista um endereço por id",
            description = "Essa rota lista um endereço por id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Nenhuma pesssa encontrada/cadastrada. Erro interno.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ListAdressDTO> adressGetById(@PathVariable Long id) {
        return adressService.adressById(id) == null ? // Não retorna o NotFound.
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(adressService.adressById(id));
    }

    @Operation(
            summary = "Cria um endereço",
            description = "Essa rota cria um endereço e pode ser atrelado a uma pessoa"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação de um endereço")
    })
    @PostMapping
    public ResponseEntity<CreateAdressDTO> createAdress(@RequestBody CreateAdressDTO createAdressDTO) {
        adressService.createAdress(createAdressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Deleta um endereço",
            description = "Essa rota deleta um endereço por id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteAdress(@PathParam("id") Long id) {
        if(adressService.adressById(id) == null) {
            return ResponseEntity.notFound().build();
        }else{
            adressService.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }

    @Operation(
            summary = "Atualiza um endereço",
            description = "Essa rota atualiza endereço. "
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Pessoa não encontrada com id")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UpdateAdressDTO> updatePerson(@PathVariable Long id, @RequestBody UpdateAdressDTO updateAdressDTO) {
        return adressService.adressById(id) == null ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(adressService.alterAdress(id, updateAdressDTO));
    }
}
