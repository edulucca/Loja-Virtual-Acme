import entidades.Assinatura;
import entidades.Cliente;
import entidades.Pagamento;
import entidades.Produto;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class ClasseTeste {
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
        System.out.println("------------------------QUESTÃO 2---------------------------");
        Pagamento.ordenarPagamentos(pagamentos).forEach(System.out::println);

        System.out.println("------------------------QUESTÃO 3---------------------------");
        //3
        System.out.println(pagamento1.valorTotal().doubleValue());

        System.out.println("------------------------QUESTÃO 4---------------------------");
        //4
        System.out.println(Pagamento.valorTotalPagamentos(pagamentos));

        System.out.println("------------------------QUESTÃO 5---------------------------");
        //5
        Pagamento.quantidadeProdutos(pagamentos).forEach((k, v) -> System.out.println("Produto: " + k + " - Quantidade: " + v));

        System.out.println("------------------------QUESTÃO 6---------------------------");
        //6
        Pagamento.agruparPorCliente(pagamentos).forEach((chave, valor) -> System.out.println("Cliente: " + chave + " = " + valor));

        System.out.println("------------------------QUESTÃO 7---------------------------");
        //7
        System.out.println(Pagamento.maiorPagamento(pagamentos).getKey());

        System.out.println("------------------------QUESTÃO 8---------------------------");
        //8
        System.out.println(Pagamento.faturamentoAnyMes(Month.MAY, pagamentos));

        //9
        Assinatura assinatura1 = new Assinatura(new BigDecimal("99.98"), LocalDate.now().minusMonths(5), cliente1);
        Assinatura assinatura2 = new Assinatura(new BigDecimal("99.98"), LocalDate.now().minusMonths(10), LocalDate.now(), cliente2);
        Assinatura assinatura3 = new Assinatura(new BigDecimal("99.98"), LocalDate.now().minusMonths(2), LocalDate.now(), cliente3);

        System.out.println("------------------------QUESTÃO 10--------------------------");
        //10
        List<Assinatura> assinaturas = Arrays.asList(assinatura1, assinatura2, assinatura3);

        assinaturas.stream()
                .filter(Assinatura::verificaAssinatura)
                .map(assinatura -> "Assinatura ativa: " + assinatura.getCliente().getNome()+ " - (" + assinatura.periodoAssinatura() + " meses)")
                .forEach(System.out::println);

        System.out.println("------------------------QUESTÃO 11--------------------------");
        //11
        assinaturas.stream()
                .map(assinatura -> "Periodo assinatura - "+ assinatura.getCliente().getNome() +": " + assinatura.getBegin()
                        + " - " + assinatura.getEnd().orElse(LocalDate.now()) + " (" + assinatura.periodoAssinatura() + " meses)")
                .forEach(System.out::println);

        System.out.println("------------------------QUESTÃO 12--------------------------");
        //12
        assinaturas.stream()
                .map(assinatura -> "Valor total da mensalidade da assinatura do cliente " + assinatura.getCliente().getNome() + ": " + assinatura.valorTotalMensalidade(assinatura.periodoAssinatura()))
                .forEach(System.out::println);

    }
}
