package projeto;

import projeto.model.Funcionario;
import projeto.service.FuncionarioService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        FuncionarioService service = new FuncionarioService();
        List<Funcionario> funcionarios = new ArrayList<>();

        // Criando funcionários
        funcionarios.add(new Funcionario("Maria", LocalDate.parse("18/10/2000", formatter), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.parse("12/05/1990", formatter), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.parse("02/05/1961", formatter), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", formatter), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", formatter), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", formatter), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", formatter), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.parse("08/07/1994", formatter), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.parse("24/05/2003", formatter), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", formatter), new BigDecimal("2799.93"), "Gerente"));

        boolean sair = false;

        while (!sair) {
            System.out.println("\n----- Sistema de Cadastro de Funcionários -----");
            System.out.println("1 - Listar Funcionários");
            System.out.println("2 - Remover Funcionário");
            System.out.println("3 - Aplicar Aumento");
            System.out.println("4 - Agrupar por Cargo");
            System.out.println("5 - Aniversariantes");
            System.out.println("6 - Funcionário Mais Velho");
            System.out.println("7 - Ordem Alfabética");
            System.out.println("8 - Total de Salários");
            System.out.println("9 - Quantos salários mínimos recebe");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> service.listaFuncionarios(funcionarios);
                case 2 -> {
                    System.out.print("\nDigite o nome do funcionário a remover: ");
                    String nome = sc.nextLine();
                    service.removerPorNome(funcionarios, nome);
                }
                case 3 -> {
                    System.out.print("\nDigite o percentual de aumento: ");
                    BigDecimal porcentagem = sc.nextBigDecimal();
                    service.aplicarAumento(funcionarios, porcentagem);
                }
                case 4 -> service.imprimirAgrupadosPorCargo(funcionarios);
                case 5 -> {
                    System.out.print("\nDigite os meses separados por vírgula (ex: 1,5,10): ");
                    String[] mesesInput = sc.nextLine().split(",");
                    int[] meses = new int[mesesInput.length];
                    for (int i = 0; i < mesesInput.length; i++) {
                        meses[i] = Integer.parseInt(mesesInput[i].trim());
                    }
                    service.imprimirAniversariantes(funcionarios, meses);
                }
                case 6 -> service.funcionarioMaisVelho(funcionarios);
                case 7 -> service.ordenarPorNome(funcionarios);
                case 8 -> service.totalSalarios(funcionarios);
                case 9 -> service.calcularSalariosMinimos(funcionarios, new BigDecimal("1212.00"));
                case 0 -> {
                    System.out.println("Saindo do sistema...");
                    sair = true;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }

            if (!sair) {
                System.out.println("\nPressione Enter para voltar ao menu ou digite '0' para sair...");
                String voltar = sc.nextLine();
                if (voltar.equals("0")) {
                    sair = true;
                }
            }
        }

        sc.close();
    }
}