package projeto.service;

import projeto.model.Funcionario;
import projeto.util.FormatadorUtil;

import java.math.BigDecimal;
import java.util.List;

public class FuncionarioService {

    // Remove os usuarios cujo o nome seja pasado na função
    public void removerPorNome(List<Funcionario> lista, String nome) {
        lista.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }

    public void listarFuncionarios(List<Funcionario> lista) {
        if (lista.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
            return;
        }

        for (Funcionario funcionario : lista) {
            String nome = funcionario.getNome();
            String data = FormatadorUtil.formatarData(funcionario.getDataNascimento());
            String salario = FormatadorUtil.formatarSalario(funcionario.getSalario());
            String cargo = funcionario.getFuncao();

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

        for (Funcionario funcionario : lista) {

            BigDecimal salarioAtual = funcionario.getSalario();

            BigDecimal salarioAumentado = salarioAtual.multiply(porcentagem).divide(new BigDecimal("100"));

            funcionario.setSalario(salarioAtual.add(salarioAumentado));
        }
    }



}
