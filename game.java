import java.util.Scanner;

public class game 
{
	private Scanner scan;
	private player p_1;
	
	public game()
	{
		p_1 = new player("Player 1");

	}
	
	public void play() 
	{
		while(p_1.getScore() < 500)
		{
			p_1.takeTurn();
		}
		System.out.println(p_1.getName() + " Wins!");
	}
	
	
}
