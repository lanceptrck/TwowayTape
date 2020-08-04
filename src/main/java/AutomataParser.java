import java.util.ArrayList;
import java.util.List;
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
			throw new RuntimeException("The language provided does not contain endstates");
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

		for (State st : states) {
			if (st.getDirection().equals(Direction.ACCEPT))
				containsAccept = true;

			if (st.getDirection().equals(Direction.HELL))
				containsHell = true;
		}

		return containsHell && containsAccept;
	}

}
