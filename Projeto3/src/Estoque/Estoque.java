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

	public boolean adicaoDeProdutos(int id, int quantidade) {
		for (Produtos prod : produtosEmEstoque) {
			if (id == prod.getId()) {
				prod.adicaoDeProdutos(quantidade);
				registroDeAdicao(id,quantidade);
				return true;
			}
		}
		return false;
	}

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

	private void registroDeAdicao(int id, int quantidade) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String  data = LocalDate.now().format(formatter);
		String nome = null;
		for (Produtos prod : produtosEmEstoque) {
			if (id == prod.getId()) {
				nome = prod.getNome();
			}
		}
		entradas.add("Foi adicionado(a) " + quantidade + " unidades de " + nome + ", no dia: " + data);
	}

	public boolean saidaDeProdutos(int id, int quantidade) {
		for(Produtos prod: produtosEmEstoque) {
			if (id == prod.getId()) {
				if(prod.getQuantidade() - quantidade >= 0) {
					prod.saidaDeProdutos(quantidade);
					registroDeSaida(id,quantidade);
					return true;
				}
			}
		}
		return false;
	}
	
	private void registroDeSaida(int id, int quantidade) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String  data = LocalDate.now().format(formatter);
		String nome = null;
		for (Produtos prod : produtosEmEstoque) {
			if (id == prod.getId()) {
				nome = prod.getNome();
			}
		}
		saidas.add("Foram vendidos: "+ quantidade + "  unidades de " + nome + ", no dia: " + data );
	}
	
	public Produtos consultaProduto(int id) {
		for(Produtos prod:produtosEmEstoque) {
			if(prod.getId() == id) {
				return prod;
			}
		}
		return null;
	}
	
	private int geraId() {
		int newId = 0;
		if (produtosEmEstoque.isEmpty()) {
			return 1;
		}
		for (Produtos a : produtosEmEstoque) {
			if (a.getId() > newId) {
				newId = a.getId();
			}
		}
		return newId;
	}

}
