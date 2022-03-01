package com.pragma.endpoints;

import com.pragma.dto.ClientDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IClientController {

    @GetMapping("/clients")
    ResponseEntity<List<ClientDto>> listAllClients();

    @GetMapping("/clientsbyage")
    ResponseEntity<List<ClientDto>> listClientsByAge(
            @RequestParam(name = "age") Integer age);

    @GetMapping("client")
    ResponseEntity<ClientDto> getClient(
            @RequestParam(name = "idType") String idType,
            @RequestParam(name = "idNumber") Long idNumber);

    @PostMapping
    ResponseEntity<ClientDto> registerClient(
            @Valid @RequestBody ClientDto clientDto);

    @PutMapping
    ResponseEntity<ClientDto> updateClient(
            @Valid @RequestBody ClientDto clientDto,
            @RequestParam(name = "idType") String idType,
            @RequestParam(name = "idNumber") Long idNumber);

    @DeleteMapping("client")
    ResponseEntity<String> deleteClient(
            @RequestParam String idType,
            @RequestParam Long idNumber);

}
