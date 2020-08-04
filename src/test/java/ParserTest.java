import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ParserTest {

	private AutomataParser ap;
	private List<String> language;

	@Before
	public void setup() {
		language = new ArrayList<String>();
		language.add("1]right(b,1)");
		language.add("1]right(a,2)");
		language.add("1]right(#,3)");
		language.add("2]right(b,2)");
		language.add("2]right(a,1)");
		language.add("2]right(#,4)");
		language.add("3]accept");
		language.add("4]hell");
		ap = new AutomataParser(language);
	}

	@Test
	public void test_single_line_parse() {
		String sample = "1]right(b,1)";
		assertEquals(new State(1, Direction.RIGHT, "b", 1), ap.parse(sample));
	}

	@Test
	public void test_incorrect_syntax() {
		String sample = "1]up(x,2)";

		assertThrows(RuntimeException.class, () -> {
			ap.parse(sample);
		});
	}

	@Test
	public void test_built_in_language() {
		ap.parseAll();
		ap.displayAllStates();
	}
	
	@Test
	public void test_language_does_not_have_end_state() {
		List<String> languageWithNoEndstates = new ArrayList<String>();
		languageWithNoEndstates.add("1]right(b,1)");
		languageWithNoEndstates.add("1]right(a,2)");
		languageWithNoEndstates.add("1]right(#,3)");
		languageWithNoEndstates.add("2]right(b,2)");
		languageWithNoEndstates.add("2]right(a,1)");
		languageWithNoEndstates.add("2]right(#,4)");

		ap.setLanguage(languageWithNoEndstates);
		assertThrows(RuntimeException.class, () -> {
			ap.parseAll();
		});

	}

}
