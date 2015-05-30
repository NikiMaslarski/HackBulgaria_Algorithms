import java.util.Scanner;

public class Brackets {

	static boolean valid = true;
	static int result = 0;
	static String expression;
	static int currentIndex = 0;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		expression = sc.next();

		ValidateAndCalculate();

		sc.close();

	}

	public static void ValidateAndCalculate() {

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
			valid = false;
			break;
		}

		if (valid && expression.length() == currentIndex) {
			System.out.printf("%d\n", result);
		} else {
			System.out.printf("NO\n");
		}
	}

	public static int evaluateRoundBracket() {
		int value = 0;
		for (int i = currentIndex; i < expression.length(); ++i) {
			if (Character.isDigit(expression.charAt(i))) {
				continue;
			} else {
				if (expression.charAt(i) == ')') {
					if (i == currentIndex) {
						value += 0;
					} else {
						value = Integer.parseInt(expression.substring(
								currentIndex, i));
					}
					currentIndex = i + 1;
					return value;
				} else {
					valid = false;
					return -1;
				}
			}
		}
		valid = false;
		return -1; // No closing bracket
	}

	public static int evaluateSquareBracket() {
		int value = 0;

		for (int i = currentIndex; i < expression.length(); ++i) {
			if (Character.isDigit(expression.charAt(i))) {
				continue;
			} else {
				if (expression.charAt(i) == ']') {
					if (i == currentIndex) {
						value += 0;
					} else {
						value += Integer.parseInt(expression.substring(
								currentIndex, i));
					}
					currentIndex = i + 1;
					return value;
				}
				if (expression.charAt(i) == '(') {
					if (i != currentIndex)
						value += Integer.parseInt(expression.substring(
								currentIndex, i));
					currentIndex = i + 1;
					value += 2 * evaluateRoundBracket();
					i = currentIndex - 1; // because evaluateRoundBracket runs
											// thru the string;
				} else {
					valid = false;
					return -1;
				}
			}
		}
		valid = false;
		return -1; // No closing bracket
	}

	public static int evaluateCurlBracket() {
		int value = 0;
		for (int i = currentIndex; i < expression.length(); ++i) {
			if (Character.isDigit(expression.charAt(i))) {
				continue;
			} else {
				if (expression.charAt(i) == '}') {
					if (i == currentIndex) {
						value += 0;
					} else {
						value += Integer.parseInt(expression.substring(
								currentIndex, i));
					}
					currentIndex = i + 1;
					return value;
				}
				if (expression.charAt(i) == '[') {
					if (i != currentIndex)
						value += Integer.parseInt(expression.substring(
								currentIndex, i));
					currentIndex = i + 1;
					value += 2 * evaluateSquareBracket();
					i = currentIndex - 1; // because evaluateSquareBracket runs
											// thru the string
				} else {
					valid = false;
					return -1;
				}
			}
		}
		valid = false;
		return -1; // No closing bracket
	}
}
