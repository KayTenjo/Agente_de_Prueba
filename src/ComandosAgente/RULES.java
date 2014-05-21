
package ComandosAgente;

import java.util.List;

public class RULES{
    
        private String command;
   	private String boardSize;
   	private String maxRoundTime;
   	private String turnDuration;
        private String roundsPerMatch;
 
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
	public String getMaxRoundTime(){
		return this.maxRoundTime;
	}
	public void setMaxRoundTime(String maxRoundTime){
		this.maxRoundTime = maxRoundTime;
	}
        
        public void setBoardSize(String boardSize){
        
            this.boardSize = boardSize;
        }
        public String getBoardSize(){
        
        return this.boardSize;
        }
 
        public String getTurnDuration(){
		return this.turnDuration;
	}
	public void setTurnDuration(String turnDuration){
		this.turnDuration = turnDuration;
	}
        
        public void setRoundsPerMatch(String roundsPerMatch){
        
            this.roundsPerMatch = roundsPerMatch;
        }
        
        public String getRoundsPerMatch(){
        
            return this.roundsPerMatch;
        }
}
