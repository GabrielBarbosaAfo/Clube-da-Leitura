package tp.daw.modelo;

/**
 * A classe responsável por gerenciar os dados de uma revista.
 */
public class Revista{

	private Long id;
	private String colecao;
	private long numeroEdicao;
	private int anoRevista;
	private boolean disponibilidade;
	private Caixa caixa;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColecao() {
		return colecao;
	}

	public void setColecao(String colecao) {
		this.colecao = colecao;
	}

	public long getNumeroEdicao() {
		return numeroEdicao;
	}

	public void setNumeroEdicao(long numeroEdicao) {
		this.numeroEdicao = numeroEdicao;
	}

	public int getAnoRevista() {
		return anoRevista;
	}

	public void setAnoRevista(int anoRevista) {
		this.anoRevista = anoRevista;
	}

	public boolean getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(boolean disponibilizade) {
		this.disponibilidade = disponibilizade;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

}// class Revista
