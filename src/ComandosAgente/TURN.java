
package ComandosAgente;

import java.util.List;

public class TURN{

   	private String command;
   	private AdvMove advMove;
   	private String remainingRoundTime;

 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
        
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
