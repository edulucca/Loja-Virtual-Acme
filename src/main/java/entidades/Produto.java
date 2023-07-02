package entidades;

import lombok.*;

import java.math.BigDecimal;
import java.net.URL;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Produto {

    private String nome;
    private URL file;
    private BigDecimal preco;
}
