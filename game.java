import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.util.Scanner;

public class game 
{
	private Scanner scan = new Scanner(System.in);
	private String name;
	private player p_1;
	private player p_2;
	private String prompt = "";
	private String r_prompt = "";
	private String o_prompt = "";
	private int value;
	private int r_value;
	private int o_value;
	private int game_set;
	private Scanner input = new Scanner(System.in);
	private Scanner input2 = new Scanner(System.in);
	
	public game()
	{
		System.out.println("Please input your name.");
		name = scan.next(); 
		p_1 = new player(name);
		p_2 = new player("Liam");
	}
	
	//Purpose: Allows players to take turns playing the game.
	//Post-Condition: Continue playing until player gets a score of 500 or chooses to end
	public void play() 
	{
		p_1.reset();
		p_2.reset();
		game_set = 0;
		while(game_set != 1)
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
						r_value = 0;
						System.out.println("Do you wish to rest?(Y/N)");
						while(r_value != 1)
						{
							r_prompt = input2.nextLine();
							if (r_prompt.equalsIgnoreCase("Y")||r_prompt.equalsIgnoreCase("N"))
							{
								if (r_prompt.equalsIgnoreCase("Y"))
								{
									p_1.reset();
									p_2.reset();
									r_value = 1;
									value = 1;
								}
								if (r_prompt.equalsIgnoreCase("N"))
								{
									System.out.println("The game has ended...");
									System.exit(0);
								}
							}
							else
							{
								System.out.println("Please enter: |Y| if you wish to reset or |N| if you wish to end the game");
							}
						}
					}
				}
				else
				{
					System.out.println("Please enter: |Y| if you wish to continue or |N| if you wish to end the game");
				}
			}
			p_1.takeTurn();
			if (p_1.getScore() < 35)
			{
				System.out.println(p_1.getName() + " must reroll.");
				p_1.takeTurn();
			}
			if (p_1.getReroll() == 0)
			{
				System.out.println(p_1.getName() + " must reroll.");
				p_1.takeTurn();
			}
			System.out.println("Do you wish to reroll any non scoring cubes?(Y/N)");
			o_value = 0;
			while (o_value != 1)
			{
				o_prompt = input.nextLine();
				if (prompt.equalsIgnoreCase("Y")||prompt.equalsIgnoreCase("N"))
				{
					if (prompt.equalsIgnoreCase("N"))
					{
						o_value = 1;										
					}
					if (prompt.equalsIgnoreCase("Y"))
					{
						p_1.reRoll(p_1.getReroll());
						o_value = 1;
					}
				}
				else
				{
					System.out.println("Please enter: |Y| or |N|");
				}
			}
			p_2.takeTurn();
			if (p_2.getScore() < 35)
			{
				System.out.println(p_2.getName() + " must reroll.");
				p_2.takeTurn();
			}
			if (p_2.getReroll() == 0)
			{
				System.out.println(p_2.getName() + " must reroll.");
				p_2.takeTurn();
			}
			if(p_1.getScore() >= 500)
				game_set = 1;
			if(p_2.getScore() >= 500)
				game_set = 1;
		}
		if(p_1.getScore() >= 500)
		{
			System.out.println(p_1.getName() + " Wins!");
			System.exit(0);
		}
		if(p_2.getScore() >= 500)
		{
			System.out.println(p_2.getName() + " Wins!");
			System.exit(0);
		}
	
}

}
