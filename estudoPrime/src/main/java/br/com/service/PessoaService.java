package br.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.model.Pessoa;
import br.com.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;
	
	public Pessoa SalvaPessoa(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	public List<Pessoa> BuscaPessoas() {
		return pessoaRepository.findAll();
	}

	public void DeletaPessoa(Pessoa pessoa) {
		pessoaRepository.delete(pessoa);
	}

	public Optional<Pessoa> BuscaPorId(Long id) {
		return pessoaRepository.findById(id);
	}

}
