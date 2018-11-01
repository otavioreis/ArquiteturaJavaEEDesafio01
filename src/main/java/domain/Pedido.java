package domain;

public class Pedido {
	private Integer id;
	private Integer idProduto;
	private String nomeCliente;
	private String enderecoCliente;
	private String emailCliente;

	public Pedido(Integer id, Integer idProduto, String nomeCliente, String enderecoCliente, String emailCliente) {
		this.id = id;
		this.setIdProduto(idProduto);
		this.nomeCliente = nomeCliente;
		this.enderecoCliente = enderecoCliente;
		this.emailCliente = emailCliente;
	}

	// id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// idProduto
	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	// nomeCliente
	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	// enderecoCliente
	public String getEnderecoCliente() {
		return enderecoCliente;
	}

	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	// emailCliente
	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
}
