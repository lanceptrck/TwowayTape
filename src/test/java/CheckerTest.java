import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CheckerTest {

	private OmegaChecker returnChecker(String fileName) {
		LanguageReader lr = new LanguageReader(fileName);
		AutomataParser ap = new AutomataParser(lr.getLanguage());
		ap.parseAll();
		ap.displayAllStates();
		return new OmegaChecker(ap.getStates());
	}
	
	public void test_valid_omega_for_problem_1() {
		OmegaChecker oc = returnChecker("language_6.txt");
		assertEquals(false, oc.isOmegaValid("#aaba#"));
		assertEquals(true, oc.isOmegaValid("#bbbaba#"));
		assertEquals(true, oc.isOmegaValid("#babab#"));
		assertEquals(true, oc.isOmegaValid("#bb#"));
	}
	
	@Test
	public void test_valid_omegas_for_problem_2() {
		OmegaChecker oc = returnChecker("language_7.txt");
		oc.isOmegaValid("#aaba#");
		oc.isOmegaValid("#bbbaba#");
		oc.isOmegaValid("#babab#");
	}

}
