package App;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Estoque.Estoque;
import Produtos.Produtos;
import Usuarios.UserControler;
import Usuarios.Usuarios;

public class Main {

	private static Scanner sc = new Scanner(System.in);
	private static Estoque estoque = new Estoque();
	private static UserControler user = new UserControler();

	public static void main(String[] args) {
		int escolhaPrincipal;
		do {
			menuPrincipal();
			escolhaPrincipal = validaEscolha();
			escolhaMenuPrincipal(escolhaPrincipal);
		} while (escolhaPrincipal != 0);
	}

	private static void menuPrincipal() {
		System.out.println("|==============================|");
		System.out.println("|        Menu Principal        |");
		System.out.println("|==============================|");
		System.out.printf("|%-30s|\n", "1 -- Menu Produto");
		System.out.printf("|%-30s|\n", "2 -- Menu Atividades");
		System.out.printf("|%-30s|\n", "3 -- Menu Usuarios");
		System.out.printf("|%-30s|\n", "0 -- Sair ");
		System.out.printf("|%-30s|\n", " ");
		System.out.println("|==============================|");
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
			int escolhaAtividades;
			do {
				menuAtividades();
				escolhaAtividades = validaEscolha();
				escolhaMenuAtividades(escolhaAtividades);
			} while (escolhaAtividades != 0);
			break;
		case 3:
			int escolhaUsuarios;
			do {
				menuUsuarios();
				escolhaUsuarios = validaEscolha();
				escolhaMenuUsuarios(escolhaUsuarios);
			} while (escolhaUsuarios != 0);
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
			} catch (InputMismatchException e) {
				System.out.print("Digite um número válido: ");
				sc.nextLine();
			}
		}
		return escolha;
	}

	private static Double validaPreco() {
		Double preco;
		while (true) {
			try {
				preco = sc.nextDouble();
				break;
			} catch (InputMismatchException e) {
				System.out.print("Digite um número válido: ");
				sc.nextLine();
			}
		}
		return preco;
	}

	private static int validaQuantidade() {
		int quantidade;
		while (true) {
			try {
				quantidade = sc.nextInt();
				if (quantidade > 0) {
					break;
				} else {
					System.out.print("Digite uma quantidade válida: ");
				}

			} catch (InputMismatchException e) {
				System.out.print("Digite um número válido: ");
				sc.nextLine();
			}
		}
		return quantidade;
	}

	private static void menuProduto() {
		System.out.println("|==============================|");
		System.out.println("|          Menu Produto        |");
		System.out.println("|==============================|");
		System.out.printf("|%-30s|\n", "1 -- Cadastrar Produto");
		System.out.printf("|%-30s|\n", "2 -- Adicionar Produto");
		System.out.printf("|%-30s|\n", "3 -- Remover Produto");
		System.out.printf("|%-30s|\n", "4 -- Visualizar Produto");
		System.out.printf("|%-30s|\n", "0 -- Voltar ");
		System.out.printf("|%-30s|\n", " ");
		System.out.println("|==============================|");
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
			System.out.println("Voltando para o Menu Principal...");
			break;

		default:
			System.out.println("Opção Inválida!");

		}
	}

	private static void cadastraProduto() {
		boolean cadastro;
		do {
			sc.nextLine();
			System.out.printf("\nNome: ");
			String nome = sc.nextLine();
			System.out.printf("\nDescrição: ");
			String descricao = sc.nextLine();
			System.out.printf("\nValor Unitario: ");
			Double valorUnitario = validaPreco();
			System.out.printf("\nQuantidade Disponivel: ");
			int quantidade = validaQuantidade();
			cadastro = estoque.cadastroDeProduto(nome, descricao, valorUnitario, quantidade);
			String verify = (cadastro) ? "\nProduto cadastrado com sucesso" : "\nFalha ao Cadastrar Produto";
			System.out.println(verify);
		} while (!cadastro);
	}

	private static void adicionarProduto() {
		int id;
		int userId;
		int quantidade;

		System.out.print("\nDigite a quantidade a ser adicionada: ");
		quantidade = validaQuantidade();

		System.out.print("\nDigite o Id do produto: ");
		id = validaEscolha();

		if (estoque.verificaExistencia(id)) {
			System.out.print("\nDigite o Id do usuário responsável: ");
			userId = validaEscolha();

			if (user.verificaExistenciaDeUsuario(userId) && user.verificaTipoDeUsuario(userId).equals("Fornecedor")) {
				String userName = user.getNameById(userId);
				String verify = (estoque.adicaoDeProdutos(id, quantidade, userId, userName))
						? "\nAdicionado com sucesso!\n"
						: "";
				System.out.println(verify);
				String prodName = estoque.getNameById(id);

				user.userRegistros(prodName, id, quantidade, userId, 0.00);

			} else {
				if (!user.verificaExistenciaDeUsuario(userId)) {
					System.out.println("\nUsuário não encontrado!\n");
				} else {
					System.out.println("\nUsuário não é um Fornecedor!\n");
				}
			}
		} else {
			System.out.println("\nProduto não encontrado!\n");
		}
	}

	private static void removerProduto() {
		int id;
		int userId;
		int quantidade;

		System.out.print("\nDigite o Id do produto: ");
		id = validaEscolha();
		if (estoque.verificaExistencia(id)) {

			do {
				System.out.print("\nDigite a quantidade vendida: ");
				quantidade = validaQuantidade();
				if (!estoque.verificaQuantidadeRemocao(quantidade, id)) {
					System.out.println("\nA quantidade digitada é maior que a quantidade no estoque!");
				}
			} while (!estoque.verificaQuantidadeRemocao(quantidade, id));

			System.out.print("\nDigite o Id do usuário responsável: ");
			userId = validaEscolha();

			if (user.verificaExistenciaDeUsuario(userId) && user.verificaTipoDeUsuario(userId).equals("Cliente")) {
				String nome = user.getNameById(userId);
				String verify = (estoque.saidaDeProdutos(id, quantidade, userId, nome))
						? "Venda registrada com sucesso!\n"
						: "Falha";
				System.out.println(verify);
				String prodName = estoque.getNameById(id);
				
				Double totalCompra = estoque.calculaValorDaVenda(quantidade, id);
				
				user.userRegistros(prodName, id, quantidade, userId, totalCompra);
			}

			else {
				if (!user.verificaExistenciaDeUsuario(userId)) {
					System.out.println("\nUsuário não encontrado!\n");
				} else {
					System.out.println("\nUsuário não é um Cliente!\n");
				}
			}
		} else {
			System.out.println("\nProduto não encontrado!\n");
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

		if (estoque.verificaExistencia(id)) {
			String produto = estoque.consultaProduto(id);
			System.out.println(produto);
		}

	}

	private static void menuAtividades() {
		System.out.println("|==============================|");
		System.out.println("|        Menu Atividades       |");
		System.out.println("|==============================|");
		System.out.printf("|%-30s|\n", "1 -- Registro Abastecimento");
		System.out.printf("|%-30s|\n", "2 -- Registro de Vendas");
		System.out.printf("|%-30s|\n", "3 -- Ver Estoque Completo");
		System.out.printf("|%-30s|\n", "0 -- Voltar ");
		System.out.printf("|%-30s|\n", " ");
		System.out.println("|==============================|");
	}

	private static void escolhaMenuAtividades(int escolha) {
		switch (escolha) {
		case 1:
			registroAbastecimento();
			break;
		case 2:
			registroVendas();
			break;
		case 3:
			mostraEstoqueCompleto();
			break;
		case 0:
			System.out.println("Voltando para o Menu Principal...");
			break;

		default:
			System.out.println("Opção Inválida!");

		}
	}

	private static void registroAbastecimento() {
		List<String> entrada = estoque.getRegistroDeEntrada();
		System.out.println("Abastecimentos registrados: " + entrada.size() + "\n");
		if (entrada.isEmpty()) {
			System.out.println("Registro vazio.");
		} else {
			for (String a : entrada) {
				System.out.println(a);
			}
		}

	}

	private static void registroVendas() {
		List<String> saida = estoque.getRegistroDeSaida();
		System.out.println("Ações registradas: " + saida.size() + "\n");
		if (saida.isEmpty()) {
			System.out.println("Registro vazio.");
		} else {
			for (String a : saida) {
				System.out.println(a);
			}
		}
	}

	private static void mostraEstoqueCompleto() {
		List<Produtos> estoqueTodo = estoque.getProdutosEmEstoque();
		System.out.println("Total de itens no Estoque: " + estoqueTodo.size() + "\n");
		if (estoqueTodo.isEmpty()) {
			System.out.println("Estoque vazio!");
		} else {
			for (Produtos a : estoqueTodo) {
				a.toString();

				System.out.println(a);
				System.out.println("\n");
			}
		}

	}

	private static void menuUsuarios() {
		System.out.println("|==============================|");
		System.out.println("|          Menu Usuários       |");
		System.out.println("|==============================|");
		System.out.printf("|%-30s|\n", "1 -- Adicionar usuário");
		System.out.printf("|%-30s|\n", "2 -- Editar usuário");
		System.out.printf("|%-30s|\n", "3 -- Listar usuários");
		System.out.printf("|%-30s|\n", "0 -- Voltar ");
		System.out.printf("|%-30s|\n", " ");
		System.out.println("|==============================|");
	}

	private static void escolhaMenuUsuarios(int escolha) {
		switch (escolha) {
		case 1:
			adicionaUsuario();
			break;
		case 2:
			editaUsuario();
			break;
		case 3:
			listarUsuarios();
			break;
		case 4:
			realizaCompra();
			break;
		case 5:
			realizaAbastecimento();
			break;
		case 0:

			break;

		default:
			System.out.println("Opção Inválida!");

		}
	}

	private static void adicionaUsuario() {
		boolean cadastro;
		do {
			sc.nextLine();
			System.out.print("\nNome do Usuário: ");
			String nome = sc.nextLine();
			String type = selecaoTipoDeUsuario();
			cadastro = user.cadastraUsuario(nome, type);
			String verify = (cadastro) ? "Usuário Criado com sucesso" : "Falha ao Cadastrar Usuário";
			System.out.println("\n" + verify + "\n");
		} while (!cadastro);
	}

	private static String selecaoTipoDeUsuario() {
		int escolhaTipoUsuario;
		System.out.println("\n|==============================|");
		System.out.println("|Selecione o tipo do Usuário   |");
		System.out.println("|==============================|");
		System.out.printf("|%-30s|\n", "1 -- Fornecedor");
		System.out.printf("|%-30s|\n", "2 -- Cliente");
		System.out.println("|==============================|");
		do {
			escolhaTipoUsuario = validaEscolha();
		} while (escolhaTipoUsuario != 1 && escolhaTipoUsuario != 2);
		String tipo = (escolhaTipoUsuario == 1) ? "Fornecedor" : "Cliente";
		return tipo;

	}

	private static void editaUsuario() {
		int id;
		do {
			System.out.print("\nDigite o Id do usuário que deseja editar: ");
			id = validaEscolha();
			if (!user.verificaExistenciaDeUsuario(id)) {
				System.out.println("\nUsuário não encontrado!\n");
				break;
			}
		} while (!user.verificaExistenciaDeUsuario(id));
		boolean edit;
		sc.nextLine();
		System.out.print("\nDigite o novo nome do usuário: ");
		String nome = sc.nextLine();
		edit = user.editarUsuario(id, nome);
		String verify = (edit) ? "Usuário editado com sucesso" : "Falha ao editar Usuário";
		System.out.println("\n" + verify + "\n");
	}

	private static void listarUsuarios() {
		List<Usuarios> userList = user.getUsuariosCadastrados();
		if (userList.isEmpty()) {
			System.out.println("Não existem usuários cadastrados!");
		} else {
			for (Usuarios a : userList) {
				a.toString();
				System.out.println("\n" + a);
				if(a.getUserType().equals("Cliente")) {
					System.out.println("\nCompras realizadas: " + a.getUserList().size() + "\n");
					if(a.getUserList().isEmpty()) {
						System.out.println("Nao existem compras registradas.");
					}else {
						for(String compras : a.getUserList()) {
							System.out.println(compras);
						}
					}
				}else {
					System.out.println("\nReabastecimentos realizados: " + a.getUserList().size()+ "\n");
					if(a.getUserList().isEmpty()) {
						System.out.println("Nao existem reabastecimentos registrados.");
					}else {
						for(String reabastecimentos : a.getUserList()) {
							System.out.println(reabastecimentos);
						}
					}
				}
				
				
				
				if(a.getUserList().isEmpty()) {
					
				}
			}
			System.out.println();
		}
	}

	private static void realizaCompra() {
		int id;
		do {
			System.out.print("\nDigite o Id do usuário responsável: ");
			id = validaEscolha();
			if (!user.verificaExistenciaDeUsuario(id)) {
				System.out.println("\nUsuário não encontrado!\n");
				break;
			}
		} while (!user.verificaExistenciaDeUsuario(id));

	}

	private static void realizaAbastecimento() {

	}

}
