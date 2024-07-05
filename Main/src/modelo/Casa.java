package modelo;

import util.AcrescimoMaiorDoQueJurosException;

import java.io.Serializable;

public class Casa extends Financiamento implements Serializable {
    private static final long serialVersionUID = 1L;
    private double tamanhoAreaConstruida;
    private double tamanhoTerreno;

    public Casa(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, double tamanhoAreaConstruida, double tamanhoTerreno) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tamanhoAreaConstruida = tamanhoAreaConstruida;
        this.tamanhoTerreno = tamanhoTerreno;
    }

    public double getTamanhoAreaConstruida() {
        return tamanhoAreaConstruida;
    }

    public double getTamanhoTerreno() {
        return tamanhoTerreno;
    }

    @Override
    public double calcularPagamentoMensal() throws AcrescimoMaiorDoQueJurosException {
        double taxaMensal = getTaxaJurosAnual() / 12 / 100;
        int meses = getPrazoFinanciamento() * 12;
        double valorImovel = getValorImovel();

        // Calcula o pagamento mensal base sem o acréscimo
        double pagamentoMensal = (valorImovel * taxaMensal) / (1 - Math.pow(1 + taxaMensal, -meses));

        double acrescimo = 80;

        // Adiciona o acréscimo ao pagamento mensal
        double pagamentoMensalComAcrescimo = pagamentoMensal + acrescimo;

        // Verifica se o acréscimo é maior do que a parcela de juros
        double parcelaDeJuros = valorImovel * taxaMensal;
        if (acrescimo > parcelaDeJuros) {
            pagamentoMensalComAcrescimo = pagamentoMensal + parcelaDeJuros;
            throw new AcrescimoMaiorDoQueJurosException("O valor do acréscimo foi ajustado para ser igual ao valor dos juros.");
        }

        return pagamentoMensalComAcrescimo;
    }


    @Override
    public String toString() {
        return "Casa," + getValorImovel() + "," + getPrazoFinanciamento() + "," + getTaxaJurosAnual() + "," + tamanhoAreaConstruida + "," + tamanhoTerreno;
    }
}
