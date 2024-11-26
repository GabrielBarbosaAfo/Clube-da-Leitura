package tp.daw.modelo;

/**
 * Classe responsável por gerenciar os dados de uma Pessoa
 * 
 */
public class Pessoa {

	private Long id;
	private String nome, telefone;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}// class Pessoa
