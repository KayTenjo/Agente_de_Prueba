
package ComandosAgente;

import java.util.List;

public class STOP_DISCONNECT{
   	private ArgumentsStopDisconnect arguments;
   	private String command;

 	public ArgumentsStopDisconnect getArguments(){
		return this.arguments;
	}
	public void setArguments(ArgumentsStopDisconnect arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
