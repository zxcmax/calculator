import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println(calc(scanner.nextLine()));
        } catch (Exception e) {
            System.out.println("Ошибка: Вы ввели некорректные данные.");
        }
    }

    public static String calc(String input) throws IOException {
        String[] romanNumbers = {
                "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        List<String> allowedRomanNumbers = Arrays.asList(Arrays.copyOfRange(romanNumbers, 0, 10));

        Integer[] arabicNumbers = new Integer[100];
        for (int i = 0; i < 100; i++) {
            arabicNumbers[i] = i + 1;
        }
        List<Integer> allowedArabicNumbers = Arrays.asList(Arrays.copyOfRange(arabicNumbers, 0, 10));

        Map<String, Integer> romanArabicNumbers = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            romanArabicNumbers.put(romanNumbers[i], arabicNumbers[i]);
        }

        String[] inputArray = input.split(" ");
        if (inputArray.length != 3) {
            throw new IOException();
        }

        String firstNumber = inputArray[0];
        String operation = inputArray[1];
        String secondNumber = inputArray[2];

        int intermediateResult;
        boolean isRomanNumber = false;

        if (allowedRomanNumbers.contains(firstNumber) && allowedRomanNumbers.contains(secondNumber)) {
            int first = romanArabicNumbers.get(firstNumber);
            int second = romanArabicNumbers.get(secondNumber);
            isRomanNumber = true;
            intermediateResult = getIntermediateResult(operation, first, second);
        } else {
            int first = Integer.parseInt(firstNumber);
            int second = Integer.parseInt(secondNumber);
            if (allowedArabicNumbers.contains(first) && allowedArabicNumbers.contains(second)) {
                intermediateResult = getIntermediateResult(operation, first, second);
            } else {
                throw new IOException();
            }
        }

        if (isRomanNumber) {
            if (intermediateResult < 1) {
                throw new IOException();
            }
            return romanNumbers[intermediateResult - 1];
        } else {
            return Integer.toString(intermediateResult);
        }
    }

    private static int getIntermediateResult(String operation, int first, int second) throws IOException {
        return switch (operation) {
            case "+" -> first + second;
            case "-" -> first - second;
            case "*" -> first * second;
            case "/" -> first / second;
            default -> throw new IOException();
        };
    }
}