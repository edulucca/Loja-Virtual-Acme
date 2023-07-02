package service;

import entidades.Assinatura;
import entidades.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AssinaturaServiceTest {
    Cliente cliente1 = new Cliente("João");
    Cliente cliente2 = new Cliente("Maria");
    Cliente cliente3 = new Cliente("Jorge");

    Assinatura assinatura1 = new Assinatura(new BigDecimal("99.98"), LocalDate.now().minusMonths(5), cliente1);
    Assinatura assinatura2 = new Assinatura(new BigDecimal("95.97"), LocalDate.now().minusMonths(10), LocalDate.now(), cliente2);
    Assinatura assinatura3 = new Assinatura(new BigDecimal("94.72"), LocalDate.now().minusMonths(2), LocalDate.now(), cliente3);

    List<Assinatura> assinaturas = Arrays.asList(assinatura1, assinatura2, assinatura3);
    @Test
    void tempoAssinaturasAtivas() {
        List<Long> tempoAssinaturasAtivas = AssinaturaService.tempoAssinaturasAtivas(assinaturas);
        List<Long> esperado = new ArrayList<>();
        esperado.add(5L);

        Assertions.assertEquals(esperado, tempoAssinaturasAtivas);
    }

    @Test
    void periodoAssinaturas() {
        List<Long> periodo = AssinaturaService.periodoAssinaturas(assinaturas);
        List<Long> esperado = new ArrayList<>();
        esperado.add(5L);
        esperado.add(10L);
        esperado.add(2L);

        Assertions.assertEquals(esperado, periodo);
    }

    @Test
    void valorTotalAssinaturas() {
        List<BigDecimal> valorTotalAssinatura = AssinaturaService.valorTotalAssinaturas(assinaturas);
        List<Long> esperado = new ArrayList<>();


        Assertions.assertEquals("[499.90, 959.70, 189.44]", String.valueOf(valorTotalAssinatura));
    }
}