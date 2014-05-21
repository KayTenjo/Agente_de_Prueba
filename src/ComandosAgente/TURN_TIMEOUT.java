
package ComandosAgente;

import java.util.List;

public class TURN_TIMEOUT{
   	private ArgumentsTurnTimeOut arguments;
   	private String command;

 	public ArgumentsTurnTimeOut getArguments(){
		return this.arguments;
	}
	public void setArguments(ArgumentsTurnTimeOut arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
