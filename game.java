import java.util.Scanner;

public class game 
{
	private Scanner scan;
	private player p_1;
	private String prompt = "";
	private int value;
	private Scanner input = new Scanner(System.in);
	
	public game()
	{
		p_1 = new player("Player 1");

	}
	
	//Purpose: Allows players to take turns playing the game.
	//Post-Condition: Continue playing until player gets a score of 500 or chooses to end
	public void play() 
	{
		while(p_1.getScore() < 500)
		{
			value = 0;
			System.out.println("Do you wish to keep playing?(Y/N)");
			while(value != 1)
			{
				prompt = input.nextLine();
				if (prompt.equalsIgnoreCase("Y")||prompt.equalsIgnoreCase("N"))
				{
					if (prompt.equalsIgnoreCase("Y"))
					{
						value = 1;
					}
					if (prompt.equalsIgnoreCase("N"))
					{
						System.out.println("The game has ended...");
						System.exit(0);
					}
				}
				else
				{
					System.out.println("Please enter: |Y| if you wish to continue or |N| if you wish to end the game");
				}
			}
			p_1.takeTurn();
		}
		System.out.println(p_1.getName() + " Wins!");
	}
	
	
}