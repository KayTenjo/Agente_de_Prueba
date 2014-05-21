
package ComandosAgente;

import java.util.List;

public class ROUND_START_ACK{
   	private ArgumentsRoundStartAck arguments;
   	private String command;

 	public ArgumentsRoundStartAck getArguments(){
		return this.arguments;
	}
	public void setArguments(ArgumentsRoundStartAck arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
