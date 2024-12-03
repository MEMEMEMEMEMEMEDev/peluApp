package peluapp.utils;

public class InputValidator {

    // Valida que una fecha y hora esté en el formato "YYYY-MM-DD HH:MM:SS"
    public static boolean validarFechaHora(String fechaHora) {
        return fechaHora != null && fechaHora.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
    }

    // Valida que un texto no supere la longitud máxima y contenga solo caracteres válidos
    public static boolean validarTexto(String texto, int maxLength) {
        return texto != null && texto.length() <= maxLength && texto.matches("[a-zA-Z0-9 áéíóúÁÉÍÓÚñÑ.,]*");
    }

    // Valida que un número entero esté dentro de un rango
    public static boolean validarNumero(String numero, int min, int max) {
        try {
            int valor = Integer.parseInt(numero);
            return valor >= min && valor <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
