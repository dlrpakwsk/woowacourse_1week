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
        Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(input);

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
            int num = parsePositiveNumber(number);
            total += num;
        }

        return total;
    }

    private static int parsePositiveNumber(String value) {
        try {
            int num = Integer.parseInt(value.trim());
            if (num < 0) {
                throw new IllegalArgumentException("음수는 사용할 수 없습니다. " + num);
            }
            return num;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다. " + value);
        }
    }
}
