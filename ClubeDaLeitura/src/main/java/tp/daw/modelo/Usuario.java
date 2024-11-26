package tp.daw.modelo;

/**
 * Classe responsável por gerenciar os dados de um usuário.
 */
public class Usuario {

	private Long id;
	private String usuario, senha;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String login) {
		this.usuario = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}// class Usuario
