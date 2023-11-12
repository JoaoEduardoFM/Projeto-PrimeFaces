package br.com.repository;

import br.com.model.Pessoa;

public interface IDaoPessoa {
	
	Pessoa consultarUsuario(String login, String senha);
}
