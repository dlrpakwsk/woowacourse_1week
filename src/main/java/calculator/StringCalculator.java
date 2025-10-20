package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private static final String DEFAULT_DELIMITERS = "[,:]";

    public static int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String[] numbers = split(input);
        return sum(numbers);
    }

    private static String[] split(String input) {
        Matcher matcher = Pattern.compile("//(.)\\n(.*)").matcher(input);

        if (matcher.matches()) {
            String customDelimiter = matcher.group(1);
            String numbers = matcher.group(2);
            return numbers.split(Pattern.quote(customDelimiter));
        }

        return input.split(DEFAULT_DELIMITERS);
    }

    private static int sum(String[] numbers) {
        int total = 0;
        for (String number : numbers) {
            total += parsePositiveNumber(number);
        }
        return total;
    }

    private static int parsePositiveNumber(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        value = value.trim();

        if (value.startsWith("-")) {
            throw new IllegalArgumentException();
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
