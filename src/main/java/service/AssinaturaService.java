package service;

import entidades.Assinatura;

import java.math.BigDecimal;
import java.util.List;

public class AssinaturaService {

    private AssinaturaService(){

    }

    public static List<Long> tempoAssinaturasAtivas(List<Assinatura> assinaturas){
        return assinaturas.stream()
                .filter(Assinatura::isAssinaturaAtiva)
                .map(Assinatura::periodoAssinatura)
                .toList();
    }

    public static List<Long> periodoAssinaturas(List<Assinatura> assinaturas){
        return assinaturas.stream()
                .map(Assinatura::periodoAssinatura)
                .toList();
    }

    public static List<BigDecimal> valorTotalAssinaturas(List<Assinatura> assinaturas){
        return assinaturas.stream()
                .map(Assinatura::valorTotalAssinaturaSemTaxa)
                .toList();
    }
}
