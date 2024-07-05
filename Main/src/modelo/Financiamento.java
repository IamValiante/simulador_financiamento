package modelo;

import util.AcrescimoMaiorDoQueJurosException;

import java.io.Serializable;

public abstract class Financiamento implements Serializable {
    private static final long serialVersionUID = 1L;
    // Atributos
    private double valorImovel;
    private int prazoFinanciamento;
    private double taxaJurosAnual;

    // Construtor
    public Financiamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual) {
        this.valorImovel = valorImovel;
        this.prazoFinanciamento = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    // Métodos
    public double getValorImovel() {
        return valorImovel;
    }

    public int getPrazoFinanciamento() {
        return prazoFinanciamento;
    }

    public double getTaxaJurosAnual() {
        return taxaJurosAnual;
    }

    // Método abstrato para calcular o pagamento mensal
    public abstract double calcularPagamentoMensal() throws AcrescimoMaiorDoQueJurosException;

    public double calcularTotalPagamento() {
        double pagamentoMensal;
        try {
            pagamentoMensal = calcularPagamentoMensal();
        } catch (AcrescimoMaiorDoQueJurosException e) {
            pagamentoMensal = 0; // Tratamento de erro
        }
        int totalMeses = prazoFinanciamento * 12;
        return pagamentoMensal * totalMeses;
    }
}
