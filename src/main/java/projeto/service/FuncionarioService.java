package projeto.service;

import projeto.model.Funcionario;
import projeto.util.FormatadorUtil;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class FuncionarioService {

    public void removerPorNome(List<Funcionario> lista, String nome) {
        lista.removeIf(f -> f.getNome().equals(nome));
    }

    public void listaFuncionarios(List<Funcionario> lista) {
        if (lista.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
            return;
        }

        for (Funcionario f : lista) {
            String nome = f.getNome();
            String data = FormatadorUtil.formatarData(f.getDataNascimento());
            String salario = FormatadorUtil.formatarSalario(f.getSalario());
            String cargo = f.getFuncao();

            System.out.println(nome + " | "
                    + data + " | "
                    + salario + " | "
                    + cargo
            );
        }
    }

    public void aplicarAumento(List<Funcionario> lista, BigDecimal porcentagem) {
        if (lista.isEmpty() || porcentagem == null) {
            System.out.println("Nenhum funcionário cadastrado ou nenhuma porcentagem passada");
            return;
        }

        for (Funcionario f : lista) {

            BigDecimal salarioAtual = f.getSalario();

            BigDecimal salarioAumentado = salarioAtual.multiply(porcentagem).divide(new BigDecimal("100"));

            f.setSalario(salarioAtual.add(salarioAumentado));
        }
    }

    private Map<String, List<Funcionario>> agruparPorCargo(List<Funcionario> lista) {
        return lista.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    public void imprimirAgrupadosPorCargo(List<Funcionario> lista) {

        if (lista == null | lista.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
            return;
        }

        agruparPorCargo(lista).forEach((cargo, funcionarios) -> {
            System.out.println("Cargo: " + cargo);
            funcionarios.forEach(f -> System.out.println(
                    f.getNome() + " | " +
                            FormatadorUtil.formatarData(f.getDataNascimento()) + " | " +
                            FormatadorUtil.formatarSalario(f.getSalario())
            ));
        });
    }

    public void imprimirAniversariantes(List<Funcionario> lista, int... meses) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }

        List<Funcionario> aniversariantes = lista.stream()
                .filter(f -> Arrays.stream(meses)
                        .anyMatch(m -> m == f.getDataNascimento().getMonthValue()))
                .toList();

        listaFuncionarios(aniversariantes);
    }

    public void funcionarioMaisVelho(List<Funcionario> lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }

        Funcionario fMaisVelho = lista.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);

        System.out.println(fMaisVelho.getNome() + " | "
                + fMaisVelho.getIdade() + " anos"
        );
    }

    public void ordenarPorNome(List<Funcionario> lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }

        List<Funcionario> listaOrdenada = lista.stream()
                .sorted((f1, f2) -> f1.getNome().compareToIgnoreCase(f2.getNome()))
                .collect(Collectors.toList());

        listaFuncionarios(listaOrdenada);
    }

    public BigDecimal totalSalarios(List<Funcionario> lista) {
        if (lista == null || lista.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal somaTotal = lista.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Soma total dos salários: " + FormatadorUtil.formatarSalario(somaTotal));

        return somaTotal;
    }

}
