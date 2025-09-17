package projeto.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatadorUtil {

    // Formata Data
    public static String formatarData(LocalDate data){
        if (data == null) {
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

    // Formata Salario
    public static String formatarSalario(BigDecimal salario) {
        if (salario == null) {
            return "";
        }

        NumberFormat nFormatado = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return nFormatado.format(salario);
    }

}
