import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CheckerTest {

	private AutomataParser ap;
	private OmegaChecker oc;
	private LanguageReader lr;

	@Before
	public void setup() {
		lr = new LanguageReader("language_2.txt");
		ap = new AutomataParser(lr.getLanguage());
		ap.parseAll();
		oc = new OmegaChecker(ap.getStates());
	}

	@Test
	public void test_valid_omega() {
		String omega = "#aaab#";
		oc.setOmega(omega);
		ap.displayAllStates();
		oc.isOmegaValid();
	}

}
