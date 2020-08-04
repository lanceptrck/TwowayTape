import java.util.Set;

public class State {

	private Integer myState;
	private Direction direction;
	private String token;
	private Integer goToState;
	private boolean isEndState;

	public State(Integer myState, Direction direction, String token, Integer goToState) {
		super();
		this.myState = myState;
		this.direction = direction;
		this.token = token;
		this.goToState = goToState;
		this.isEndState = false;
	}

	public State(Integer myState, Direction direction) {
		super();
		this.myState = myState;
		this.direction = direction;
		this.isEndState = true;
	}

	public Integer getMyState() {
		return myState;
	}

	public Direction getDirection() {
		return direction;
	}

	public String getToken() {
		return token;
	}

	public Integer getGoToState() {
		return goToState;
	}

	public boolean isEndState() {
		return isEndState;
	}

	@Override
	public String toString() {
		return "States [myState=" + myState + ", direction=" + direction + ", token=" + token + ", goToState="
				+ goToState + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((goToState == null) ? 0 : goToState.hashCode());
		result = prime * result + ((myState == null) ? 0 : myState.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (direction != other.direction)
			return false;
		if (goToState == null) {
			if (other.goToState != null)
				return false;
		} else if (!goToState.equals(other.goToState))
			return false;
		if (myState == null) {
			if (other.myState != null)
				return false;
		} else if (!myState.equals(other.myState))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}

}
