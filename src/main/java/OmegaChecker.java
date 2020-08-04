import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OmegaChecker {

	private List<State> states;
	private String omega;
	private Set<String> validTokens;
	private List<Integer> endStateNumbers;

	public OmegaChecker(List<State> states, String omega) {
		super();
		this.states = states;
		this.omega = omega;
		this.validTokens = new HashSet<>();
		retrieveValidTokens();
	}

	public OmegaChecker(List<State> states) {
		super();
		this.states = states;
		this.validTokens = new HashSet<>();
		retrieveValidTokens();
	}

	public void setOmega(String omega) {
		this.omega = omega;
	}

	private void retrieveValidTokens() {
		for (State st : states) {
			validTokens.add(st.getToken());
		}
	}

	private List<Integer> getEndStates() {
		if (endStateNumbers == null) {
			endStateNumbers = new ArrayList<>();
			for (State st : states) {
				if (st.getDirection() == Direction.HELL || st.getDirection() == Direction.ACCEPT) {
					endStateNumbers.add(st.getMyState());
				}
			}
		}

		return endStateNumbers;
	}

	public boolean isOmegaValid() {
		char[] tape = omega.toCharArray();
		int myState = 1;
		int gotoState = 1;
		int position = 0;
		Direction dir = Direction.RIGHT;

		while (position <= omega.length() && !dir.equals(Direction.HELL) && !dir.equals(Direction.ACCEPT)) {
			;
			if (position == 0) {
				if (tape[position] != '#') {
					return false;
				} else
					System.out.println("Token is " + tape[position] + ", Start of tape");
			} else {
				char curr = tape[position];
				State st = getGotoStateByInputToken(myState, curr + "");
				dir = st.getDirection();
				System.out.println(dir);
				myState = st.getMyState();

				if (!dir.equals(Direction.HELL) && !dir.equals(Direction.ACCEPT))
					gotoState = st.getGoToState();
			}

			if (dir.equals(Direction.RIGHT) || dir.equals(Direction.HELL) || dir.equals(Direction.ACCEPT))
				position++;
			else
				position--;

		}

		if (dir.equals(Direction.ACCEPT)) {
			System.out.println("The omega: " + omega + " is valid for this language.");
			return true;
		}

		else {
			System.out.println("The omega: " + omega + " is invalid for this language.");
			return false;
		}

	}

	public State getGotoStateByInputToken(int myState, String token) {
		for (State st : states) {
			if (st.getMyState() == myState && st.getToken().equals(token)) {
				System.out.println(
						"At state " + myState + ", Token is " + token + ", Will go to state " + st.getGoToState());

				if (getEndStates().contains(st.getGoToState())) {
					System.out.println("This one is an endstate.");
					return getEndstateByStateNumber(st.getGoToState());
				}

				return getStateByTokenAndCurrentState(st.getGoToState(), token);
			}
		}
		return null;
	}

	public State getStateByTokenAndCurrentState(int stateNum, String token) {
		for (State st : states) {

			if (st.getMyState() == stateNum && st.getToken().equals(token)) {
				return st;
			}
		}

		return null;
	}

	public List<State> getStates() {
		return states;
	}

	private State getEndstateByStateNumber(Integer stateNum) {
		for (State st : states) {

			if (st.getMyState() == stateNum) {
				return st;
			}
		}

		return null;
	}

	public String getOmega() {
		return omega;
	}

	public Set<String> getValidTokens() {
		return validTokens;
	}

}
