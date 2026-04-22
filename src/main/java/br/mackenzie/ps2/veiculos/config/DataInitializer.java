package br.mackenzie.ps2.veiculos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.mackenzie.ps2.veiculos.model.Veiculo;
import br.mackenzie.ps2.veiculos.repository.VeiculoRepository;

@Component
public class DataInitializer implements CommandLineRunner {
	
	@Autowired
	private VeiculoRepository veiculoRepository;

	@Override
	public void run(String... args) throws Exception {
		
		// Limpar dados anteriores (se houver)
		veiculoRepository.deleteAll();
		
		// Criar e salvar veículos de teste
		Veiculo veiculo1 = new Veiculo();
		veiculo1.setMarca("Toyota");
		veiculo1.setModelo("Corolla");
		veiculo1.setAno(2022);
		veiculo1.setPlaca("ABC1234");
		veiculo1.setCor("Preto");
		veiculoRepository.save(veiculo1);
		
		Veiculo veiculo2 = new Veiculo();
		veiculo2.setMarca("Honda");
		veiculo2.setModelo("Civic");
		veiculo2.setAno(2023);
		veiculo2.setPlaca("XYZ9876");
		veiculo2.setCor("Branco");
		veiculoRepository.save(veiculo2);
		
		Veiculo veiculo3 = new Veiculo();
		veiculo3.setMarca("Ford");
		veiculo3.setModelo("Fiesta");
		veiculo3.setAno(2021);
		veiculo3.setPlaca("DEF5678");
		veiculo3.setCor("Vermelho");
		veiculoRepository.save(veiculo3);
		
		Veiculo veiculo4 = new Veiculo();
		veiculo4.setMarca("Chevrolet");
		veiculo4.setModelo("Onix");
		veiculo4.setAno(2024);
		veiculo4.setPlaca("GHI9012");
		veiculo4.setCor("Prata");
		veiculoRepository.save(veiculo4);
		
		Veiculo veiculo5 = new Veiculo();
		veiculo5.setMarca("Fiat");
		veiculo5.setModelo("Uno");
		veiculo5.setAno(2020);
		veiculo5.setPlaca("JKL3456");
		veiculo5.setCor("Azul");
		veiculoRepository.save(veiculo5);
		
		System.out.println("✓ Dados de teste carregados com sucesso!");
	}

}
