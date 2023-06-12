import entidades.Assinatura;
import entidades.Cliente;
import entidades.Pagamento;
import entidades.Produto;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class ClasseTeste {
    public static void main(String[] args) throws MalformedURLException {
        // 1
        Cliente cliente1 = new Cliente("João");
        Cliente cliente2 = new Cliente("Maria");

        Produto produto1 = new Produto("Musicas - Coldplay", new URL("https://www.acme.com/media/coldplay.mp3"), new BigDecimal("40.0"));
        Produto produto2 = new Produto("Vídeos - Lady Gaga", new URL("https://www.acme.com/media/lady-gaga.mp4"), new BigDecimal("75.0"));
        Produto produto3 = new Produto("Imagens - Imagine Dragons", new URL("https://www.acme.com/media/imagine-dragons.png"), new BigDecimal("20.0"));

        Pagamento pagamento1 = new Pagamento(Arrays.asList(produto1, produto2), LocalDate.now(), cliente1);
        Pagamento pagamento2 = new Pagamento(Arrays.asList(produto2, produto3), LocalDate.now().minusDays(1), cliente2);
        Pagamento pagamento3 = new Pagamento(Arrays.asList(produto1, produto3), LocalDate.now().minusMonths(1), cliente1);

        List<Pagamento> pagamentos = Arrays.asList(pagamento1, pagamento2, pagamento3);

        //2
        System.out.println("------------------------QUESTÃO 2---------------------------");
        pagamentos.stream()
                .sorted(Comparator.comparing(Pagamento::getDataCompra))
                .forEach(System.out::println);

        System.out.println("------------------------QUESTÃO 3---------------------------");
        //3
        Optional<Double> pagamentoFinal = pagamento1.getProdutos().stream()
                .map(produto -> produto.getPreco().doubleValue())
                .reduce(Double::sum);
        pagamentoFinal.ifPresent(System.out::println);

        System.out.println("------------------------QUESTÃO 4---------------------------");
        //4
        BigDecimal valorTotalPagamentos = pagamentos.stream()
                .flatMap(pagamento -> pagamento.getProdutos().stream())
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(valorTotalPagamentos);

        System.out.println("------------------------QUESTÃO 5---------------------------");
        //5
        Map<String, Long> qntdProduto = pagamentos.stream()
                .flatMap(pagamento -> pagamento.getProdutos().stream())
                .collect(Collectors.groupingBy(Produto::getNome, Collectors.counting()));
        qntdProduto.forEach((chave, valor) -> System.out.println(valor));

        System.out.println("------------------------QUESTÃO 6---------------------------");
        //6
        Map<String, List<Produto>> produtosPorCliente = pagamentos.stream()
                .collect(Collectors.groupingBy(pagamento -> pagamento.getCliente().getNome(),
                        Collectors.flatMapping(pagamento -> pagamento.getProdutos().stream(), Collectors.toList())));
        produtosPorCliente.forEach((chave, valor) -> System.out.println(valor));

        System.out.println("------------------------QUESTÃO 7---------------------------");
        //7
        Map.Entry<String, Optional<BigDecimal>> clienteComMaiorGasto = pagamentos.stream()
                .collect(Collectors.groupingBy(pagamento -> pagamento.getCliente().getNome(),
                        Collectors.mapping(pagamento -> pagamento.getProdutos().stream()
                                        .map(Produto::getPreco)
                                        .reduce(BigDecimal.ZERO, BigDecimal::add),
                                Collectors.reducing(BigDecimal::add))))
                .entrySet().stream()
                .max(Comparator.comparing(entry -> entry.getValue().orElse(BigDecimal.ZERO)))
                .orElseThrow();

        clienteComMaiorGasto.getValue().ifPresent(valor -> System.out.println(clienteComMaiorGasto.getKey()));

        System.out.println("------------------------QUESTÃO 8---------------------------");
        //8
        BigDecimal faturamentoMes = pagamentos.stream()
                .filter(pagamento -> pagamento.getDataCompra().getMonth() == Month.MAY)
                .flatMap(pagamento -> pagamento.getProdutos().stream())
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println(faturamentoMes);

        //9
        Assinatura assinatura1 = new Assinatura(new BigDecimal("99.98"), LocalDate.now().minusMonths(5), cliente1);
        Assinatura assinatura2 = new Assinatura(new BigDecimal("99.98"), LocalDate.now().minusMonths(10), LocalDate.now(), cliente2);
        Assinatura assinatura3 = new Assinatura(new BigDecimal("99.98"), LocalDate.now().minusMonths(2), LocalDate.now(), cliente1);

        System.out.println("------------------------QUESTÃO 10--------------------------");
        //10
        List<Assinatura> assinaturas = Arrays.asList(assinatura1, assinatura2, assinatura3);

        assinaturas.stream()
                .filter(assinatura -> assinatura.getEnd() == null)
                .forEach(assinatura -> {
                    int mesesAssinaturaAtiva = Period.between(assinatura.getBegin(), LocalDate.now()).getMonths();
                    System.out.println("Assinatura ativa: " + assinatura.getCliente().getNome()+ " - (" + mesesAssinaturaAtiva + " meses)");
                });

        System.out.println("------------------------QUESTÃO 11--------------------------");
        //11
        assinaturas.stream()
                .filter(assinatura -> assinatura.getEnd() != null)
                .forEach(assinatura -> {
                    int duracaoAssinatura = Period.between(assinatura.getBegin(), assinatura.getEnd()).getMonths();
                    System.out.println("Assinatura cancelada: " + assinatura.getBegin() + " - " + assinatura.getEnd() + " (" + duracaoAssinatura + " meses)");
                });

        System.out.println("------------------------QUESTÃO 12--------------------------");
        //12
        assinaturas.forEach(assinatura -> {
            LocalDate endDate = assinatura.getEnd() != null ? assinatura.getEnd() : LocalDate.now();
            int qntdMesesAssinados = Period.between(assinatura.getBegin(), endDate).getMonths() + 1;
            BigDecimal valorTotalMensalidade = assinatura.getMensalidade().multiply(BigDecimal.valueOf(qntdMesesAssinados));
            System.out.println("Valor total da mensalidade da assinatura do cliente " + assinatura.getCliente().getNome() + ": " + valorTotalMensalidade);
        });
    }
}
