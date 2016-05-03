package utils;
import java.util.ArrayList;
import java.util.List;

public class WordsSplitter {

	public static List<String> getStringsFromTextByLimit(String text, int limit) {
		final List<String> strings = new ArrayList<String>();

		do {
			if (text.length() > limit) {
				final int endOfLineIndex = text.lastIndexOf(" ", limit);

				if (endOfLineIndex == -1) {
					strings.add(text.substring(0, limit));
					text = text.substring(limit);
				} else {
					strings.add(text.substring(0, endOfLineIndex));
					text = text.substring(endOfLineIndex + 1);
				}
			} else {
				strings.add(text);
				break;
			}
		} while (true);

		return strings;
	}

	public static List<String> getRemainPartOfString(String text, int limit) {
		final List<String> strings = getStringsFromTextByLimit(text, limit);
		int size = strings.size();
		return strings.subList(1, size);
	}

	public static String getStringForRegister(String text, int limit)
	{
		return getStringsFromTextByLimit(text, limit).get(0);
	}
}