package teste;

import entidades.*;
import entidades.enums.VencimentoEnum;
import service.AssinaturaService;
import service.PagamentoService;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.logging.Logger;

public class ClasseTeste {

    static Logger log = Logger.getLogger(ClasseTeste.class.getName());

    public static void main(String[] args) throws MalformedURLException {

        // 1
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

        //2
        log.info("------------------------QUESTÃO 2---------------------------");
        List<Pagamento> pagamentosOrdenados = PagamentoService.ordenarPagamentos(pagamentos);
        String pagamentosOrdenadosString = String.valueOf(pagamentosOrdenados);
        log.info(pagamentosOrdenadosString);

        log.info("------------------------QUESTÃO 3---------------------------");
        //3
        double valorTotal = pagamento1.valorTotal().doubleValue();
        String valorTotalString = String.valueOf(valorTotal);
        log.info(valorTotalString);

        log.info("------------------------QUESTÃO 5---------------------------");
        // 5
        Map<String, Long> quantidadeProdutos = PagamentoService.quantidadeProdutos(pagamentos);
        String quantidadeProdutosString = String.valueOf(quantidadeProdutos);
        log.info(quantidadeProdutosString);

        log.info("------------------------QUESTÃO 6---------------------------");
        // 6
        Map<String, List<Produto>> pagamentosPorCliente = PagamentoService.agruparPorCliente(pagamentos);
        String pagamentosPorClienteString = String.valueOf(pagamentosPorCliente);
        log.info(pagamentosPorClienteString);

        log.info("------------------------QUESTÃO 7---------------------------");
        // 7
        Map.Entry<String, Optional<BigDecimal>> maiorPagamento = PagamentoService.maiorPagamento(pagamentos);
        String maiorPagamentoString = maiorPagamento.getKey();
        log.info(maiorPagamentoString);

        log.info("------------------------QUESTÃO 8---------------------------");
        // 8
        BigDecimal faturamentoJunho = PagamentoService.faturamentoAnyMes(Month.JUNE, pagamentos);
        String faturamentoMaioString = String.valueOf(faturamentoJunho);
        log.info(faturamentoMaioString);

        //9
        Assinatura assinatura1 = new Assinatura(new BigDecimal("99.98"), LocalDate.now().minusMonths(5), cliente1);
        Assinatura assinatura2 = new Assinatura(new BigDecimal("95.97"), LocalDate.now().minusMonths(10), LocalDate.now(), cliente2);
        Assinatura assinatura3 = new Assinatura(new BigDecimal("94.72"), LocalDate.now().minusMonths(2), LocalDate.now(), cliente3);

        log.info("------------------------QUESTÃO 10--------------------------");
        // 10
        List<Assinatura> assinaturas = Arrays.asList(assinatura1, assinatura2, assinatura3);
        String tempoAssinaturasAtivasString = String.valueOf(AssinaturaService.tempoAssinaturasAtivas(assinaturas));
        log.info(tempoAssinaturasAtivasString);

        log.info("------------------------QUESTÃO 11--------------------------");
        // 11
        String periodoAssinaturasString = String.valueOf(AssinaturaService.periodoAssinaturas(assinaturas));
        log.info(periodoAssinaturasString);

        log.info("------------------------QUESTÃO 12--------------------------");
        // 12
        String valorTotalAssinaturasString = String.valueOf(AssinaturaService.valorTotalAssinaturas(assinaturas));
        log.info(valorTotalAssinaturasString);

        log.info("------------------------QUESTÃO 13--------------------------");
        // 13
        String valorTotalAssinaturaAnualString = String.valueOf(assinatura1.valorTotalAssinaturaComTaxa(new AssinaturaAnual()));
        log.info(valorTotalAssinaturaAnualString);

        String valorTotalAssinaturaSemestralString = String.valueOf(assinatura2.valorTotalAssinaturaComTaxa(new AssinaturaSemestral()));
        log.info(valorTotalAssinaturaSemestralString);

        String valorTotalAssinaturaTrimestralString = String.valueOf(assinatura3.valorTotalAssinaturaComTaxa(new AssinaturaTrimestral()));
        log.info(valorTotalAssinaturaTrimestralString);

        log.info("------------------------QUESTÃO 14--------------------------");
        // 14
        assinatura1.setVencimentoEnum(VencimentoEnum.DIA_DEZ);
        boolean isAssinaturaVencida = assinatura1.isAssinaturaVencida(pagamento1.getDataCompra());
        String isAssinaturaVencidaString = String.valueOf(isAssinaturaVencida);
        log.info(isAssinaturaVencidaString);

    }
}

