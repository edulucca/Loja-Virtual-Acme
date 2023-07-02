package entidades;

import entidades.enums.TaxaEnum;
import entidades.interfaces.TaxaAssinatura;

import java.math.BigDecimal;

public class AssinaturaTrimestral implements TaxaAssinatura {
    @Override
    public BigDecimal aplicarTaxa(BigDecimal valorTotal) {
        return valorTotal.add(valorTotal.multiply(TaxaEnum.TRIMESTRAL.getTaxa()).divide(BigDecimal.valueOf(100)));
    }
}
