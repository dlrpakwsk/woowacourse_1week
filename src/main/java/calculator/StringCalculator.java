package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    private static final String DEFAULT_DELIMITERS = "[,:]";

    public void run() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        try {
            int result = add(input);
            System.out.println("결과 : " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 입력입니다: " + e.getMessage());
        }
    }

    public int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String[] numbers = split(input);
        return sum(numbers);
    }

    private String[] split(String input) {
        Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(input);

        if (matcher.matches()) {
            String customDelimiter = matcher.group(1);
            String numbers = matcher.group(2);
            return numbers.split(Pattern.quote(customDelimiter));
        }

        return input.split(DEFAULT_DELIMITERS);
    }

    private int sum(String[] numbers) {
        int total = 0;

        for (String number : numbers) {
            int num = parsePositiveNumber(number);
            total += num;
        }

        return total;
    }

    private int parsePositiveNumber(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("구분자만 있거나 빈 값이 포함되어 있습니다.");
        }

        try {
            int num = Integer.parseInt(value.trim());
            if (num < 0) {
                throw new IllegalArgumentException("음수는 허용되지 않습니다: " + num);
            }
            return num;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: " + value);
        }
    }
}
