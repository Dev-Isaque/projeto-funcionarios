package projeto.service;

import projeto.model.Funcionario;

import java.util.List;

public class FuncionarioService {

    // Remove os usuarios cujo o nome seja pasado na função
    public void removerPorNome(List<Funcionario> lista, String nome) {
        lista.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }

    public void listarFuncionarios(List<Funcionario> lista) {

    }

}
