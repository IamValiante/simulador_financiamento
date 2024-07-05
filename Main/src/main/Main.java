package main;

import modelo.Apartamento;
import modelo.Casa;
import modelo.Financiamento;
import modelo.Terreno;
import util.ArquivoUtil;
import util.InterfaceUsuario;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        ArrayList<Financiamento> financiamentos = new ArrayList<>();

        // Solicitar informações para um financiamento personalizado
        System.out.println("Digite as informações para o financiamento personalizado:");
        String tipoImovel = interfaceUsuario.pedirTipoImovel();
        double valorImovel = interfaceUsuario.pedirValorImovel();
        int prazoFinanciamento = interfaceUsuario.pedirPrazoFinanciamento();
        double taxaJuros = interfaceUsuario.pedirTaxaJuros();
        Financiamento financiamento = criarFinanciamento(interfaceUsuario, tipoImovel, valorImovel, prazoFinanciamento, taxaJuros);
        financiamentos.add(financiamento);

        // Adicionar financiamentos fixos
        financiamentos.add(new Casa(500000, 10, 10, 200, 500));
        financiamentos.add(new Casa(200000, 10, 10, 150, 300));
        financiamentos.add(new Apartamento(600000, 10, 10, 2, 5));
        financiamentos.add(new Apartamento(300000, 10, 10, 1, 10));
        financiamentos.add(new Terreno(50000, 15, 8, "residencial"));

        // Salvar financiamentos em arquivo de texto
        try {
            ArquivoUtil.salvarFinanciamentosEmTexto("financiamentos.txt", financiamentos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar financiamentos em arquivo de texto: " + e.getMessage());
        }

        // Ler financiamentos de arquivo de texto
        try {
            ArrayList<Financiamento> financiamentosLidos = ArquivoUtil.lerFinanciamentosDeTexto("financiamentos.txt");
            System.out.println("Financiamentos lidos do arquivo de texto:");
            for (Financiamento f : financiamentosLidos) {
                System.out.println(f);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler financiamentos de arquivo de texto: " + e.getMessage());
        }

        // Salvar financiamentos em arquivo serializado
        try {
            ArquivoUtil.salvarFinanciamentosSerializados("financiamentos.ser", financiamentos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar financiamentos em arquivo serializado: " + e.getMessage());
        }

        // Ler financiamentos de arquivo serializado
        try {
            ArrayList<Financiamento> financiamentosLidosSerializados = ArquivoUtil.lerFinanciamentosSerializados("financiamentos.ser");
            System.out.println("Financiamentos lidos do arquivo serializado:");
            for (Financiamento f : financiamentosLidosSerializados) {
                System.out.println(f);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler financiamentos de arquivo serializado: " + e.getMessage());
        }

        // Calcular total
        double totalValorImoveis = calcularTotalValorImoveis(financiamentos);
        double totalValorFinanciamentos = calcularTotalValorFinanciamentos(financiamentos);

        // Mostrar resultados
        System.out.printf("Total de todos os imóveis: R$%.2f%n", totalValorImoveis);
        System.out.printf("Total de todos os financiamentos: R$%.2f%n", totalValorFinanciamentos);
    }

    private static Financiamento criarFinanciamento(InterfaceUsuario interfaceUsuario, String tipoImovel, double valorImovel, int prazoFinanciamento, double taxaJuros) {
        Financiamento financiamento = null;
        switch (tipoImovel) {
            case "c":
                double tamanhoAreaConstruida = interfaceUsuario.pedirTamanhoAreaConstruida();
                double tamanhoTerreno = interfaceUsuario.pedirTamanhoTerreno();
                financiamento = new Casa(valorImovel, prazoFinanciamento, taxaJuros, tamanhoAreaConstruida, tamanhoTerreno);
                break;
            case "a":
                int numeroVagasGaragem = interfaceUsuario.pedirNumeroVagasGaragem();
                int numeroAndar = interfaceUsuario.pedirNumeroAndar();
                financiamento = new Apartamento(valorImovel, prazoFinanciamento, taxaJuros, numeroVagasGaragem, numeroAndar);
                break;
            case "t":
                String tipoZona = interfaceUsuario.pedirTipoZona();
                financiamento = new Terreno(valorImovel, prazoFinanciamento, taxaJuros, tipoZona);
                break;
        }
        return financiamento;
    }

    private static double calcularTotalValorImoveis(ArrayList<Financiamento> financiamentos) {
        double total = 0;
        for (Financiamento financiamento : financiamentos) {
            total += financiamento.getValorImovel();
        }
        return total;
    }

    private static double calcularTotalValorFinanciamentos(ArrayList<Financiamento> financiamentos) {
        double total = 0;
        for (Financiamento financiamento : financiamentos) {
            total += financiamento.calcularTotalPagamento();
        }
        return total;
    }
}
