package entidades;

import entidades.interfaces.TaxaAssinatura;
import entidades.enums.TaxaEnum;

import java.math.BigDecimal;

public class AssinaturaSemestral implements TaxaAssinatura {
    @Override
    public BigDecimal aplicarTaxa(BigDecimal valorTotal) {
        return valorTotal.add(valorTotal.multiply(TaxaEnum.SEMESTRAL.getTaxa()).divide(BigDecimal.valueOf(100)));
    }
}
