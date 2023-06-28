package entidades;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pagamento {

    private List<Produto> produtos;
    private LocalDate dataCompra;
    private Cliente cliente;

    public BigDecimal valorTotal(){
        return produtos.stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static List<Pagamento> ordenarPagamentos(List<Pagamento> pagamentos){
        return pagamentos.stream()
                .sorted(Comparator.comparing(Pagamento::getDataCompra)).toList();
    }

    public static BigDecimal valorTotalPagamentos(List<Pagamento> pagamentos){
        return pagamentos.stream()
                .map(Pagamento::valorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Map<String, Long> quantidadeProdutos(List<Pagamento> pagamentos){
        return pagamentos.stream()
                .flatMap(pagamento -> pagamento.getProdutos().stream())
                .collect(Collectors.groupingBy(Produto::getNome, Collectors.counting()));
    }

    public static Map<String, List<Produto>> agruparPorCliente(List<Pagamento> pagamentos){
        return pagamentos.stream()
                .collect(Collectors.groupingBy(pagamento -> pagamento.getCliente().getNome(),
                        TreeMap::new,
                        Collectors.flatMapping(pagamento -> pagamento.getProdutos().stream(), Collectors.toList())));
    }

    public static Map.Entry<String, Optional<BigDecimal>> maiorPagamento(List<Pagamento> pagamentos){
        return pagamentos.stream()
                .collect(Collectors.groupingBy(pagamento -> pagamento.getCliente().getNome(),
                        Collectors.mapping(pagamento -> pagamento.getProdutos().stream()
                                        .map(Produto::getPreco)
                                        .reduce(BigDecimal.ZERO, BigDecimal::add),
                                Collectors.reducing(BigDecimal::add))))
                .entrySet().stream()
                .max(Comparator.comparing(entry -> entry.getValue().orElse(BigDecimal.ZERO)))
                .orElseThrow();
    }

    public static BigDecimal faturamentoAnyMes(Month mes, List<Pagamento> pagamentos){
        return pagamentos.stream()
                .filter(pagamento -> pagamento.getDataCompra().getMonth().equals(mes))
                .flatMap(pagamento -> pagamento.getProdutos().stream())
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
