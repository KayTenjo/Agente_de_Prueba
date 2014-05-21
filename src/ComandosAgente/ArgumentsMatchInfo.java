
package ComandosAgente;

import java.util.List;

public class ArgumentsMatchInfo{
   	private List awaitingPlayers;
   	private List currentMatches;

 	public List getAwaitingPlayers(){
		return this.awaitingPlayers;
	}
	public void setAwaitingPlayers(List awaitingPlayers){
		this.awaitingPlayers = awaitingPlayers;
	}
 	public List getCurrentMatches(){
		return this.currentMatches;
	}
	public void setCurrentMatches(List currentMatches){
		this.currentMatches = currentMatches;
	}
}
