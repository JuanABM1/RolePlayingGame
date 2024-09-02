package proyecto;

import java.util.Scanner;

public class ToolsBrito {


    // Un Scanner para leer los strings
    public static String leerString() {
        return new Scanner(System.in).nextLine();
    }

    // Un scanner que lee String y si no se introduce uno se muestra un error
    public static char leerChar() {
        char c = 0;                 //se inicializa el char a cero para que si hay un error devuelte este valor
        String s = leerString();

        if (s.length() == 1)        // comprobamos que lo que se haya leido sea un caracter
        {
            c = s.charAt(0);
        } else {
            System.err.println("El dato introducido no es un caracter");
        }
        return c;
    }

    // Un Scanner que lee los integros
    public static int leerInt() {
        return new Scanner(System.in).nextInt();
    }

    // Un Scanner para leer los floats
    public static float leerFloat()
    {
        return new Scanner(System.in).nextFloat();
    }

    // Un Scanner para leer los doubles

    public static double leerDouble()
    {
        return new Scanner(System.in).nextDouble();
    }

    public static int preguntarNumeroEntreDosValores(int min_value, int max_value, String mensaje, String mensajeFinal, String mensajeCategoria) {
        int numeroPreguntas;
        boolean salir = false;


        do {
            System.out.println(mensaje);
            numeroPreguntas = leerInt();
            if (numeroPreguntas < min_value || numeroPreguntas > max_value){
                System.out.println("ERROR!!!\nPlease choose a value between " + min_value + " and " + max_value);
            }else {
                System.out.println(mensajeFinal + numeroPreguntas + mensajeCategoria);
                salir = true;
            }
        }while (!salir);
        return numeroPreguntas;
    }

    public static String convertirArrayDeCharEnString(char[] array){
        String stringResultante;
        stringResultante = String.valueOf(array);

        return stringResultante;
    }

    public static int menu(String[] opciones, String subject) {
        int option;
        boolean validOption = false;
        do {
            System.out.println(subject);
            for (int i = 0; i < opciones.length; i++) {
                System.out.println(opciones[i]);
            }
            option = ToolsBrito.leerInt();
            if (option >= 0 && option <= (opciones.length - 1)) {
                validOption = true;
            } else {
                System.out.println("Choose a valid option");
            }
        }while (!validOption);
        return option;
    }

    public static boolean pedirConfirmacion(String option) {
        char decision;
        System.out.println("Are you sure you want to " + option + " (Y or N)");
        decision = Character.toUpperCase(ToolsBrito.leerChar());

        if (decision == 'Y'){
            return true;
        }else {
            return false;
        }
    }
}

