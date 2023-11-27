package Testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Estoque.Estoque;

class Teste_Estoque  {

    private Estoque estoque;

    @BeforeEach
    void setUp() {
        estoque = new Estoque();
    }

    @Test
    void testeCadastroDeProduto() {
        assertTrue(estoque.cadastroDeProduto("Produto1", "Descrição1", 10.0, 50));
        assertTrue(estoque.verificaExistencia(1));
        assertEquals("Produto1", estoque.getNameById(1));
    }

    @Test
    void testeAdicaoDeProdutos() {
        estoque.cadastroDeProduto("Produto1", "Descrição1", 10.0, 50);
        assertTrue(estoque.adicaoDeProdutos(1, 20, 1, "Fornecedor1"));
        assertEquals(70, estoque.getProdutosEmEstoque().get(0).getQuantidade());
    }

    @Test
    void testeSaidaDeProdutos() {
        estoque.cadastroDeProduto("Produto1", "Descrição1", 10.0, 50);
        assertTrue(estoque.saidaDeProdutos(1, 30, 1, "Cliente1"));
        assertEquals(20, estoque.getProdutosEmEstoque().get(0).getQuantidade());
    }

    @Test
    void testeConsultaProduto() {
        estoque.cadastroDeProduto("Produto1", "Descrição1", 10.0, 50);
        assertEquals("id: 1\nnome: Produto1\ndescricao: Descrição1\nvalorUnitario: 10.0\nquantidade: 50",
                estoque.consultaProduto(1));
    }

    @Test
    void testeCalculaValorDaVenda() {
        estoque.cadastroDeProduto("Produto1", "Descrição1", 10.0, 50);
        assertEquals(300.0, estoque.calculaValorDaVenda(30, 1), 0.001);
    }

    @Test
    void testeVerificaQuantidadeRemocao() {
        estoque.cadastroDeProduto("Produto1", "Descrição1", 10.0, 50);
        assertTrue(estoque.verificaQuantidadeRemocao(30, 1));
        assertFalse(estoque.verificaQuantidadeRemocao(60, 1));
    }

    @Test
    void testeGetRegistroDeEntrada() {
        estoque.cadastroDeProduto("Produto1", "Descrição1", 10.0, 50);
        estoque.adicaoDeProdutos(1, 20, 1, "Fornecedor1");
        assertEquals(1, estoque.getRegistroDeEntrada().size());
    }

    @Test
    void testeGetRegistroDeSaida() {
        estoque.cadastroDeProduto("Produto1", "Descrição1", 10.0, 50);
        estoque.saidaDeProdutos(1, 30, 1, "Cliente1");
        assertEquals(1, estoque.getRegistroDeSaida().size());
    }
}
