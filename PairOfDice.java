/*
 * Name: Thomas Loesch
 * Date: 01/24/15
 * Description: A class which consists of 2 ints for the current dice values
 */

public class PairOfDice {
	private int die1; // an int value representing the first die
	private int die2; // an int value representing the second die
	
	public PairOfDice()               { die1 = 1; die2 = 1; }
	public PairOfDice(int x, int y)   { die1 = x; die2 = y; }
	
	public void roll()                { die1 = (int)(Math.random() * 6 + 1);   // Generates a value between 1 and 6 for both die
	                                    die2 = (int)(Math.random() * 6 + 1); }
	
	public boolean isDouble()         { return die1 == die2; }
	
	public int sum()                  { return die1 + die2; }
	
	public int getDie1()              { return die1; }
	public int getDie2()              { return die2; }
	public int getHighest()           { if( die1 > die2) return die1;
							            return die2; }
	
	public void setDie1(int x)        { die1 = x; }
	public void setDie2(int y)        { die2 = y; }
	public void setDice(int x, int y) { die1 = x; die2 = y; }
	
	public String toString()          { return die1 + " " + die2; }
}
