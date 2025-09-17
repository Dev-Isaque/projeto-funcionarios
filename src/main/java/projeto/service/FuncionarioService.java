package projeto.service;

import projeto.model.Funcionario;
import projeto.util.FormatadorUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                    + cargo + " | "
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

}
