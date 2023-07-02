package entidades;

import entidades.enums.VencimentoEnum;
import entidades.interfaces.TaxaAssinatura;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;


@ToString
public class Assinatura {
    private BigDecimal mensalidade;
    private LocalDate begin;
    private Optional<LocalDate> end;
    private Cliente cliente;
    private VencimentoEnum vencimentoEnum;

    public Assinatura(BigDecimal mensalidade, LocalDate begin, LocalDate end, Cliente cliente) {
        this.mensalidade = mensalidade;
        this.begin = begin;
        this.end = Optional.of(end);
        this.cliente = cliente;
    }

    public Assinatura(BigDecimal mensalidade, LocalDate begin, Cliente cliente) {
        this.mensalidade = mensalidade;
        this.begin = begin;
        this.cliente = cliente;
        this.end = Optional.empty();
    }

    public BigDecimal getMensalidade() {
        return mensalidade;
    }

    public LocalDate getBegin() {
        return begin;
    }

    public Optional<LocalDate> getEnd() {
        return end;
    }

    public void setVencimentoEnum(VencimentoEnum vencimentoEnum) {
        this.vencimentoEnum = vencimentoEnum;
    }

    public int getVencimentoEnum() {
        return vencimentoEnum.getDia();
    }

    public long periodoAssinatura(){
        return ChronoUnit.MONTHS.between(this.getBegin(), this.getEnd().orElse(LocalDate.now()));
    }

    public BigDecimal valorTotalAssinaturaSemTaxa() {
        return getMensalidade().multiply(BigDecimal.valueOf(periodoAssinatura()));
    }

    public BigDecimal valorTotalAssinaturaComTaxa(TaxaAssinatura tipoAssinatura) {
        return tipoAssinatura.aplicarTaxa(getMensalidade().multiply(BigDecimal.valueOf(periodoAssinatura())));
    }

    public boolean isAssinaturaAtiva(){
        return getEnd().isEmpty();
    }

    public boolean isAssinaturaVencida(LocalDate dataPagamento){
        return LocalDate.now().withDayOfMonth(getVencimentoEnum()).isBefore(dataPagamento);
    }
}
