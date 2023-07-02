package entidades.enums;

import java.math.BigDecimal;

public enum TaxaEnum {
    ANUAL(BigDecimal.valueOf(0.00)),
    SEMESTRAL(BigDecimal.valueOf(3.00)),
    TRIMESTRAL(BigDecimal.valueOf(5.00));

    private BigDecimal taxa;
    TaxaEnum(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }
}
