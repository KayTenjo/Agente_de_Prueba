
package ComandosAgente;

import java.util.List;

public class ROUND_START{
   	
   	private String command;
   	private String advColor;
   	private String color;
   	private String firstMove;

 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
        
        public String getAdvColor(){
		return this.advColor;
	}
	public void setAdvColor(String advColor){
		this.advColor = advColor;
	}
 	public String getColor(){
		return this.color;
	}
	public void setColor(String color){
		this.color = color;
	}
 	public String getFirstMove(){
		return this.firstMove;
	}
	public void setFirstMove(String firstMove){
		this.firstMove = firstMove;
	}

}
