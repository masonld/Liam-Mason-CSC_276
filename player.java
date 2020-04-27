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
	private List<Integer> reroll_List;
	private boolean check;
	
	//Purpose:Class Constructor
	//Post-Conditions: Player object is constructed.
	public player (String name)
	 {
		dice die_1 = new dice(2, 3, 4, 5, 6, 10);
		dice die_2 = new dice(1, 2, 4, 5, 6, 10);
		rollList = new ArrayList<>();
		reroll_List = new ArrayList<>();
		dice_List1 = die_1.die;
		dice_List2 = die_2.die;
		DIE_SIZE = 6;
		p_name = name;
		score = 0;
		dice= new Random();
	 }

	//Purpose:Rolls all dice and updates score accordingly.
	//Post-Conditions:5 numbers are rolled randomly and the score is updated.
	public void takeTurn()
	 {
		int roll1=getRoll(dice_List1, rollList);
		int roll2=getRoll(dice_List1, rollList);
		int roll3=getRoll(dice_List1, rollList);
		int roll4=getRoll(dice_List1, rollList);
		int roll5=getRoll(dice_List2, rollList);
		System.out.println("Roll: "+"["+roll1+", "+roll2+", "+roll3+", "+roll4+", "+roll5+"]");
		updateScore(roll1,roll2,roll3,roll4,roll5);
		check = false;
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

public boolean getcheck()
{
	return check;
}

public int getReroll()
{
	return reroll_List.size();
}
//Purpose: Resets the score and rollList
//Post-Conditions: Score and rollList are reset
public void reset()
{
	score = 0;
	rollList = new ArrayList<>();
	reroll_List = new ArrayList<>();
}

//Purpose: Produces a random number within range of the die.
//Post-Conditions: Said random number is returned.
private int getRoll(List<Integer> die, List<Integer> list)
 {
	int roll = die.get(dice.nextInt(DIE_SIZE));
	list.add(roll);
	return roll;
 }


//Purpose: Updates the score of the player based on the rules.
//Post-Conditions:The score is updated from it's original value.
private void updateScore(int roll1, int roll2,int roll3, int roll4, int roll5)
 {	
	if(checkTrain(roll1))
		score = score + (roll1*100) + this.addRolls(rollList);
	else if(checkRolls(roll1))
		score = score + (roll1*10) + this.addRolls(rollList);
	else if(checkRolls(roll2))
		score = score + (roll2*10) + this.addRolls(rollList);
	else if(checkRolls(roll3))
		score = score + (roll3*10) + this.addRolls(rollList);
	else if(checkRolls(roll4))
		score = score + (roll4*10) + this.addRolls(rollList);
	else if(checkRolls(roll5))
		score = score + (roll5*10) + this.addRolls(rollList);
 }

//Purpose:Checks the occurences of a roll.
//Post-Conditions: Returns true or false.
private boolean checkRolls(int value){
	boolean check = false;
	int occurences = Collections.frequency(rollList, value);
	if(occurences >= 3)
	{
		check = true;
		Collections.replaceAll(rollList, value, 0);
	}
	if(occurences == 2 && dice_List2.contains(1))
	{
		check = true;
		Collections.replaceAll(rollList, value, 0);
	}
	return check;
}

//purpose: Checks the rolls to see if it results in a freight train.
//Post-Conditions: Returns true or false. 
private boolean checkTrain(int value)
{
	boolean check = false;
	int occurences = Collections.frequency(rollList, value);
	if(occurences == 5)
	{
		check = true;
		Collections.replaceAll(rollList,value,0);
	}
	return check;
}

//Purpose:Adds up the rolls
//Post-Conditions: The rolls are added up properly.
private int addRolls(List<Integer> list)
{
	int sum = 0;
	while(!list.isEmpty())
	{
		int roll = list.remove(0);
		if(roll==10 || roll==5)
			sum = sum + roll;
		else if(roll != 0 && list != reroll_List)
			reroll_List.add(roll);
	}
		return sum;
}

public void reRoll(int times)
{
	int rerolls =times;
	reroll_List.clear();
	for(int i = 0; i < rerolls;i++)
	{
		 getRoll(dice_List1,reroll_List);
	}
	System.out.println(p_name + " rerolled: "+reroll_List);
	score = score + addRolls(reroll_List);
	reroll_List.clear();
}


public void resolveFlash(){
	System.out.println("Resolve flash: ");
	if(reroll_List.size()!=0)
		reRoll(reroll_List.size());
	else{
		reRoll(5);
	}

	score = score + addRolls(reroll_List);
	rollList.clear();

}

public boolean flash(int value)
{
	check = false;
	int occurences = Collections.frequency(rollList, value);
	if(occurences >= 3)
	{
		check = true;
		Collections.replaceAll(rollList, value, 0);
	}
	else if(rollList.contains(1) && occurences == 2)
	{
			check = true;
			Collections.replaceAll(rollList, 1, 0);
			Collections.replaceAll(rollList, value, 0);
	}	
	return check;
}
}