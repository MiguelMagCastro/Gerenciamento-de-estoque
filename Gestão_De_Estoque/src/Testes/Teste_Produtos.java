package Testes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Produtos.Produtos;

class Teste_Produtos {

    private Produtos produto;

    @BeforeEach
    void setUp() {
        produto = new Produtos(1, "Produto1", "Descrição1", 10.0, 50);
    }

    @Test
    void testGetId() {
        assertEquals(1, produto.getId());
    }

    @Test
    void testGetNome() {
        assertEquals("Produto1", produto.getNome());
    }

    @Test
    void testGetDescricao() {
        assertEquals("Descrição1", produto.getDescricao());
    }

    @Test
    void testGetValorUnitario() {
        assertEquals(10.0, produto.getValorUnitario(), 0.001);
    }

    @Test
    void testGetQuantidade() {
        assertEquals(50, produto.getQuantidade());
    }

    @Test
    void testAdicaoDeProdutos() {
        produto.adicaoDeProdutos(20);
        assertEquals(70, produto.getQuantidade());
    }

    @Test
    void testSaidaDeProdutos() {
        assertTrue(produto.saidaDeProdutos(30));
        assertEquals(20, produto.getQuantidade());
    }

    @Test
    void testSaidaDeProdutosInsuficiente() {
        assertFalse(produto.saidaDeProdutos(60));
        assertEquals(50, produto.getQuantidade());
    }

    @Test
    void testToString() {
        String expected = "id: 1\nnome: Produto1\ndescricao: Descrição1\nvalorUnitario: 10.0\nquantidade: 50";
        assertEquals(expected, produto.toString());
    }
}
