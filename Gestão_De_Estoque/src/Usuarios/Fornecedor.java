package Usuarios;

import java.util.ArrayList;
import java.util.List;


public class Fornecedor extends Usuarios {

	private static List<String> reabastecimentos = new ArrayList<>();
	
	public Fornecedor(int userId1, String userName1) {
		super(userId1, userName1, reabastecimentos, "Fornecedor");
		
	}

}
