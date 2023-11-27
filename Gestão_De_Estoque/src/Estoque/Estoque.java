package Estoque;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Produtos.Produtos;

public class Estoque {
	final List<Produtos> produtosEmEstoque = new ArrayList<>();
	final List<String> entradas = new ArrayList<>();
	final List<String> saidas = new ArrayList<>();

	/**
	 * <p>
	 * <ul>
	 * Cadastra um Novo produto no sistema, contendo as seguintes informações:
	 * <li>Nome</li>
	 * <li>Descrição</li>
	 * <li>Preço Unitario</li>
	 * <li>Quantidade</li>
	 * </ul>
	 * </p>
	 * 
	 * @param nome           : Nome do produto a ser cadastrado;
	 * @param descricao:     Descrição do produto a ser cadastrado;
	 * @param precoUnitario: Preço Unitario do produto a ser cadastrado;
	 * @param quantidade:    Quantidade do produto a ser cadastrada;
	 * @return <b>true</b> caso consiga cadastrar o produto<br>
	 *         <b>false</b>caso não consiga cadastrar o produto.
	 */
	public boolean cadastroDeProduto(String nome, String descricao, Double precoUnitario, int quantidade) {
		int id = geraId();
		produtosEmEstoque.add(new Produtos(id, nome, descricao, precoUnitario, quantidade));
		if (!produtosEmEstoque.isEmpty()) {
			for (Produtos prod : produtosEmEstoque) {
				if (id == prod.getId()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * <p>
	 * Incrementa o valor da quantidade de um produto
	 * </p>
	 * 
	 * @param id:         Id do produto em questão;
	 * @param quantidade: Quantidade a ser adicionada ao produto em questão;
	 * @param userId:     Id do usuário relacionado;
	 * @param userName:   Nome do usuário relacionado;
	 * @return<b>true</b> caso consiga incrementar ao produto<br>
	 *                    <b>false</b>caso não consiga incrementar ao produto.
	 */
	public boolean adicaoDeProdutos(int id, int quantidade, int userId, String userName) {
		for (Produtos prod : produtosEmEstoque) {
			if (id == prod.getId()) {
				prod.adicaoDeProdutos(quantidade);
				registroDeAdicao(id, quantidade, userId, userName);
				return true;
			}
		}
		return false;
	}

	/**
	 * <p>
	 * Verifica a existencia de um produto com base no Id informado
	 * </p>
	 * 
	 * @param id: Id a ser buscado;
	 * @return <b>true</b> caso consiga encontrar o produto<br>
	 *         <b>false</b>caso não consiga encontrar o produto.
	 */
	public boolean verificaExistencia(int id) {
		if (!produtosEmEstoque.isEmpty()) {
			for (Produtos prod : produtosEmEstoque) {
				if (id == prod.getId()) {
					return true;
				}
			}
		}
		return false;
	}

	private void registroDeAdicao(int id, int quantidade, int userId, String userName) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String data = LocalDate.now().format(formatter);
		String nome = null;
		for (Produtos prod : produtosEmEstoque) {
			if (id == prod.getId()) {
				nome = prod.getNome();
			}
		}
		entradas.add("Foram adicionadas " + quantidade + " unidades de " + nome + ", no dia: " + data
				+ "\nPelo forncedor: " + userName + ", id: " + userId);
	}

	/**
	 * <p>
	 * Diminui o valor da quantidade de um produto
	 * </p>
	 * 
	 * @param id:         Id do produto em questão;
	 * @param quantidade: Quantidade a ser removida ao produto em questão;
	 * @param userId:     Id do usuário relacionado;
	 * @param userName:   Nome do usuário relacionado;
	 * @return<b>true</b> caso consiga remover a quantidade<br>
	 *                    <b>false</b>caso não consiga remover a quantidade.
	 */
	public boolean saidaDeProdutos(int id, int quantidade, int userId, String userName) {
		for (Produtos prod : produtosEmEstoque) {
			if (id == prod.getId()) {
				prod.saidaDeProdutos(quantidade);
				registroDeSaida(id, quantidade, userId, userName);
				return true;
			}
		}
		return false;
	}

	private void registroDeSaida(int id, int quantidade, int userId, String userName) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String data = LocalDate.now().format(formatter);
		String nome = null;
		for (Produtos prod : produtosEmEstoque) {
			if (id == prod.getId()) {
				nome = prod.getNome();
			}
		}
		saidas.add("Foram vendidas " + quantidade + " unidades de " + nome + ", no dia: " + data + "\nPara o cliente: "
				+ userName + ", id: " + userId);
	}

	/**
	 * <p>
	 * Busca um produto com base no id
	 * </p>
	 * 
	 * @param id: id do produto desejado;
	 * @return Uma String contendo as informações do produto.<br>
	 *         <b>null</b> caso não encontre o produto.
	 */
	public String consultaProduto(int id) {
		for (Produtos prod : produtosEmEstoque) {
			if (prod.getId() == id) {
				return prod.toString();
			}
		}
		return null;
	}

	private int geraId() {
		int maiorID = 0;
		if (produtosEmEstoque.isEmpty()) {
			return 1;
		} else {
			for (Produtos a : produtosEmEstoque) {
				if (a.getId() > maiorID) {
					maiorID = a.getId();
				}
			}
			return (maiorID + 1);
		}
	}

	public List<String> getRegistroDeEntrada() {
		return this.entradas;
	}

	public List<String> getRegistroDeSaida() {
		return this.saidas;
	}

	public List<Produtos> getProdutosEmEstoque() {
		return produtosEmEstoque;
	}

	public boolean verificaQuantidadeRemocao(int quantidade, int id) {
		if (!produtosEmEstoque.isEmpty()) {
			for (Produtos prod : produtosEmEstoque) {
				if (id == prod.getId()) {
					if (prod.getQuantidade() - quantidade >= 0) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public String getNameById(int id){
		for(Produtos a:produtosEmEstoque) {
			if(id == a.getId()) {
				return a.getNome();
			}
		}
		return null;
	}
	
	public Double calculaValorDaVenda(int quantidade, int id) {
		for(Produtos a : produtosEmEstoque) {
			if(a.getId() == id) {
				return (Double) a.getValorUnitario() * quantidade;
			}
		}
		return 0.00;
	}
	
}
