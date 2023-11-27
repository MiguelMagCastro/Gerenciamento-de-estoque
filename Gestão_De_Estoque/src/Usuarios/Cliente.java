package Usuarios;

import java.util.ArrayList;
import java.util.List;


public class Cliente extends Usuarios {

	private static List<String> compras = new ArrayList<>();
	
	
	public Cliente(int userId1, String userName1) {
		super(userId1, userName1, compras , "Cliente");
	}
	
	

}
