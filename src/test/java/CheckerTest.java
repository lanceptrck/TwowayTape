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

	@Test
	public void test_valid_omega() {
		OmegaChecker oc = returnChecker("language_5.txt");
		assertEquals(true, oc.isOmegaValid("#aababb#"));
		assertEquals(false, oc.isOmegaValid("#ababb#"));

	}

	@Test
	public void test_language_1_all_num_of_a_must_be_even() {
		OmegaChecker oc = returnChecker("language_1.txt");
		assertEquals(true, oc.isOmegaValid("#aaaa#"));
		assertEquals(false, oc.isOmegaValid("#abbb#"));
	}

}
