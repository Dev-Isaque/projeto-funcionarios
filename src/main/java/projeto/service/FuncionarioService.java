package projeto.service;

import projeto.model.Funcionario;
import projeto.util.FormatadorUtil;

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



}
