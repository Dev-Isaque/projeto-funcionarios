package projeto.service;

import projeto.model.Funcionario;
import projeto.util.FormatadorUtil;
import projeto.util.ValidadorListaUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class FuncionarioService {

    public void removerPorNome(List<Funcionario> lista, String nome) {
        if (ValidadorListaUtil.vaziaPadrao(lista)) return;

        boolean removido = lista.removeIf(f -> f.getNome().equals(nome));
        if (removido) {
            System.out.println("Funcionário '" + nome + "' removido com sucesso.");
        } else {
            System.out.println("Nome inválido ou funcionário não cadastrado.");
        }
    }

    public void listaFuncionarios(List<Funcionario> lista) {
        if (ValidadorListaUtil.vaziaPadrao(lista)) return;

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
        if (ValidadorListaUtil.vaziaPadrao(lista)) return;

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
        if (ValidadorListaUtil.vaziaPadrao(lista)) return;

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
        if (ValidadorListaUtil.vaziaPadrao(lista)) return;

        List<Funcionario> aniversariantes = lista.stream()
                .filter(f -> Arrays.stream(meses)
                        .anyMatch(m -> m == f.getDataNascimento().getMonthValue()))
                .toList();

        listaFuncionarios(aniversariantes);
    }

    public void funcionarioMaisVelho(List<Funcionario> lista) {
        if (ValidadorListaUtil.vaziaPadrao(lista)) return;

        Funcionario fMaisVelho = lista.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);

        System.out.println(fMaisVelho.getNome() + " | "
                + fMaisVelho.getIdade() + " anos"
        );
    }

    public void ordenarPorNome(List<Funcionario> lista) {
        if (ValidadorListaUtil.vaziaPadrao(lista)) return;

        List<Funcionario> listaOrdenada = lista.stream()
                .sorted((f1, f2) -> f1.getNome().compareToIgnoreCase(f2.getNome()))
                .collect(Collectors.toList());

        listaFuncionarios(listaOrdenada);
    }

    public void totalSalarios(List<Funcionario> lista) {
        if (ValidadorListaUtil.vaziaComMensagem(lista,
                "Nenhum funcionário cadastrado para calcular total de salários."
        )) return;

        BigDecimal somaTotal = lista.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Soma total dos salários: " + FormatadorUtil.formatarSalario(somaTotal));
    }

    public void calcularSalariosMinimos(List<Funcionario> lista, BigDecimal salarioMinimo) {
        if (ValidadorListaUtil.vaziaPadrao(lista)) return;

        for (Funcionario f : lista) {
            BigDecimal quantidade = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println(f.getNome() +
                    " recebe " +
                    quantidade +
                    " salários mínimos.");
        }
    }

}
