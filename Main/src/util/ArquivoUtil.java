package util;

import modelo.Apartamento;
import modelo.Casa;
import modelo.Financiamento;
import modelo.Terreno;

import java.io.*;
import java.util.ArrayList;

public class ArquivoUtil {

    // Método para salvar financiamentos em um arquivo de texto
    public static void salvarFinanciamentosEmTexto(String caminhoArquivo, ArrayList<Financiamento> financiamentos) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            writer.write("==== Simulação de Financiamento ====\n");
            int count = 1;
            for (Financiamento financiamento : financiamentos) {
                writer.write(String.format("Imóvel %d\n", count++));
                writer.write(String.format("Tipo de Imóvel: %s\n", financiamento.getClass().getSimpleName()));
                writer.write(String.format("Valor: R$ %.2f\n", financiamento.getValorImovel()));
                writer.write(String.format("Prazo de Financiamento: %d anos\n", financiamento.getPrazoFinanciamento()));
                writer.write(String.format("Taxa de Juros anual: %.2f%%\n", financiamento.getTaxaJurosAnual()));

                if (financiamento instanceof Casa) {
                    Casa casa = (Casa) financiamento;
                    writer.write(String.format("Área construída: %.2f m²\n", casa.getTamanhoAreaConstruida()));
                    writer.write(String.format("Tamanho do terreno: %.2f m²\n", casa.getTamanhoTerreno()));
                } else if (financiamento instanceof Apartamento) {
                    Apartamento apartamento = (Apartamento) financiamento;
                    writer.write(String.format("Número de vagas na garagem: %d\n", apartamento.getNumeroVagasGaragem()));
                    writer.write(String.format("Número do andar: %d\n", apartamento.getNumeroAndar()));
                } else if (financiamento instanceof Terreno) {
                    Terreno terreno = (Terreno) financiamento;
                    writer.write(String.format("Tipo de zona: %s\n", terreno.getTipoZona()));
                }
                writer.write(String.format("Valor total deste financiamento: R$ %.2f\n", financiamento.calcularTotalPagamento()));
                writer.write("\n");
            }
        }
    }

    // Método para ler financiamentos de um arquivo de texto
    public static ArrayList<Financiamento> lerFinanciamentosDeTexto(String caminhoArquivo) throws IOException, ClassNotFoundException {
        ArrayList<Financiamento> financiamentos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Imovel")) {
                    // Ignora a primeira linha e as linhas de separação
                    for (int i = 0; i < 4; i++) {
                        reader.readLine();
                    }
                    String tipoImovel = reader.readLine().split(": ")[1].trim();
                    double valorImovel = Double.parseDouble(reader.readLine().split(": ")[1].replace("R$ ", "").trim());

                    // Leitura do prazo de financiamento
                    String prazoLine = reader.readLine().split(": ")[1].trim();
                    int prazoFinanciamento = Integer.parseInt(prazoLine.split("\\(")[1].split(" ")[0]); // Extrai apenas o número

                    double taxaJuros = Double.parseDouble(reader.readLine().split(": ")[1].replace("%", "").trim());

                    switch (tipoImovel) {
                        case "Casa":
                            double tamanhoAreaConstruida = Double.parseDouble(reader.readLine().split(": ")[1].replace(" m²", "").trim());
                            double tamanhoTerreno = Double.parseDouble(reader.readLine().split(": ")[1].replace(" m²", "").trim());
                            financiamentos.add(new Casa(valorImovel, prazoFinanciamento, taxaJuros, tamanhoAreaConstruida, tamanhoTerreno));
                            break;
                        case "Apartamento":
                            int numeroVagasGaragem = Integer.parseInt(reader.readLine().split(": ")[1].trim());
                            int numeroAndar = Integer.parseInt(reader.readLine().split(": ")[1].trim());
                            financiamentos.add(new Apartamento(valorImovel, prazoFinanciamento, taxaJuros, numeroVagasGaragem, numeroAndar));
                            break;
                        case "Terreno":
                            String tipoZona = reader.readLine().split(": ")[1].trim();
                            financiamentos.add(new Terreno(valorImovel, prazoFinanciamento, taxaJuros, tipoZona));
                            break;
                        default:
                            throw new ClassNotFoundException("Tipo de imóvel não reconhecido");
                    }
                }
            }
        }
        return financiamentos;
    }


    // Método auxiliar para converter uma linha de texto em um objeto Financiamento
    private static Financiamento parseFinanciamento(String linha) throws ClassNotFoundException {
        String[] dados = linha.split(",");
        String tipoImovel = dados[0];
        double valorImovel = Double.parseDouble(dados[1]);
        int prazoFinanciamento = Integer.parseInt(dados[2]);
        double taxaJuros = Double.parseDouble(dados[3]);

        switch (tipoImovel) {
            case "Casa":
                double tamanhoAreaConstruida = Double.parseDouble(dados[4]);
                double tamanhoTerreno = Double.parseDouble(dados[5]);
                return new Casa(valorImovel, prazoFinanciamento, taxaJuros, tamanhoAreaConstruida, tamanhoTerreno);
            case "Apartamento":
                int numeroVagasGaragem = Integer.parseInt(dados[4]);
                int numeroAndar = Integer.parseInt(dados[5]);
                return new Apartamento(valorImovel, prazoFinanciamento, taxaJuros, numeroVagasGaragem, numeroAndar);
            case "Terreno":
                String tipoZona = dados[4];
                return new Terreno(valorImovel, prazoFinanciamento, taxaJuros, tipoZona);
            default:
                throw new ClassNotFoundException("Tipo de imóvel não reconhecido");
        }
    }

    // Método para salvar financiamentos em um arquivo serializado
    public static void salvarFinanciamentosSerializados(String caminhoArquivo, ArrayList<Financiamento> financiamentos) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(financiamentos);
        }
    }

    // Método para ler financiamentos de um arquivo serializado
    public static ArrayList<Financiamento> lerFinanciamentosSerializados(String caminhoArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoArquivo))) {
            return (ArrayList<Financiamento>) ois.readObject();
        }
    }
}
