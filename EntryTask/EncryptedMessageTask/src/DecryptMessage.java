import java.util.Scanner;

public class DecryptMessage {
	private static int alphabetLen;
	private static int keyLen;
	private static char[] key;
	private static String alphabet;
	private static char[] message;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String encmsg = sc.nextLine();

		encmsg = fixString(encmsg);

		SplitText(encmsg);

		String decryptedMsg = Decrypt();

		System.out.printf("%s\n", decryptedMsg);

		sc.close();
	}

	private static String fixString(String encmsg) {
		int strlen = encmsg.length();
		StringBuilder temp = new StringBuilder();
		temp.append(encmsg.substring(strlen / 2, strlen));
		temp.append(encmsg.substring(0, strlen / 2));

		return temp.toString();
	}

	private static void SplitText(String encmsg) {
		int i = 0;
		int j = encmsg.length() - 1;

		while (encmsg.charAt(i) != '~')
			++i;
		alphabetLen = Integer.parseInt(encmsg.substring(0, i));

		while (encmsg.charAt(j) != '~')
			--j;
		keyLen = Integer.parseInt(encmsg.substring(j + 1, encmsg.length()));

		alphabet = encmsg.substring(i + 1, i + alphabetLen + 1);
		key = encmsg.substring(j - keyLen, j).toCharArray();
		message = encmsg.substring(i + 1 + alphabetLen, j - keyLen)
				.toCharArray();

	}

	private static String Decrypt() {
		StringBuilder decryptedMsg = new StringBuilder();
		int originalIndex;
		for (int i = 0; i < message.length; ++i) {
			originalIndex = alphabet.indexOf(message[i])
					- alphabet.indexOf(key[i % keyLen]);
			if (originalIndex < 0)
				originalIndex += alphabetLen;

			decryptedMsg.append(alphabet.charAt(originalIndex));
		}
		return decryptedMsg.toString();
	}
}