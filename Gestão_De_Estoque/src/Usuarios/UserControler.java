package Usuarios;

import java.util.ArrayList;
import java.util.List;

public class UserControler {

	List<Usuarios> usuariosCadastrados = new ArrayList<>();

	public List<Usuarios> getUsuariosCadastrados() {
		return usuariosCadastrados;
	}

	public boolean cadastraUsuario(String nome, String tipo) {
		int id = geraId();
		if (tipo.equalsIgnoreCase("Cliente")) {
			usuariosCadastrados.add(new Cliente(id, nome));
		} else {
			usuariosCadastrados.add(new Fornecedor(id, nome));
		}
		if (!usuariosCadastrados.isEmpty()) {
			for (Usuarios a : usuariosCadastrados) {
				if (id == a.getUserId()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean verificaExistenciaDeUsuario(int id) {
		for(Usuarios a : usuariosCadastrados) {
			if(a.getUserId() == id) {
				return true;
			}
		}
		return false;
	}
	
	public boolean editarUsuario(int id, String nome) {
		for(Usuarios a : usuariosCadastrados) {
			if(a.getUserId() == id) {
				a.setUserName(nome);
			}
		}
		for(Usuarios b : usuariosCadastrados) {
			if(b.getUserId() == id) {
				if(b.getUserName().equalsIgnoreCase(nome)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public String verificaTipoDeUsuario(int id) {
		for(Usuarios a : usuariosCadastrados) {
			if(a.getUserId() == id) {
				return a.getUserType();
			}
		}
		return null;
	}
	
	public String getNameById(int id) {
		for(Usuarios a : usuariosCadastrados) {
			if(a.getUserId() == id) {
				return a.getUserName();
			}
		}
		return null;
	}
	
	private int geraId() {
		int maiorID = 0;
		if (usuariosCadastrados.isEmpty()) {
			return 1;
		} else {
			for (Usuarios a : usuariosCadastrados) {
				if (a.getUserId() > maiorID) {
					maiorID = a.getUserId();
				}
			}
			return (maiorID + 1);
		}
	}

	public void userRegistros(String prodName, int prodId, int quantidade, int userId, Double valorCompra) {
		for(Usuarios a : usuariosCadastrados) {
			if(a.getUserId() == userId) {
				a.registro(prodName, prodId, quantidade, valorCompra);
			}
		}
	}
}
