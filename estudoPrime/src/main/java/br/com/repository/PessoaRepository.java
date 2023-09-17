package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
