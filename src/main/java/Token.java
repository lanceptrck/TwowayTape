
public enum Token {
	LEFT("left"),
	RIGHT("right"),
	SHARP("#"),
	COMMA(","),
	LPAREN("\\("),
	RPAREN("\\)"),
	BRACKET("]");
	
	private String token;
	
	public String getToken() {
		return this.token;
	}
	
	private Token(String token) {
		this.token = token;
	}
}
