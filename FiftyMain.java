/*
 * Name: Thomas Loesch
 * Date: 01/24/15
 * Description: The main driver for the Fifty game
 */

import java.util.Scanner;

public class FiftyMain {
	
	public static PairOfDice pod ; // One global dice object

	public static boolean gameOn( Player p1, Player p2 ) { return !( p1.isWinner() || p2.isWinner() ); }
	
	public static void takeTurn( Player p ) {
		pod.roll();

		if( pod.sum() == 7 ) p.incScore(7);
		else if( pod.isDouble() ) {
			int dieValue = pod.getDie1();
			
			if      ( dieValue == 1 || dieValue == 6 ) p.incScore(20);
			else if ( dieValue == 3 )                  p.halfScore();
			else                                       p.incScore(10);
		}
		
		System.out.println( "Name: " + p.getName() + "\t\tRoll: " + pod );
	}

	public static void takeDesperationTurn( Player p ) {
		p.decRolls();
		
		pod.roll();
		int sum = pod.sum();
		
		if( sum == 7 || sum == 11 ) p.incScore(25); 
		else if( pod.isDouble() )   p.setScore(0);
		else                        p.incScore( -1 * pod.getHighest() );
		
		System.out.println( "Name: " + p.getName() + "\tDesperation Roll: " + pod );
	}
	
	public static void printFinalResult( Player p1, Player p2 ) {
	// print result: winner (or tie), name of winner(s) and score
		
		System.out.println( "===================== RESULTS =====================" );
		
		if( p1.isWinner() ){
			if( p2.isWinner() ){
				if( p1.getScore() == p2.getScore() )     System.out.println( "There has been a tie!" );
				else if( p1.getScore() > p2.getScore() ) System.out.println( p1.getName() + " is the winner!" );
				else                                     System.out.println( p2.getName() + " is the winner!" );
			}
			else                                         System.out.println( p1.getName() + " is the winner!" );
		}
		else if( p2.isWinner() )                         System.out.println( p2.getName() + " is the winner!" );
		
		System.out.println();
		System.out.println( p1 );
		System.out.println( p2 );
	}
	
	public static void main( String[] args ) {
		Scanner in = new Scanner( System.in );
		
		Player player1, player2;
		player1 = new Player( "", 2 );
		player2 = new Player( "", 2 );
			
		pod = new PairOfDice();
		
		System.out.print( "Enter name of player 1: " );
		player1.setName( in.nextLine() );
		System.out.print( "Enter name of player 2: " );
		player2.setName( in.nextLine() );

		System.out.println( "\n=========== Staring Game ===========" );
		
		int round = 0;
		
		while( gameOn( player1, player2 ) ) {
			System.out.println( "\nRound " + ++round + ":" );
			
			takeTurn( player1 );
			takeTurn( player2 );
			
			if( player1.getDespRolls() > 0 || player2.getDespRolls() > 0 ){
				System.out.println();
				outputScores( player1, player2 );
			
				if( player1.getDespRolls() > 0 ) askDesperation( player1, in );
				if( player2.getDespRolls() > 0 ) askDesperation( player2, in );
			}
			
			System.out.println();
			
			outputScores( player1, player2 );
		}
		
		printFinalResult( player1, player2 );
		
		in.close();
	}

	public static void outputScores( Player player1, Player player2 ) {
		System.out.print( "Player: " + player1.getName() + "  Score: " + player1.getScore() + "  " );
		System.out.print( "Player: " + player2.getName() + "  Score: " + player2.getScore() + "\n" );
	}
	
	public static void askDesperation( Player p, Scanner in ){
		System.out.print( p.getName() + ", you have " + p.getDespRolls() + " desperation roll(s) left. Are you desperate? (y/n) ");
		if( in.nextLine().startsWith("y") ) takeDesperationTurn(p);
	}

}
