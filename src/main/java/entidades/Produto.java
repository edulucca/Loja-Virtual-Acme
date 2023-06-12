package entidades;

import lombok.*;

import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Path;

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
