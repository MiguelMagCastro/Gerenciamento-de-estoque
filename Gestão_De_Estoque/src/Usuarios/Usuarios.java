package Usuarios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuarios implements IUsuario {

	private int userId;
	private String userName;
	private LocalDate nascimento;
	private List<String> compras;

	public Usuarios(int userId1, String userName1, LocalDate nascimento1) {
		this.userId = userId1;
		this.userName = userName1;
		this.nascimento = nascimento1;
		this.compras = new ArrayList<>();
	}

	@Override
	public boolean realizaCompra(String prodNome, Double prodValor , int quantidade) {
		this.compras.add(quantidade + "X -- " + prodNome + "Valor Unitario: " + prodValor + "TOTAL: R$" + valorCompra(quantidade, prodValor) );
		return false;
	}

	@Override
	public void editaUsuario(String nome, LocalDate nascimento) {
		

	}
	
	private Double valorCompra(int quantidade , Double valorUnitario) {
		return (Double) valorUnitario * quantidade;
	}

	//Start Getters
	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}
	//End Getters
}
