package br.com.projetospring.springdata.repositiry;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.projetospring.springdata.orm.Funcionario;
import br.com.projetospring.springdata.orm.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>,
		JpaSpecificationExecutor<Funcionario> {
	
	List<Funcionario> findByNome(String nome);
	
	/*Alterado para JPQL (Na query utiliza os dados da classe JAVA*/
	/*List<Funcionario> findyByNameAndSalarioGreaterThanAndDataContratacao(String nome, Double salario, LocalDate data);*/
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome " 
			+ "AND f.salario >= :salario and f.dataContratacao = :data")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate data);
	
	/*Consulta utilizando o SELECT da BD (Na query utiliza os dados do BB)*/
	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao > :data",
			nativeQuery = true)
	List<Funcionario> findDataContratacaoMaior(LocalDate data);
	
	@Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario();
	
}
