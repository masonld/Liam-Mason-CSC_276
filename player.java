import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class player 
{
	private final int DIE_SIZE;
	private int score;
	private Random dice;
	private String p_name;
	private List<Integer> dice_List1;
	private List<Integer> dice_List2;
	private List<Integer> rollList;
	
	//Purpose:Class Constructor
	//Post-Conditions: Player object is constructed.
	public player (String name)
	 {
		DIE_SIZE = 6;
		dice die_1 = new dice(1, 2, 4, 5, 6, 10);
		dice die_2 = new dice(2, 3, 4, 5, 6, 10);
		dice_List1 = die_1.die;
		dice_List2 = die_2.die;
		rollList = new ArrayList<>();
		p_name = name;
		score = 0;
		dice= new Random();
	 }

	//Purpose:Rolls all dice and updates score accordingly.
	//Post-Conditions:5 numbers are rolled randomly and the score is updated.
	public void takeTurn()
	 {
	
		int roll1=getRoll(dice_List1);
		int roll2=getRoll(dice_List1);
		int roll3=getRoll(dice_List1);
		int roll4=getRoll(dice_List1);
		int roll5=getRoll(dice_List2);
		System.out.println("Roll: "+"["+roll1+", "+roll2+", "+roll3+", "+roll4+", "+roll5+"]");
		updateScore(roll1,roll2,roll3,roll4,roll5);
		
	 }

	//Purpose:Returns the player's name.
	//Post-Conditions: Player's name is returned.
	public String getName()
	{
	  return p_name;
	}
	
	//Purpose:Displays the players name and score.
	//Post-Conditions:The player and score is returned.
	public String toString()
	{	
		return "The score for " + p_name + " is:" + score;
	}
	
	//Purpose:Returns the player's score.
	//Post-Conditions: Player's score is returned.
	public int getScore()
	 {
		return score;
	 }

	//Purpose: Produces a random number within range of the die.
	//Post-Conditions: Said random number is returned.
	private int getRoll(List<Integer> die)
	 {
		int roll = die.get(dice.nextInt(DIE_SIZE));
		rollList.add(roll);
		return roll;
	 }


	//Purpose: Updates the score of the player based on the rules.
	//Post-Conditions:The score is updated from it's original value.
	private void updateScore(int roll1, int roll2,int roll3, int roll4, int roll5)
	 {		
		if(checkRolls(roll1))
			score = score + (roll1*10)+ this.addRolls();
		else if(checkRolls(roll2))
			score = score + (roll2*10)+ this.addRolls();
		else if(checkRolls(roll3))
			score = score + (roll3*10)+ this.addRolls();
		else if(checkRolls(roll4))
			score = score + (roll4*10)+ this.addRolls();
		else if(checkRolls(roll5))
			score = score + (roll5*10)+ this.addRolls();
		else
			score = score + this.addRolls();
	 }
	
	//Purpose:Checks the occurences of a roll.
	//Post-Conditions: Returns true or false.
	private boolean checkRolls(int value){
		boolean flash = false;
		int occurences = Collections.frequency(rollList, value);
		if(occurences >= 3){
			flash = true;
			Collections.replaceAll(rollList, value, 0);
		}
		return flash;
	}
	
	//Purpose:Adds up the rolls
	//Post-Conditions: The rolls are added up properly.
	private int addRolls(){
		int sum = 0;
		while(!rollList.isEmpty())
		{
			sum = sum + rollList.remove(0);
		}
		return sum;
	}
}
