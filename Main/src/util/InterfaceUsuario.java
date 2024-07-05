package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InterfaceUsuario {
    private Scanner entrada;

    public InterfaceUsuario() {
        this.entrada = new Scanner(System.in);
    }

    public double pedirValorImovel() {
        double valorImovel = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.print("Digite o valor do imóvel: R$ ");
                valorImovel = entrada.nextDouble();
                if (valorImovel < 0) {
                    throw new IllegalArgumentException("O valor do imóvel deve ser positivo.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número válido.");
                entrada.nextLine(); // Limpar o buffer do scanner
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return valorImovel;
    }

    public int pedirPrazoFinanciamento() {
        int prazoFinanciamento = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.print("Digite o prazo de financiamento (em anos): ");
                prazoFinanciamento = entrada.nextInt();
                if (prazoFinanciamento <= 0 || prazoFinanciamento > 30) {
                    throw new IllegalArgumentException("Prazo de financiamento inválido! Por favor, digite um valor entre 1 e 30.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número válido.");
                entrada.nextLine(); // Limpar o buffer do scanner
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return prazoFinanciamento;
    }

    public double pedirTaxaJuros() {
        double taxaJuros = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.print("Taxa de juros anual (em %): ");
                taxaJuros = entrada.nextDouble();
                if (taxaJuros <= 0 || taxaJuros > 100) {
                    throw new IllegalArgumentException("Taxa de juros incorreta! Por favor, digite um valor válido.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número válido.");
                entrada.nextLine(); // Limpar o buffer do scanner
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return taxaJuros;
    }

    public String pedirTipoImovel() {
        String tipoImovel = "";
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.print("Digite o tipo de imóvel (c - Casa, a - Apartamento, t - Terreno): ");
                tipoImovel = entrada.next().toLowerCase();
                if (!tipoImovel.equals("c") && !tipoImovel.equals("a") && !tipoImovel.equals("t")) {
                    throw new IllegalArgumentException("Tipo de imóvel inválido. Tente novamente.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite uma letra válida.");
                entrada.nextLine(); // Limpar o buffer do scanner
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return tipoImovel;
    }

    public double pedirTamanhoAreaConstruida() {
        double tamanhoAreaConstruida = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.print("Digite o tamanho da área construída (em m²): ");
                tamanhoAreaConstruida = entrada.nextDouble();
                if (tamanhoAreaConstruida < 0) {
                    throw new IllegalArgumentException("O tamanho da área construída deve ser positivo.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número válido.");
                entrada.nextLine(); // Limpar o buffer do scanner
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return tamanhoAreaConstruida;
    }

    public double pedirTamanhoTerreno() {
        double tamanhoTerreno = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.print("Digite o tamanho do terreno (em m²): ");
                tamanhoTerreno = entrada.nextDouble();
                if (tamanhoTerreno < 0) {
                    throw new IllegalArgumentException("O tamanho do terreno deve ser positivo.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número válido.");
                entrada.nextLine(); // Limpar o buffer do scanner
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return tamanhoTerreno;
    }

    public int pedirNumeroVagasGaragem() {
        int numeroVagasGaragem = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.print("Digite o número de vagas na garagem: ");
                numeroVagasGaragem = entrada.nextInt();
                if (numeroVagasGaragem < 0) {
                    throw new IllegalArgumentException("O número de vagas na garagem deve ser positivo.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro válido.");
                entrada.nextLine(); // Limpar o buffer do scanner
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return numeroVagasGaragem;
    }

    public int pedirNumeroAndar() {
        int numeroAndar = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.print("Digite o número do andar: ");
                numeroAndar = entrada.nextInt();
                if (numeroAndar < 0) {
                    throw new IllegalArgumentException("O número do andar deve ser positivo.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro válido.");
                entrada.nextLine(); // Limpar o buffer do scanner
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return numeroAndar;
    }

    public String pedirTipoZona() {
        String tipoZona = "";
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.print("Digite o tipo de zona (residencial ou comercial): ");
                tipoZona = entrada.next().toLowerCase();
                if (!tipoZona.equals("residencial") && !tipoZona.equals("comercial")) {
                    throw new IllegalArgumentException("Tipo de zona inválido. Tente novamente.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um valor válido.");
                entrada.nextLine(); // Limpar o buffer do scanner
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return tipoZona;
    }
}


