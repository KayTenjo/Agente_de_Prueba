
package ComandosAgente;

import java.util.List;

public class SESSION_START{
   	private ArgumentsSessionStart arguments;
   	private String command;

 	public ArgumentsSessionStart getArguments(){
		return this.arguments;
	}
	public void setArguments(ArgumentsSessionStart arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
