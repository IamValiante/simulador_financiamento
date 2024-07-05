package modelo;

import java.io.Serializable;

public class Terreno extends Financiamento implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final double ACRESCIMO_INADIMPLENCIA = 0.02;
    private String tipoZona;

    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, String tipoZona) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tipoZona = tipoZona;
    }

    public String getTipoZona() {
        return tipoZona;
    }

    @Override
    public double calcularPagamentoMensal() {
        int meses = getPrazoFinanciamento() * 12;
        double taxaMensal = getTaxaJurosAnual() / 12 / 100;
        double pagamentoMensal = (getValorImovel() / meses) * (1 + taxaMensal);
        return pagamentoMensal * (1 + ACRESCIMO_INADIMPLENCIA);
    }

    @Override
    public String toString() {
        return "Terreno," + getValorImovel() + "," + getPrazoFinanciamento() + "," + getTaxaJurosAnual() + "," + tipoZona;
    }
}
