import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CheckerTest {

	private AutomataParser ap;
	private List<String> language;
	private OmegaChecker oc;

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
		ap.parseAll();
		oc = new OmegaChecker(ap.getStates());
	}

	@Test
	public void test_valid_omega() {
		String omega = "#aab#";
		oc.setOmega(omega);
		ap.displayAllStates();
		oc.isOmegaValid();
	}

}
