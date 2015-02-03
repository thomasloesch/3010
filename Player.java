/*
 * Name: Thomas Loesch
 * Date: 01/24/15
 * Description: A class to hold the score, name, and remaining desperation rolls for a player
 */

public class Player {
	private String name;
	private int desperationRolls; // represents the number of remaining desperation rolls
	private int score;
	
	public Player(String x, int y)  { name = x; 
		                              desperationRolls = y;
		                              score = 0; }
	
	public String toString()        { return "Player: " + name + "   Score: " + score + "   Desperation rolls remaining: " + desperationRolls; }
	
	public String getName()         { return name; }
	public int getDespRolls()       { return desperationRolls; }
	public int getScore()           { return score; }
	
	public boolean isWinner()       { return score >= 50; }
	
	public void setName(String x)   { name = x; }
	public void setDespRolls(int x) { desperationRolls = x; }
	public void setScore(int x)     { score = x; }
	
	public void incScore(int x)     { score += x; }
	public void halfScore()         { score /= 2; }
	public void decRolls()          { desperationRolls--; }
}
