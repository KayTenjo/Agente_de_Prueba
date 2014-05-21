
package ComandosAgente;

import java.util.List;

public class SESSION_QUIT{
   	private ArgumentsSessionQuit arguments;
   	private String command;

 	public ArgumentsSessionQuit getArguments(){
		return this.arguments;
	}
	public void setArguments(ArgumentsSessionQuit arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
