
package ComandosAgente;

import java.util.List;

public class ArgumentsClock{
   	private String matchElapsedTime;
   	private String remainingMatchTime;
   	private String remainingRoundTime;
   	private String remainingTurnTime;
   	private String roundElapsedTime;
   	private String timestamp;

 	public String getMatchElapsedTime(){
		return this.matchElapsedTime;
	}
	public void setMatchElapsedTime(String matchElapsedTime){
		this.matchElapsedTime = matchElapsedTime;
	}
 	public String getRemainingMatchTime(){
		return this.remainingMatchTime;
	}
	public void setRemainingMatchTime(String remainingMatchTime){
		this.remainingMatchTime = remainingMatchTime;
	}
 	public String getRemainingRoundTime(){
		return this.remainingRoundTime;
	}
	public void setRemainingRoundTime(String remainingRoundTime){
		this.remainingRoundTime = remainingRoundTime;
	}
 	public String getRemainingTurnTime(){
		return this.remainingTurnTime;
	}
	public void setRemainingTurnTime(String remainingTurnTime){
		this.remainingTurnTime = remainingTurnTime;
	}
 	public String getRoundElapsedTime(){
		return this.roundElapsedTime;
	}
	public void setRoundElapsedTime(String roundElapsedTime){
		this.roundElapsedTime = roundElapsedTime;
	}
 	public String getTimestamp(){
		return this.timestamp;
	}
	public void setTimestamp(String timestamp){
		this.timestamp = timestamp;
	}
}
