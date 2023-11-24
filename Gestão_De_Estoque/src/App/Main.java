package App;

import java.util.Scanner;

import Estoque.Estoque;
import Produtos.Produtos;

public class Main {

	private static Scanner sc = new Scanner(System.in);
	private static Estoque estoque = new Estoque();

	public static void main(String[] args) {
		int escolhaPrincipal;
		do {
			menuPrincipal();
			escolhaPrincipal = validaEscolha();
			escolhaMenuPrincipal(escolhaPrincipal);
		} while (escolhaPrincipal != 0);
	}

	private static void menuPrincipal() {
		System.out.println("|==========================|");
		System.out.println("|      Menu Principal      |");
		System.out.println("|==========================|");
		System.out.printf("|%-26s|\n", "1 -- Menu Produto");
		System.out.printf("|%-26s|\n", "2 -- Menu ...");
		System.out.printf("|%-26s|\n", "3 -- Menu ...");
		System.out.printf("|%-26s|\n", "0 -- Sair ");
		System.out.printf("|%-26s|\n", " ");
		System.out.println("|==========================|");
	}

	private static void escolhaMenuPrincipal(int escolhaPrincipal) {
		switch (escolhaPrincipal) {
		case 1:
			int escolhaProduto;
			do {
				menuProduto();
				escolhaProduto = validaEscolha();
				escolhaMenuProduto(escolhaProduto);
			} while (escolhaProduto != 0);
			break;
		case 2:
			System.out.println("Em desenvolvimento");
			break;
		case 3:
			System.out.println("Em desenvolvimento");
			break;
		case 0:
			System.out.println("Fechando Menu...");
			break;

		default:
			System.out.println("Opção Inválida!");

		}
	}

	private static int validaEscolha() {
		int escolha;
		while (true) {
			try {
				escolha = sc.nextInt();
				break;
			} catch (NumberFormatException e) {
				System.out.println("Digite um número válido");
			}
		}
		return escolha;
	}

	private static void menuProduto() {
		System.out.println("|==========================|");
		System.out.println("|        Menu Produto      |");
		System.out.println("|==========================|");
		System.out.printf("|%-26s|\n", "1 -- Cadastrar Produto");
		System.out.printf("|%-26s|\n", "2 -- Adicionar Produto");
		System.out.printf("|%-26s|\n", "3 -- Remover Produto");
		System.out.printf("|%-26s|\n", "3 -- Remover Produto");
		System.out.printf("|%-26s|\n", "4 -- Visualizar Produto");
		System.out.printf("|%-26s|\n", "0 -- Sair ");
		System.out.printf("|%-26s|\n", " ");
		System.out.println("|==========================|");
	}

	private static void escolhaMenuProduto(int escolha) {
		switch (escolha) {
		case 1:
			cadastraProduto();
			break;
		case 2:
			adicionarProduto();
			break;
		case 3:
			removerProduto();
			break;
		case 4:
			visualizarProduto();
			break;
		case 0:
			System.out.println("Fechando Menu...");
			break;

		default:
			System.out.println("Opção Inválida!");

		}
	}

	private static void cadastraProduto() {
		sc.nextLine();
		System.out.print("\nDigite o Nome do Produto: ");
		String nome = sc.nextLine();
		System.out.print("Digite a Descrição do Produto:");
		String descricao = sc.nextLine();
		System.out.print("Digite o valor Unitario do Produto: ");
		Double valorUnitario = sc.nextDouble();
		System.out.print("Digite a Quantidade do Produto: ");
		int quantidade = sc.nextInt();
		String verify = (estoque.cadastroDeProduto(nome, descricao, valorUnitario, quantidade))
				? "\nCadastrado com sucesso"
				: "\nFalha ao Cadastrar Produto";
		System.out.println(verify);
	}

	private static void adicionarProduto() {
		int id;
		do {
			System.out.print("Digite o Id do produto que deseja adicionar: ");
			id = validaEscolha();
			if (!estoque.verificaExistencia(id)) {
				System.out.println("\nProduto não encontrado!\n");
				break;
			}
		} while (!estoque.verificaExistencia(id));
		int quantidade;
		if (estoque.verificaExistencia(id)) {
			do {
				System.out.print("Digite a quantidade a ser adicionada: ");
				quantidade = validaEscolha();
			} while (quantidade <= 0);

			String verify = (estoque.adicaoDeProdutos(id, quantidade)) ? "Adicionado com sucesso!" : "Falha";
			System.out.println(verify);
		}

	}

	private static void removerProduto() {
		int id;
		do {
			System.out.print("Digite o Id do produto que deseja remover: ");
			id = validaEscolha();
			if (!estoque.verificaExistencia(id)) {
				System.out.println("\nProduto não encontrado!\n");
				break;
			}
		} while (!estoque.verificaExistencia(id));
		int quantidade;
		if (estoque.verificaExistencia(id)) {
			do {
				System.out.print("Digite a quantidade a ser removida: ");
				quantidade = validaEscolha();
			} while (quantidade <= 0);

			String verify = (estoque.saidaDeProdutos(id, quantidade)) ? "Removido com sucesso!" : "Falha";
			System.out.println(verify);
		}
	}

	private static void visualizarProduto() {
		int id;
		do {
			System.out.print("Digite o Id do produto que deseja visualizar: ");
			id = validaEscolha();
			if (!estoque.verificaExistencia(id)) {
				System.out.println("\nProduto não encontrado!\n");
				break;
			}
		} while (!estoque.verificaExistencia(id));
		
		if(estoque.verificaExistencia(id)) {
			String produto = estoque.consultaProduto(id);
			System.out.println(produto);
		}
		

	}
	
	
}
