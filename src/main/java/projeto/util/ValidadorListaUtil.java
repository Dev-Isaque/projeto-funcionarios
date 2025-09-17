package projeto.util;

import java.util.List;

public class ValidadorListaUtil {

    private ValidadorListaUtil() {
    }

    public static boolean vazia(List<?> lista) {
        return lista == null || lista.isEmpty();
    }

    public static boolean vaziaPadrao(List<?> lista) {
        if (vazia(lista)) {
            System.out.println("Nenhum funcionário cadastrado.");
            return true;
        }
        return false;
    }

    public static boolean vaziaComMensagem(List<?> lista, String mensagem) {
        if (vazia(lista)) {
            System.out.println(mensagem);
            return true;
        }
        return false;
    }
}
