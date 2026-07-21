import jdk.internal.org.jline.utils.AttributedCharSequence;

public class Main {

    // Si necesitas usar la variable IO, debe declararse dentro de la clase y con un valor válido
    private static final AttributedCharSequence IO = null;

    public static void main(String[] args) {
        // Nota: Si usas Java estándar, se escribe System.out.println en lugar de IO.println
        System.out.println(String.format("Hello and welcome!"));

        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
    }
}
