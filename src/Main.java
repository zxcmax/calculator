import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println(calc(scanner.nextLine()));
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calc(String input) throws Exception {
        List<String> romanNumbers = Arrays.asList(
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
        );

        String[] inputArray = input.trim().split("\\s*[+\\-*/]\\s*");
        if (inputArray.length != 2) {
            throw new Exception("Некорректный формат ввода. Ожидается формат: 'a + b'.");
        }

        String firstNumber = inputArray[0];
        String secondNumber = inputArray[1];
        String operation = input.replaceAll("[^+\\-*/]", "");

        int first;
        int second;
        int intermediateResult;
        boolean isRomanNumber = false;

        if (romanNumbers.contains(firstNumber) && romanNumbers.contains(secondNumber)) {
            first = romanNumbers.indexOf(firstNumber) + 1;
            second = romanNumbers.indexOf(secondNumber) + 1;
            isRomanNumber = true;
        } else {
            try {
                first = Integer.parseInt(firstNumber);
                second = Integer.parseInt(secondNumber);

            } catch (NumberFormatException e) {
                throw new Exception("Некорректный формат чисел. Числа должны быть только арабскими или только римскими от 1 до 10 (от I до X) включительно.");
            }
        }

        if (first < 1 || first > 10 || second < 1 || second > 10) {
            throw new Exception("Числа должны быть в диапазоне от 1 до 10 (от I до X) включительно.");
        }

        intermediateResult = getIntermediateResult(operation, first, second);

        if (isRomanNumber) {
            if (intermediateResult < 1) {
                throw new Exception("Результат работы с римскими числами не может быть меньше I.");
            }
            return romanNumbers.get(intermediateResult - 1);
        } else {
            return Integer.toString(intermediateResult);
        }
    }

    private static int getIntermediateResult(String operation, int first, int second) throws Exception {
        return switch (operation) {
            case "+" -> first + second;
            case "-" -> first - second;
            case "*" -> first * second;
            case "/" -> first / second;
            default -> throw new Exception("Некорректная операция. Допустимые операции: +, -, *, /.");
        };
    }
}