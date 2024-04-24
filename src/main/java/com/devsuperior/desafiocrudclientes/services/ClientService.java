package com.devsuperior.desafiocrudclientes.services;

import com.devsuperior.desafiocrudclientes.dto.ClientDTO;
import com.devsuperior.desafiocrudclientes.entities.Client;
import com.devsuperior.desafiocrudclientes.repositories.ClientRepository;
import com.devsuperior.desafiocrudclientes.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
  private final ClientRepository clientRepository;

  @Autowired
  public ClientService(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Transactional(readOnly = true)
  public Page<ClientDTO> findAll(Pageable pageable) {
    return clientRepository.findAll(pageable).map(this::toDTO);
  }

  @Transactional(readOnly = true)
  public ClientDTO findById(Long id) {
    return toDTO(clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found")));
  }

  @Transactional
  public ClientDTO insert(ClientDTO clientDTO) {
    System.out.println(clientDTO.id());
    Client savedClient = clientRepository.save(toClient(clientDTO));
    return toDTO(savedClient);
  }

  @Transactional
  public ClientDTO update(Long id, ClientDTO clientDTO) {
    try {
      Client client = clientRepository.getReferenceById(id);
      copyDtoToEntity(clientDTO, client);
      return toDTO(clientRepository.save(client));
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("Client not found ");
    }
  }

  @Transactional(propagation = Propagation.SUPPORTS)
  public void delete(Long id) {
    if(!clientRepository.existsById(id)) {
      throw new ResourceNotFoundException("Client not found");
    }
    clientRepository.deleteById(id);
  }

  private void copyDtoToEntity(ClientDTO source, Client destination) {
    destination.setName(source.name());
    destination.setCpf(source.cpf());
    destination.setIncome(source.income());
    destination.setBirthDate(source.birthDate());
    destination.setChildren(source.children());
  }

  private Client toClient(ClientDTO clientDTO) {
    return new Client(
      clientDTO.id(),
      clientDTO.name(),
      clientDTO.cpf(),
      clientDTO.income(),
      clientDTO.birthDate(),
      clientDTO.children()
    );
  }

  private ClientDTO toDTO(Client client) {
    return new ClientDTO(
      client.getId(),
      client.getName(),
      client.getCpf(),
      client.getIncome(),
      client.getBirthDate(),
      client.getChildren()
    );
  }
}
