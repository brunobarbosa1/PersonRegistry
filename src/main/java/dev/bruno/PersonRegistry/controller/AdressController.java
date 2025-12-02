package dev.bruno.PersonRegistry.controller;

import dev.bruno.PersonRegistry.dtos.AdressDTO;
import dev.bruno.PersonRegistry.service.AdressService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/adress")
public class AdressController {

    private final AdressService adressService;

    public AdressController(AdressService adressService) {
        this.adressService = adressService;
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<AdressDTO>> findAll() {
        return adressService.findAll().isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(adressService.findAll());
    }

    @GetMapping()
    public ResponseEntity<AdressDTO> findById(@PathParam("id") Long id) {
        return adressService.findById(id) == null ? ResponseEntity
                .status(HttpStatus.NOT_FOUND).build() :
                ResponseEntity.ok(adressService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<AdressDTO> createAdress(@RequestBody AdressDTO adressDTO) {
        adressService.createPerson(adressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteAdress(@PathParam("id") Long id) {
        if(adressService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            adressService.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdressDTO> updatePerson(@PathVariable Long id, @RequestBody AdressDTO adressDTO) {
        return adressService.findById(id) == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
                ResponseEntity.ok(adressService.alterPerson(id, adressDTO));
    }
}
