package domain;

public class Produto {
	private Integer id;
	private String nome;
	private String descricao;
	private Double valor;

	public Produto(Integer id, String nome, String descricao, Double valor) {
		this.id = id;
		this.setNome(nome);
		this.setDescricao(descricao);
		this.setValor(valor);
	}

	// id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// nome
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	// descrição
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	// valor
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}
