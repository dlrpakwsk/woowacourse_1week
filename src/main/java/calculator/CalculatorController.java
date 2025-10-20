package calculator;

public class CalculatorController {
    public void run() {
        try {
            String input = View.InputView.readInput();
            int result = StringCalculator.add(input);
            View.OutputView.printResult(result);
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 입력입니다: " + e.getMessage());
        }
    }
}
