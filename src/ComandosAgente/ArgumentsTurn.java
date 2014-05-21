
package ComandosAgente;

import java.util.List;

public class ArgumentsTurn{
   	private AdvMove advMove;
   	private String remainingRoundTime;

 	public AdvMove getAdvMove(){
		return this.advMove;
	}
	public void setAdvMove(AdvMove advMove){
		this.advMove = advMove;
	}
 	public String getRemainingRoundTime(){
		return this.remainingRoundTime;
	}
	public void setRemainingRoundTime(String remainingRoundTime){
		this.remainingRoundTime = remainingRoundTime;
	}
}
