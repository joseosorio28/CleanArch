package com.pragma.endpoints;

import com.pragma.service.ClientServiceImp;
import com.pragma.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
@Validated
@RequiredArgsConstructor
public class ClientController implements IClientController {

    private final ClientServiceImp clientService;

    @GetMapping("/clients")
    public ResponseEntity<List<ClientDto>> listAllClients() {
        return new ResponseEntity<>(clientService.getClients(), HttpStatus.OK);
    }

    @GetMapping("/clientsbyage")
    public ResponseEntity<List<ClientDto>> listClientsByAge(
            @RequestParam(name = "age") Integer age) {
        return new ResponseEntity<>(clientService.getClientsByAge(age), HttpStatus.OK);
    }

    @GetMapping("client")
    public ResponseEntity<ClientDto> getClient(
            @RequestParam(name = "idType") String idType,
            @RequestParam(name = "idNumber") Long idNumber) {
        return new ResponseEntity<>(clientService.searchClient(idType, idNumber), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDto> registerClient(
            @Valid @RequestBody ClientDto clientDto) {
        clientService.addClient(clientDto);
        return new ResponseEntity<>(clientDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ClientDto> updateClient(
            @Valid @RequestBody ClientDto clientDto,
            @RequestParam(name = "idType") String idType,
            @RequestParam(name = "idNumber") Long idNumber) {
        clientService.updateClient(clientDto, idType, idNumber);
        return new ResponseEntity<>(clientDto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("client")
    public ResponseEntity<String> deleteClient(
            @RequestParam String idType,
            @RequestParam Long idNumber) {
        clientService.deleteClient(idType, idNumber);
        return new ResponseEntity<>("User deleted with id: " +
                idType + " " + idNumber, HttpStatus.ACCEPTED);
    }
}
