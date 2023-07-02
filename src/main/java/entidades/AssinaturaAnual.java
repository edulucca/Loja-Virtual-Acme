package entidades;

import entidades.interfaces.TaxaAssinatura;
import entidades.enums.TaxaEnum;

import java.math.BigDecimal;

public class AssinaturaAnual implements TaxaAssinatura {
    @Override
    public BigDecimal aplicarTaxa(BigDecimal valorTotal) {
        return valorTotal.add(valorTotal.multiply(TaxaEnum.ANUAL.getTaxa()).divide(BigDecimal.valueOf(100)));
    }
}
