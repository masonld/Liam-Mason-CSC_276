
import java.util.ArrayList;
import java.util.List;

public class dice 
{
	
	public List<Integer> die;
	
	//Purpose:Constructor of this class
	//Post-Conditions: A dice object is constructed
	public dice(int S_1, int S_2, int S_3, int S_4, int S_5, int S_6)
	{
		die = new ArrayList<>();
		die = make_Die(die, S_1, S_2,  S_3, S_4, S_5, S_6); 
	}
	
	//Purpose:Creates a List of 6 integer values
	//Post-Conditions: A integer list is returned.
	private List<Integer> make_Die(List<Integer> list,int S_1, int S_2, int S_3, int S_4, int S_5, int S_6)
	{
		list.add(S_1);
		list.add(S_2);
		list.add(S_3);
		list.add(S_4);
		list.add(S_5);
		list.add(S_6);
		return list;
	}
}