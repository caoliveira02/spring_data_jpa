package br.com.projetospring.springdata.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.projetospring.springdata.orm.Funcionario;
import br.com.projetospring.springdata.repositiry.FuncionarioRepository;

@Service
public class CrudRelatoriosService {
	
	private boolean system = true;
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
}
