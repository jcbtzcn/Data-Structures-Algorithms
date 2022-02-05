/*
 * This class represents the configuration and the score of it.
 */
public class Record {
	private String config;
	private int score;
	//Constructor for the class.
	//Takes two parameters which indicate the config and score.
	public Record(String config, int score) {
		this.config = config;
		this.score = score;
	}
	//Getter method for configuration.
	public String getConfig() {
		return config;
	}
	//Getter method for score.
	public int getScore() {
		return score;
	}
}