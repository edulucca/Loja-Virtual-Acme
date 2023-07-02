package entidades.enums;

public enum VencimentoEnum {
    DIA_UM(1),
    DIA_CINCO(5),
    DIA_DEZ(10),
    DIA_DEZOITO(18);

    private int dia;

    VencimentoEnum(int dia){
        this.dia = dia;
    }

    public int getDia(){return dia;}
}
