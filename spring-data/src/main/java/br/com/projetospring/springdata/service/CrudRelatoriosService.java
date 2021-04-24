package br.com.projetospring.springdata.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.projetospring.springdata.orm.Funcionario;
import br.com.projetospring.springdata.repositiry.FuncionarioRepository;

@Service
public class CrudRelatoriosService {
	
	private boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final FuncionarioRepository funcionarioRepository;
	
	public CrudRelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual ação de Unidade deseja executar:");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca funcionario por Nome");
			
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				buscarFuncionarioNome(scanner);
				break;
			case 2:
				buscarFuncionarioNomeSalarioMaiorDate(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
	}
	
	public void buscarFuncionarioNome(Scanner scanner) {
		System.out.println("Qual nome deseja pesquisar");
		String nome = scanner.next();
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(System.out::println);
	}
	
	private void buscarFuncionarioNomeSalarioMaiorDate(Scanner scanner) {	
		System.out.println("Qual nome deseja pesquisar");
		String nome = scanner.next();

		System.out.println("Qual data de contratação deseja pesquisar");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);

		System.out.println("Qual salario deseja pesquisar");
		Double salario = scanner.nextDouble();
		
		List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
		list.forEach(System.out::println);
		
	}
	
}
