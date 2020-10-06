
public class String_char {

	public static void main(String[] args) {

		String name = "jyoti";
		String lastname = "pandey";

		char[] Array_cha = name.toCharArray();
		char[] Array_chal = lastname.toCharArray();

		int len =Math.max(name.length(), lastname.length());
		String result = "";

		for (int i=0 ;i < len ;i++) {
			if (  i < Array_cha.length) {
				result = result + Array_cha[i];
			}
			if (  i < Array_chal.length) {
				result = result + Array_chal[i];
			}
			
		}
		System.out.println(result);
	}
}