package br.com.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.dao.DaoGeneric;
import br.com.model.Pessoa;
import br.com.repository.IDaoPessoa;
import br.com.repository.IDaoPessoaimpl;
import br.com.service.PessoaService;

@ViewScoped
@ManagedBean(name = "pessoabean")
public class pessoaBean { // Altere o nome da classe para corresponder ao nome do bean

	private Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
	private List<Pessoa> pessoas = new ArrayList<>();
	
	IDaoPessoa daoPessoa = new IDaoPessoaimpl();
	
	@Autowired PessoaService pessoaService;
	
	public String salvar() {
		pessoa = daoGeneric.merge(pessoa);
		carregarPessoas();
		return "";
	}

	public String novo() {
		pessoa = new Pessoa();
		return "";
	}

	public String remove() {
		daoGeneric.deletePorId(pessoa);
		carregarPessoas();
		pessoa = new Pessoa();

		return "";
	}
	
	public String logar() {
		Pessoa pessoaUser = daoPessoa.consultarUsuario(pessoa.getLogin(), pessoa.getSenha());	
		if(pessoaUser != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("usuarioLogado", pessoaUser.getLogin());
			
			return "/primeirapagina.xhtml";
		}
		
		return "index.xhtml";
	}
	
	

	public Pessoa getPessoa() {
		return pessoa;
	}

	@PostConstruct
	public void carregarPessoas() {
		pessoas = daoGeneric.listaEntidade(Pessoa.class);
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
