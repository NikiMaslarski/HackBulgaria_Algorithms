import java.util.Scanner;

public class Brackets {

	static boolean isValid = true;
	static int result = 0;
	static String expression;
	static int currentIndex = 0;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		expression = sc.next();

		isValidateAndCalculate();

		sc.close();

	}

	public static void isValidateAndCalculate() {

		switch (expression.charAt(currentIndex++)) {
		case '(':
			result = evaluateRoundBracket();
			break;
		case '[':
			result = evaluateSquareBracket();
			break;
		case '{':
			result = evaluateCurlBracket();
			break;
		default:
			isValid = false;
			break;
		}

		if (isValid && expression.length() == currentIndex) {
			System.out.printf("%d\n", result);
		} else {
			System.out.printf("NO\n");
		}
	}

	public static int evaluateRoundBracket() {
		int currentValue = 0;
		for (int i = currentIndex; i < expression.length(); ++i) {
			if (Character.isDigit(expression.charAt(i))) {
				continue;
			} else {
				if (expression.charAt(i) == ')') {
					if (i == currentIndex) {
						currentValue += 0;
					} else {
						currentValue = Integer.parseInt(expression.substring(
								currentIndex, i));
					}
					currentIndex = i + 1;
					return currentValue;
				} else {
					isValid = false;
					return -1;
				}
			}
		}
		isValid = false;
		return -1; // No closing bracket
	}

	public static int evaluateSquareBracket() {
		int currentValue = 0;

		for (int i = currentIndex; i < expression.length(); ++i) {
			if (Character.isDigit(expression.charAt(i))) {
				continue;
			} else {
				if (expression.charAt(i) == ']') {
					if (i == currentIndex) {
						currentValue += 0;
					} else {
						currentValue += Integer.parseInt(expression.substring(
								currentIndex, i));
					}
					currentIndex = i + 1;
					return currentValue;
				}
				if (expression.charAt(i) == '(') {
					if (i != currentIndex)
						currentValue += Integer.parseInt(expression.substring(
								currentIndex, i));
					currentIndex = i + 1;
					currentValue += 2 * evaluateRoundBracket();
					i = currentIndex - 1; // because evaluateRoundBracket runs
											// thru the string;
				} else {
					isValid = false;
					return -1;
				}
			}
		}
		isValid = false;
		return -1; // No closing bracket
	}

	public static int evaluateCurlBracket() {
		int currentValue = 0;
		for (int i = currentIndex; i < expression.length(); ++i) {
			if (Character.isDigit(expression.charAt(i))) {
				continue;
			} else {
				if (expression.charAt(i) == '}') {
					if (i == currentIndex) {
						currentValue += 0;
					} else {
						currentValue += Integer.parseInt(expression.substring(
								currentIndex, i));
					}
					currentIndex = i + 1;
					return currentValue;
				}
				if (expression.charAt(i) == '[') {
					if (i != currentIndex)
						currentValue += Integer.parseInt(expression.substring(
								currentIndex, i));
					currentIndex = i + 1;
					currentValue += 2 * evaluateSquareBracket();
					i = currentIndex - 1; // because evaluateSquareBracket runs
											// thru the string
				} else {
					isValid = false;
					return -1;
				}
			}
		}
		isValid = false;
		return -1; // No closing bracket
	}
}
