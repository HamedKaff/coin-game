package view.model;

import java.util.List;
import java.util.ArrayList;

/*
 * Class:           ViewModel
 * Description:     this class takes care of the Win/Loss of players
 * Author:          Hamed Alkaff - s3708483
 */
public class ViewModel {
	

	private List<String> winloss  = new ArrayList<String>();

	
	public void addWinloss(String result) {
		String[] a = result.split(":");
		for (String b: winloss) {
			String[] c = b.split(":"); 
			
			if (a[0].equals(c[0]) ) {
				winloss.remove(b);
				break;
			}
		}
		winloss.add(result);
	}

	public void clearWinloss() {
		winloss = new ArrayList<String>();
	}
	
	public String getWinloss(String playerid) {		
		for (String b: winloss) {
			String[] c = b.split(":");
			
			if (playerid.equals(c[0]) ) {
				return c[1];
			}
		}
		return "";
	}
}
