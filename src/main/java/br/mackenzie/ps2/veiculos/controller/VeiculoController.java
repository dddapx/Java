package br.mackenzie.ps2.veiculos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.mackenzie.ps2.veiculos.model.Veiculo;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {
	
	private static List<Veiculo> veiculos = new ArrayList<>();
	private static Long proximoId = 1L;
	
	static {
		// Dados iniciais para teste
		veiculos.add(new Veiculo(1L, "Toyota", "Corolla", 2022, "ABC1234", "Preto"));
		veiculos.add(new Veiculo(2L, "Honda", "Civic", 2023, "XYZ9876", "Branco"));
		proximoId = 3L;
	}
	
	// GET - Listar todos os veículos
	@GetMapping
	public ResponseEntity<List<Veiculo>> listarTodos() {
		return ResponseEntity.ok(veiculos);
	}
	
	// GET - Buscar veículo por ID
	@GetMapping("/{id}")
	public ResponseEntity<Veiculo> buscarPorId(@PathVariable Long id) {
		Optional<Veiculo> veiculo = veiculos.stream()
			.filter(v -> v.getId().equals(id))
			.findFirst();
		
		return veiculo.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	// GET - Buscar veículo por placa
	@GetMapping("/placa/{placa}")
	public ResponseEntity<Veiculo> buscarPorPlaca(@PathVariable String placa) {
		Optional<Veiculo> veiculo = veiculos.stream()
			.filter(v -> v.getPlaca().equalsIgnoreCase(placa))
			.findFirst();
		
		return veiculo.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	// POST - Criar novo veículo
	@PostMapping
	public ResponseEntity<Veiculo> criar(@RequestBody Veiculo veiculo) {
		veiculo.setId(proximoId++);
		veiculos.add(veiculo);
		return ResponseEntity.status(HttpStatus.CREATED).body(veiculo);
	}
	
	// PUT - Atualizar veículo existente
	@PutMapping("/{id}")
	public ResponseEntity<Veiculo> atualizar(@PathVariable Long id, @RequestBody Veiculo veiculoAtualizado) {
		Optional<Veiculo> veiculoExistente = veiculos.stream()
			.filter(v -> v.getId().equals(id))
			.findFirst();
		
		if (veiculoExistente.isPresent()) {
			Veiculo veiculo = veiculoExistente.get();
			veiculo.setMarca(veiculoAtualizado.getMarca());
			veiculo.setModelo(veiculoAtualizado.getModelo());
			veiculo.setAno(veiculoAtualizado.getAno());
			veiculo.setPlaca(veiculoAtualizado.getPlaca());
			veiculo.setCor(veiculoAtualizado.getCor());
			return ResponseEntity.ok(veiculo);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	// DELETE - Deletar veículo
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		boolean removido = veiculos.removeIf(v -> v.getId().equals(id));
		
		if (removido) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
