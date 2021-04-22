package br.com.projetospring.springdata.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.projetospring.springdata.orm.Cargo;
import br.com.projetospring.springdata.orm.Funcionario;
import br.com.projetospring.springdata.orm.Unidade;
import br.com.projetospring.springdata.repositiry.CargoRepository;
import br.com.projetospring.springdata.repositiry.FuncionarioRepository;
import br.com.projetospring.springdata.repositiry.UnidadeRepository;

@Service
public class CrudFuncionarioService {
	
	private boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final CargoRepository cargoRepository;
	private final FuncionarioRepository funcionarioRepository;
	private final UnidadeRepository unidadeRepository;

	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, 
			UnidadeRepository unidadeRepository,
			CargoRepository cargoRepository) {
		this.funcionarioRepository = funcionarioRepository;
		this.unidadeRepository = unidadeRepository;
		this.cargoRepository = cargoRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual ação de Funcionario deseja executar:");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Vizualizar");
			System.out.println("4 - Deletar");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scanner);
				break;	
			default:
				system = false;
				break;
			}
		}
	}
	
	public void salvar(Scanner scanner) {
		
		System.out.println("Nome: ");
		String nome = scanner.next();
		
		System.out.println("CPF: ");
		String cpf = scanner.next();
		
		System.out.println("Salario: ");
		Double salario = scanner.nextDouble();
		
		System.out.println("Data de contratação: ");
		String dataContratacao = scanner.next();
				
		System.out.println("Digite o cargoId");
		Integer cargoId = scanner.nextInt();
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
	
		funcionarioRepository.save(funcionario);
		System.out.println("Salvo");
	}
	
	public void atualizar(Scanner scanner) {
		System.out.println("Id: ");
		int id = scanner.nextInt();
		
		System.out.println("Nome: ");
		String nome = scanner.next();
		
		System.out.println("CPF: ");
		String cpf = scanner.next();
		
		System.out.println("Salario: ");
		Double salario = scanner.nextDouble();
		
		System.out.println("Data de contratação: ");
		String dataContratacao = scanner.next();
		
		System.out.println("Digite o cargoId");
        Integer cargoId = scanner.nextInt();
		
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());
        
		funcionarioRepository.save(funcionario);
		System.out.println("Atualizado");
	}
	
	public void visualizar() {
		Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
		funcionarios.forEach(funcionario -> System.out.println(funcionario));
	}
	
	public void deletar(Scanner scanner){
		System.out.println("Id: ");
		int id = scanner.nextInt();
		funcionarioRepository.deleteById(id);
		System.out.println("Deletado");
	}
	
}
