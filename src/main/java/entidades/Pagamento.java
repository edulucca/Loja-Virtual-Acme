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

}
