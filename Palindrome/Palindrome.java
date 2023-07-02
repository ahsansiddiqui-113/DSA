
import BasicIO.*;


/**	This program determines whether in strings are
 **	palindromes using an array of characters.
 **
 **	@author	D. Hughes
 **
 **	@version	1.0 (Mar 20042)								*/

public class Palindrome {

	
	private ASCIIPrompter		in;		// prompter for input
	private ASCIIDisplayer		out;	// displayer for output


	/**	The constructor determines if strings are palindromes
	 **	using a method reverse.									*/
	 
	public Palindrome ( ) {
		
		in = new ASCIIPrompter();
		out = new ASCIIDisplayer();
		checkPalindromes();
		in.close();
		out.close();
		
	};	// constructor
	
	
	/**	This method reads strings and checks if they are
	 **	palindromes.											*/
	
	private void checkPalindromes ( ) {
	
		String	str;		// string to be checked as palindrome
		String	reversed;	// reversed version of str

		while ( true ) {
			in.setLabel("Enter string");
			str = in.readString();
		if ( ! in.successful() ) break;
			out.setLabel("\"");
			out.writeString(str);
			reversed = reverse(str);
			if ( str.equalsIgnoreCase(reversed) ) {
				out.setLabel("\" is a palindrome");
			}
			else {
				out.setLabel("\" is not a palindrome");
			};
			out.writeEOL();
		};
	
	};	// checkPalindromes
	
	
	/**	This method returns a string in which the characters
	 **	of the parameter are in reverse order using an array of 
	 **	characters.
	 **
	 **	@param	str	string to be reversed
	 **
	 **	@return	String	string in reverse order.				*/

	private String reverse ( String str ) {

		char	[]theString;	// string as array of characters
		char	c;
		int		i;

		theString = str.toCharArray();
		for ( i=0 ; i<theString.length/2 ; i++ ) {
			c = theString[i];
			theString[i] = theString[theString.length-1-i];
			theString[theString.length-1-i] = c;
		};
		return new String(theString);

	};	// reverse
	
	
	public static void main ( String[] args ) { new Palindrome(); };
	
	
}	// Palindrome