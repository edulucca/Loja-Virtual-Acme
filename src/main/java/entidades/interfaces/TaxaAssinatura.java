package entidades.interfaces;

import java.math.BigDecimal;

public interface TaxaAssinatura {
    BigDecimal aplicarTaxa(BigDecimal valorTotal);
}
