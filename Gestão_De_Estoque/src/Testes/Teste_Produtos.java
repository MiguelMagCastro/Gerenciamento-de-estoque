package Testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Produtos.Produtos;

class Teste_Produtos {
	Produtos produto;
	
	@BeforeEach
	public void declaraProduto() {
		produto = new Produtos(1, "Martelo", "Obejto com cabo de madeira e lamina de aço", 10.00, 10 );
	}

	@Test
	public void testaAdiçãoDeProduto() {
		produto.adicaoDeProdutos(10);
		assertEquals(20,produto.getQuantidade());
	}
	
	@Test
	public void saidaDeProdutoValida() {
		assertTrue(produto.saidaDeProdutos(5));
	}
	
	@Test
	public void saidaDeProdutosInvalida() {
		assertFalse(produto.saidaDeProdutos(100));
	}
	
}
