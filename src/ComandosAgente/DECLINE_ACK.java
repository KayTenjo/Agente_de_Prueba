
package ComandosAgente;

import java.util.List;

public class DECLINE_ACK{
   	private ArgumentsDeclineAck arguments;
   	private String command;

 	public ArgumentsDeclineAck getArguments(){
		return this.arguments;
	}
	public void setArguments(ArgumentsDeclineAck arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
