import java.util.Scanner;


public class Brackets {
	
	static final int lcurlbr = '{';
	static final int rcurlbr = '}';
	static final int lsqbr = '[';
	static final int rsqbr = ']';
	static final int lroundbr = '(';
	static final int rroundbr = ')';
	static boolean valid = true;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String[] expr = new String[1];
		expr[0] = sc.next();
		
		isValidExp(expr);

		
		sc.close();
		
	}

	public static void isValidExp(String[] expr){
		
		int flag;
		int result = 0;
		
		char temp = expr[0].charAt(0);
		expr[0] = expr[0].substring(1);
		
		
		if(temp == lroundbr){
			result += roundbr(expr);
			if(valid == false ){
				System.out.printf("NO");
				return;
			}
			if(expr[0].length() != 0){
				valid = false;
			}
		}
		
		else if(temp == lcurlbr){
			result += curlbr(expr);
			if(valid == false){
				System.out.printf("NO");
				return;
			}
			if(expr[0].length() != 0)
				valid = false;
		}
		else if(temp == lsqbr){
			result += sqbr(expr);
			if(valid == false){
				System.out.printf("NO");
				return;
			}
			if(expr[0].length() != 0)
				valid = false;
		}
		else
			valid = false;
	
		if(valid){
			System.out.printf("%d\n" ,result);
		}
		else{
			System.out.printf("NO\n");
		}
	}
	
	public static int roundbr(String[] expr){
		char temp;
		int value = 0;
		for(int i = 0; i < expr[0].length(); ++i){
			temp = expr[0].charAt(i);
			if(Character.isDigit(temp)){
				continue;
			}
			else{
				if(temp == rroundbr){
					if(i == 0){
						value += 0;
					}
					else{
						value =  Integer.parseInt(expr[0].substring(0, i));
					}
					expr[0] = expr[0].substring(i+1);
					return value;
				}
				else{
					valid = false;
					return -1;
				}
			}
		}
		valid = false;
		return -1;   // No closing bracket
	}
	
	public static int sqbr(String[] expr){
		char temp;
		int value = 0;
		for(int i = 0; i < expr[0].length(); ++i){
			temp = expr[0].charAt(i);
			if(Character.isDigit(temp)){
				continue;
			}
			else{
				if(temp == rsqbr){
					if(i == 0){
						value += 0;
					}
					else{
						value +=  Integer.parseInt(expr[0].substring(0, i));
					}
					expr[0] = expr[0].substring(i+1);
					return value;
				}
				if(temp == lroundbr){
					if(i != 0)
						value += Integer.parseInt(expr[0].substring(0, i));
					expr[0] = expr[0].substring(i+1);
					value += 2 * roundbr(expr);
					i = -1;                    // because roundbr returns new string from index 0;					
				}
				else{
					valid = false;
					return -1;
				}
		    }
	    }
		valid = false; 
		return -1;              //No closing bracket
	}
	
	public static int curlbr(String[] expr){
		char temp;
		int value = 0;
		for(int i = 0; i < expr[0].length(); ++i){
			temp = expr[0].charAt(i);
			if(Character.isDigit(temp)){
				continue;
			}
			else{
				if(temp == rcurlbr){
					if(i == 0){
						value += 0;
					}
					else{
						value +=  Integer.parseInt(expr[0].substring(0, i));
					}
					expr[0] = expr[0].substring(i+1);
					return value;
				}
				if(temp == lsqbr){
					if(i != 0)
						value += Integer.parseInt(expr[0].substring(0, i));
					expr[0] = expr[0].substring(i+1);
					value += 2 * sqbr(expr);
					i = -1;                    //because sqbr returns new string from index 0
				}
				else{
					valid = false;
					return -1;
				}
			}
		}
		valid = false;
		return -1;
	}
}
