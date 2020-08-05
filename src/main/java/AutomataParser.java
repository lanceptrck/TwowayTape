import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class AutomataParser {

	private List<String> language;
	private List<State> states;

	public AutomataParser(List<String> language) {
		this.language = new ArrayList<String>(language);
		this.states = new ArrayList<>();
	}

	public List<String> getLanguage() {
		return language;
	}

	public void setLanguage(List<String> language) {
		this.language = language;
	}

	private boolean isDirectionValid(String dir) {
		if (dir.equals(Direction.LEFT.getDirection()) || dir.contentEquals(Direction.RIGHT.getDirection()))
			return true;
		else
			return false;
	}

	private boolean isTokenValid(String token) {

		if (token.contentEquals("#"))
			return true;

		String regex = "^[a-zA-Z0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(token).matches();

	}

	public void parseAll() {
		for (String s : language) {
			parse(s);
		}

		if (!containsEndstates()) {
			throw new RuntimeException(
					"The language provided does not contain endstates or it does not end in hell or an accept state");
		}

	}

	public State parse(String s) {
		String right_string;

		// For Square Bracket
		String arr[] = s.split(Token.BRACKET.getToken());
		String stateNum = arr[0];
		right_string = arr[1];

		if (!StringUtils.isNumeric(stateNum)) {
			throw new RuntimeException("Improper syntax, states should always be numbers");
		}

		if (Direction.isSpecialDirection(right_string)) {
			State st = new State(Integer.valueOf(stateNum), Direction.valueOf(right_string.toUpperCase()));
			states.add(st);
			return st;
		}

		// For Left Paren
		arr = right_string.split(Token.LPAREN.getToken());
		String direction = arr[0];
		right_string = arr[1];

		if (!isDirectionValid(direction))
			throw new RuntimeException("Direction should only be left or right.");

		// For Token
		arr = right_string.split(Token.COMMA.getToken());
		String token = arr[0];
		right_string = arr[1];

		if (!isTokenValid(token))
			throw new RuntimeException("Token should be alpha numeric.");

		// Go-to state
		arr = right_string.split(Token.RPAREN.getToken());
		String gotoState = arr[0];

		if (!StringUtils.isNumeric(stateNum)) {
			throw new RuntimeException("Improper syntax, states should always be numbers");
		}

		State st = new State(Integer.valueOf(stateNum), Direction.valueOf(direction.toUpperCase()), token,
				Integer.valueOf(gotoState));

		states.add(st);

		return st;

	}

	public void displayAllStates() {
		System.out.println("Displaying all states");
		for (State st : states) {
			System.out.println(st);
		}
	}

	public List<State> getStates() {
		return new ArrayList<State>(states);
	}

	public boolean containsEndstates() {

		boolean containsHell = false;
		boolean containsAccept = false;
		boolean doesLanguageAccept = false;
		boolean doesLanguageHell = false;

		List<Integer> hellStateNums = new ArrayList<>();
		List<Integer> acceptStateNums = new ArrayList<>();
		Set<Integer> gotoStates = new HashSet<>();

		for (State st : states) {

			// Get states that goes to another state given an input
			if (st.getGoToState() != null) {
				gotoStates.add(st.getGoToState());
			}

			// Get the accepting states
			if (st.getDirection().equals(Direction.ACCEPT)) {
				containsAccept = true;
				acceptStateNums.add(st.getMyState());
			}

			// Get the hell states
			if (st.getDirection().equals(Direction.HELL)) {
				containsHell = true;
				hellStateNums.add(st.getMyState());
			}

		}

		for (Integer i : acceptStateNums) {
			if (gotoStates.contains(i))
				doesLanguageAccept = true;
		}

		for (Integer i : hellStateNums) {
			if (gotoStates.contains(i))
				doesLanguageHell = true;
		}

		return containsHell && containsAccept && doesLanguageAccept && doesLanguageHell;
	}

	public boolean doesLanguageEnd() {
		return false;
	}

}
