
package ComandosAgente;

import java.util.List;

public class ArgumentsTurnQueryResp{
   	private String activePlayerId;
   	private String remainingTurnTime;

 	public String getActivePlayerId(){
		return this.activePlayerId;
	}
	public void setActivePlayerId(String activePlayerId){
		this.activePlayerId = activePlayerId;
	}
 	public String getRemainingTurnTime(){
		return this.remainingTurnTime;
	}
	public void setRemainingTurnTime(String remainingTurnTime){
		this.remainingTurnTime = remainingTurnTime;
	}
}
