
package ComandosAgente;

import java.util.List;

public class ERR_UNKNOWN_COMMAND{
   	private ArgumentsErrUnknownCommand arguments;
   	private String command;

 	public ArgumentsErrUnknownCommand getArguments(){
		return this.arguments;
	}
	public void setArguments(ArgumentsErrUnknownCommand arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
