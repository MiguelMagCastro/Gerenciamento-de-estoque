package Testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Usuarios.Cliente;
import Usuarios.Fornecedor;
import Usuarios.Usuarios;

class Teste_Usuarios  {

    private Usuarios cliente;
    private Usuarios fornecedor;

    @BeforeEach
    void setUp() {
        List<String> listaCompras = new ArrayList<>();
        List<String> listaAdicoes = new ArrayList<>();
        cliente = new Cliente(1, "Cliente1");
        fornecedor = new Fornecedor(2, "Fornecedor1");
    }

    @Test
    void testeRealizaCompra() {
        assertFalse(cliente.realizaCompra("Produto1", 10.0, 5));
        assertEquals(1, cliente.getUserList().size());
    }

    @Test
    void testeRegistroCliente() {
        assertTrue(cliente.registro("Produto1", 1, 5, 50.0));
        assertTrue(cliente.getUserList().get(0).contains("Comprou 5 unidades do produto id:1, Nome: Produto1"));
    }

    @Test
    void testeRegistroFornecedor() {
        assertTrue(fornecedor.registro("Produto1", 1, 5, null));
        assertTrue(fornecedor.getUserList().get(0).contains("Adicionou 5 unidades do produto id:1, Nome: Produto1"));
    }

    @Test
    void testeGetUserId() {
        assertEquals(1, cliente.getUserId());
    }

    @Test
    void testeGetUserName() {
        assertEquals("Cliente1", cliente.getUserName());
    }

    @Test
    void testeGetUserType() {
        assertEquals("Cliente", cliente.getUserType());
        assertEquals("Fornecedor", fornecedor.getUserType());
    }

    @Test
    void testeGetUserList() {
        assertEquals(1, cliente.getUserList().size());
    }

    @Test
    void testeSetUserName() {
        cliente.setUserName("NovoNome");
        assertEquals("NovoNome", cliente.getUserName());
    }

    @Test
    void testeToString() {
        assertEquals("Id: 1\nNome: Cliente1 \nTipo: Cliente", cliente.toString());
    }
}



