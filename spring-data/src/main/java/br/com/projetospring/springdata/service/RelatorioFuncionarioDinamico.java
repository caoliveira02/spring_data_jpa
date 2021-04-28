package br.com.projetospring.springdata.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;

import Specification.SpecificationFuncionario;
import br.com.projetospring.springdata.orm.Funcionario;
import br.com.projetospring.springdata.repositiry.FuncionarioRepository;

public class RelatorioFuncionarioDinamico {

	private final FuncionarioRepository funcionarioRepository;
	
	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial (Scanner scanner) {
		System.out.println("Digite o nome: ");
		String nome = scanner.next();
		
		List<Funcionario> funcionarios = funcionarioRepository.findAll(Specification.where(SpecificationFuncionario.nome(nome)));
	}
}
