package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {
	
	private ClienteRepository clienteRepository;
	private CatalogoClienteService catalogoClienteService;
	
	@GetMapping   //LISTAR TODOS OS CLIENTES
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	@GetMapping("/{clienteId}") // BUSCAR CLIENTE PELO ID
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		return clienteRepository.findById(clienteId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping	// ADICIONAR UM CLIENTE
	@ResponseStatus(HttpStatus.CREATED) //remove o 200/ok e coloca o 201/created
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {   
		return catalogoClienteService.salvar(cliente);
	}
	
	@PutMapping("/{clienteId}")   // ATUALIZAR CLIENTE
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, 
			@RequestBody Cliente cliente){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId); //forçar atualização do cliente sem adicionar um novo
		cliente = catalogoClienteService.salvar(cliente); 
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")   //EXCLUIR UM CLIENTE
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		catalogoClienteService.excluir(clienteId);
		
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	
	
	
}
