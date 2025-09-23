import java.util.Scanner;

public class Input {

    public static String scanString(String prompt, Scanner scan) {
        System.out.print(prompt);
        String line = scan.nextLine();
        while (line == null || line.trim().isEmpty()) {
            System.out.print("Entrada inválida. " + prompt);
            line = scan.nextLine();
        }
        return line.trim();
    }

    public static int scanInt(String prompt, Scanner scan) {
        while (true) {
            System.out.print(prompt);
            String line = scan.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número inteiro válido.");
            }
        }
    }

    public static double scanDouble(String prompt, Scanner scan) {
        while (true) {
            System.out.print(prompt);
            String line = scan.nextLine().trim().replace(",", ".");
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido (ex: 12.5).");
            }
        }
    }

    public static double scanPositiveDouble(String prompt, Scanner scan) {
        double v;
        do {
            v = scanDouble(prompt, scan);
            if (v <= 0) System.out.println("O valor deve ser maior que zero.");
        } while (v <= 0);
        return v;
    }

    public static boolean scanYesNo(String prompt, Scanner scan) {
        while (true) {
            System.out.print(prompt);
            String line = scan.nextLine().trim().toLowerCase();
            if (line.equals("s")) return true;
            if (line.equals("n")) return false;
            System.out.println("Entrada inválida! Digite 'S' para sim ou 'N' para não.");
        }
    }
}
