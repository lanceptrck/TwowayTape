
public enum Direction {
	LEFT("left"), RIGHT("right"), HELL("hell"), ACCEPT("accept");

	private String direction;

	public String getDirection() {
		return this.direction;
	}

	private Direction(String direction) {
		this.direction = direction;
	}

	public static boolean isSpecialDirection(String test) {
		if (test.equals(Direction.HELL.getDirection()) || test.equals(Direction.ACCEPT.getDirection()))
			return true;
		else
			return false;
	}

}
