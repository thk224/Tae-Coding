//Tae Kim(tk2686)
public class MyString {
	private char[] chars;
	
	// The constructor, which initializes the object with the char array supplied.
	public MyString(char[] chars) {
		this.chars = new char[chars.length];
		System.arraycopy(chars, 0, this.chars, 0, chars.length);
	}
	
	// Returns the number of characters.
	public int length() {
		return this.chars.length;
	}
	
	// Returns the character at the specified index.
	public char charAt(int index) {
		return this.chars[index];
	}
	
	// Returns the index of the first occurrence of the specified char, or -1 if no such character occurs.
	public int indexOf(char ch) {
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == ch) {
				return i;
			}
		}
		
		return -1;
	}
	
	// Selection sort
	public void sort() {
		for (int i = 0; i < chars.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < chars.length; j++) {
				if (chars[j] < chars[min]) {
				min = j;
				}
			}
			char tmp = chars[i];
			chars[i] = chars[min];
			chars[min] = tmp;
		}
	}
	
	public static void main(String[] args) {
		// test cases
		MyString s = new MyString(new char[]{'n', 'y', 'u', 'n', 'y'});
		System.out.println(s.length());
		System.out.println(s.charAt(1));
		System.out.println(s.indexOf('x'));
		System.out.println(s.indexOf('y'));
		s.sort(); // sorted into {'n', 'n', 'u', 'y', 'y'}
		System.out.println(s.charAt(1));
		System.out.println(s.indexOf('y'));

	}
}
