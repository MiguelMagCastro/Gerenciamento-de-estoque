package Usuarios;

import java.time.LocalDate;

import Produtos.Produtos;

public interface IUsuario {

	boolean realizaCompra(String prodNome, Double prodValor , int quantidade);
	
	void editaUsuario(String nome, LocalDate nascimento);
	
}
