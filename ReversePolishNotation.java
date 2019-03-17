
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class ReversePolishNotation {
	private static RedBlackTree variable = new RedBlackTree();

	public static boolean isNumeric(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i))) {
				return false;
			}
		}
		return true;

	}

	public static boolean isVariable(String s) {
		if (!Character.isLetter(s.charAt(0))) {
			return false;
		}
		return true;

	}

	public static BigInteger transform(Object s) throws Exception {
		String str = String.valueOf(s);
		if (isVariable(str)) {
			if (!variable.contains(str)) {
				throw new java.lang.Exception("error: no variable " + str);
			}
			return variable.value(str);
		} else {
			BigInteger bigInt = new BigInteger(str);
			return bigInt;
		}
	}

	public static void evaluate(String s) throws Exception {
		String[] arr = s.split("\\s+");
		DynamicStack number = new DynamicStack();
		for (String i : arr) {
			if (isNumeric(i)) {
				number.push(i);
			} else if (isVariable(i)) {
				number.push(i);
			} else {
				BigInteger a = transform(number.pop());
				BigInteger b;
				BigInteger c;
				BigInteger d;

				switch (i) {
				case "+":
					b = transform(number.pop());
					c = b.add(a);
					number.push(c);
					break;

				case "-":
					b = transform(number.pop());
					c = b.subtract(a);
					number.push(c);
					break;

				case "*":
					b = transform(number.pop());
					c = b.multiply(a);
					number.push(c);
					break;

				case "/":
					b = transform(number.pop());
					c = b.divide(a);
					number.push(c);
					break;

				case "%":
					b = transform(number.pop());
					c = b.mod(a);
					number.push(c);
					break;

				case "#":
					b = transform(number.pop());
					if (number.isEmpty()) {
						throw new java.lang.Exception("error: stack underflow exception");
					} else {
						c = transform(number.pop());
					}
					d = c.modPow(b, a);
					number.push(d);
					break;

				case "~":
					b = a.negate();
					number.push(b);
					break;

				case "=":
					String str = String.valueOf(number.pop());
					if (isNumeric(str)) {
						throw new java.lang.Exception("error:" + str + " not an lvalue");
					} else {
						variable.insert(str, a);
						number.push(a);
						break;
					}

				}

			}
		}
		System.out.println(String.valueOf(transform(number.pop())));

	}

	public static void main(String[] args) throws Exception {
		String input;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while ((input = br.readLine()).length() != 0) {
			evaluate(input);
		}

	}
}
