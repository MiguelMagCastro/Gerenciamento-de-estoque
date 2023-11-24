package Produtos;

public class Produtos implements IProdutos {

	private int id;
	private String nome;
	private String descricao;
	private Double valorUnitario;
	private int quantidade;

	public Produtos(int id1, String nome1, String descricao1, Double valorUnitario1, int quantidade1) {
		this.id = id1;
		this.nome = nome1;
		this.descricao = descricao1;
		this.valorUnitario = valorUnitario1;
		this.quantidade = quantidade1;

	}

	// Start Getters
	public int getId() {
		return this.id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public int getQuantidade() {
		return quantidade;
	}
	// End Getters

	@Override
	public void adicaoDeProdutos(int quantidade) {
		this.quantidade += quantidade;
	}

	@Override
	public boolean saidaDeProdutos(int quantidade) {
		if (this.quantidade - quantidade >= 0) {
			this.quantidade -= quantidade;
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "id: " + id + "\nnome: " + nome + "\ndescricao: " + descricao + "\nvalorUnitario: " + valorUnitario
				+ "\nquantidade: " + quantidade;
	}

}
