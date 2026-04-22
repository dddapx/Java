package br.mackenzie.ps2.veiculos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.mackenzie.ps2.veiculos.model.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
	
	Optional<Veiculo> findByPlacaIgnoreCase(String placa);
	
}
