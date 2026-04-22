package br.mackenzie.ps2.veiculos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.mackenzie.ps2.veiculos.repository.VeiculoRepository;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	// GET - Listar todos os veículos
	@GetMapping
	public ResponseEntity<List<Veiculo>> listarTodos() {
		return ResponseEntity.ok(veiculoRepository.findAll());
	}
	
	// GET - Buscar veículo por ID
	@GetMapping("/{id}")
	public ResponseEntity<Veiculo> buscarPorId(@PathVariable Long id) {
		Optional<Veiculo> veiculo = veiculoRepository.findById(id);
		return veiculo.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	// GET - Buscar veículo por placa
	@GetMapping("/placa/{placa}")
	public ResponseEntity<Veiculo> buscarPorPlaca(@PathVariable String placa) {
		Optional<Veiculo> veiculo = veiculoRepository.findByPlacaIgnoreCase(placa);
		return veiculo.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	// POST - Criar novo veículo
	@PostMapping
	public ResponseEntity<Veiculo> criar(@RequestBody Veiculo veiculo) {
		Veiculo veiculoSalvo = veiculoRepository.save(veiculo);
		return ResponseEntity.status(HttpStatus.CREATED).body(veiculoSalvo);
	}
	
	// PUT - Atualizar veículo existente
	@PutMapping("/{id}")
	public ResponseEntity<Veiculo> atualizar(@PathVariable Long id, @RequestBody Veiculo veiculoAtualizado) {
		Optional<Veiculo> veiculoExistente = veiculoRepository.findById(id);
		
		if (veiculoExistente.isPresent()) {
			Veiculo veiculo = veiculoExistente.get();
			veiculo.setMarca(veiculoAtualizado.getMarca());
			veiculo.setModelo(veiculoAtualizado.getModelo());
			veiculo.setAno(veiculoAtualizado.getAno());
			veiculo.setPlaca(veiculoAtualizado.getPlaca());
			veiculo.setCor(veiculoAtualizado.getCor());
			return ResponseEntity.ok(veiculoRepository.save(veiculo));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	// DELETE - Deletar veículo
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (veiculoRepository.existsById(id)) {
			veiculoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
