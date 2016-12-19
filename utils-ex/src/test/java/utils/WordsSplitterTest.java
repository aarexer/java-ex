package utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by rexer.
 */
public class WordsSplitterTest extends Assert{
	
	private static final String testString = "Hello World Lovely World Here I am Yeah";
	
	@Test
	public void testFirstPartOfString() {
		assertEquals(WordsSplitter.getStringForRegister(testString, 20), "Hello World Lovely");
	}

	@Test
	public void testSecondPartOfString() {
		assertEquals(WordsSplitter.getRemainPartOfString(testString, 20).get(0), "World Here I am Yeah");
	}
}
