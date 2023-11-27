package Usuarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Usuarios implements IUsuario {

	private int userId;
	private String userName;
	private String userType;
	
	private List<String> list;

	protected Usuarios(int userId1, String userName1, List<String> list1 , String userType1 ) {
		this.userId = userId1;
		this.userName = userName1;
		if(list1 == null) {
			this.list = new ArrayList<>();
		}else {
			this.list = list1;
		}
		this.userType = userType1;
	}

	
	public boolean realizaCompra(String prodNome, Double prodValor , int quantidade) {
		this.list.add(null);
		return false;
	}
	
	public boolean registro(String prodNome, int prodId, int quantidade, Double totalCompra) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String data = LocalDate.now().format(formatter);
		if(this.userType == "Cliente") {
			this.list.add("Comprou " + quantidade + " unidades do produto id:" + prodId + ", Nome: " + prodNome + ", no dia " + data + " Total da compra: R$" + totalCompra );
		}else {
			this.list.add("Adicionou " + quantidade + " unidades do produto id:" + prodId + ", Nome: " + prodNome + ", no dia " + data);
		}
		
		if(!this.list.isEmpty()) {
			for(String a: list) {
				if(a.contains(quantidade + " unidades do produto id: " + prodId + ", Nome: " + prodNome + ", no dia " + data));
				return true;
			}
		}
		return false;
	}
	



	//Start Getters
	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}
	
	public String getUserType() {
		return this.userType;
	}
	
	public List<String> getUserList() {
		return this.list;
	}
	//End Getters

	public void setUserName(String nome) {
		this.userName = nome;
	}

	
	@Override
	public String toString() {
		return "Id: " + userId + "\nNome: " + userName + " \nTipo: " + userType;
	}
	
	
	
	
}
