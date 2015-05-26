import java.util.Scanner;


public class Palindromes {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String word = sc.next();
		word += word;
		
		printPalindromes(word);
		sc.close();

	}
	
	public static boolean isPalindrome(String substr){
		for(int i = 0; i < substr.length()/2; ++i){
			if(substr.charAt(i) != substr.charAt(substr.length() - 1 - i)){
				return false;
			}
		}
		return true;
	}
	
	public static void printPalindromes(String word){
		boolean noPalindrom = true;
		for(int i = 0; i < word.length()/2; ++i){
			if(isPalindrome(word.substring(i, i + word.length()/2))){
				noPalindrom = false;
				System.out.printf("%s\n", word.substring(i, i + word.length()/2));
			}
		}
		if(noPalindrom){
			System.out.printf("NONE\n");
		}
	}	
}
