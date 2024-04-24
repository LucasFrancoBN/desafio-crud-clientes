package com.devsuperior.desafiocrudclientes.controllers;

import com.devsuperior.desafiocrudclientes.dto.ClientDTO;
import com.devsuperior.desafiocrudclientes.entities.Client;
import com.devsuperior.desafiocrudclientes.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientController {

  private final ClientService clientService;

  @Autowired
  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping
  public ResponseEntity<Page<ClientDTO>> findAll(
      @PageableDefault(page = 0, size = 6, sort = "name") Pageable pageable
  ) {
    return ResponseEntity.ok(clientService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok(clientService.findById(id));
  }

  @PostMapping
  public ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO clientDTO) {
    ClientDTO savedClient = clientService.insert(clientDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedClient.id()).toUri();
    return ResponseEntity.created(uri).body(savedClient);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ClientDTO> update(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO) {
    ClientDTO updatedClient = clientService.update(id, clientDTO);
    return ResponseEntity.ok(updatedClient);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    clientService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
