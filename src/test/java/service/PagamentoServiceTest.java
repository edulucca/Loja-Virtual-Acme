package service;

import entidades.Cliente;
import entidades.Pagamento;
import entidades.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static service.PagamentoService.*;

class PagamentoServiceTest {

        Cliente cliente1 = new Cliente("João");
        Cliente cliente2 = new Cliente("Maria");
        Cliente cliente3 = new Cliente("Jorge");

        Produto produto1 = new Produto("Musicas - Coldplay", new URL("https://www.acme.com/media/coldplay.mp3"), new BigDecimal("40.0"));
    Produto produto2 = new Produto("Vídeos - Lady Gaga", new URL("https://www.acme.com/media/lady-gaga.mp4"), new BigDecimal("75.0"));
    Produto produto3 = new Produto("Imagens - Imagine Dragons", new URL("https://www.acme.com/media/imagine-dragons.png"), new BigDecimal("20.0"));

    Pagamento pagamento1 = new Pagamento(Arrays.asList(produto1, produto2), LocalDate.now(), cliente1);
    Pagamento pagamento2 = new Pagamento(Arrays.asList(produto2, produto3), LocalDate.now().minusDays(1), cliente2);
    Pagamento pagamento3 = new Pagamento(Arrays.asList(produto1, produto3), LocalDate.now().minusMonths(1), cliente3);

    List<Pagamento> pagamentos = Arrays.asList(pagamento1, pagamento2, pagamento3);

    PagamentoServiceTest() throws MalformedURLException {
    }

    @Test
    void ordenarPagamentos() {
        // Arrange

        // Act
        List<Pagamento> resultado = PagamentoService.ordenarPagamentos(pagamentos);

        // Assert
        Assertions.assertEquals(LocalDate.of(2023, 06, 2), resultado.get(0).getDataCompra());
        Assertions.assertEquals(LocalDate.of(2023, 7, 1), resultado.get(1).getDataCompra());
        Assertions.assertEquals(LocalDate.of(2023, 7, 2), resultado.get(2).getDataCompra());
    }

    @Test
    void valorTotalPagamentos() {
        // Act
        BigDecimal resultado = PagamentoService.valorTotalPagamentos(pagamentos);

        // Assert
        Assertions.assertEquals(BigDecimal.valueOf(270.0), resultado);
    }

    @Test
    void quantidadeProdutos() {
        // Act
        Map<String, Long> resultado = PagamentoService.quantidadeProdutos(pagamentos);

        // Assert
        Assertions.assertEquals(3, resultado.size());
    }

    @Test
    void agruparPorCliente() {
        // Arrange

        // Act
        Map<String, List<Produto>> resultado = PagamentoService.agruparPorCliente(pagamentos);

        // Assert
        Assertions.assertEquals(PagamentoService.agruparPorCliente(pagamentos), resultado);
    }

    @Test
    void maiorPagamento() {

        // Act
        Map.Entry<String, Optional<BigDecimal>> resultado = PagamentoService.maiorPagamento(pagamentos);

        // Assert
        Assertions.assertEquals(pagamento1.getCliente().getNome(), resultado.getKey());
        Assertions.assertEquals(BigDecimal.valueOf(115.0), resultado.getValue().get());
    }

    @Test
    void faturamentoAnyMes() {
        // Act
        BigDecimal resultado = PagamentoService.faturamentoAnyMes(Month.JUNE, pagamentos);

        // Assert
        Assertions.assertEquals(BigDecimal.valueOf(60.0), resultado);
    }
}