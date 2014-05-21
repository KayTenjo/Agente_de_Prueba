
package ComandosAgente;

import java.util.List;

public class TURN_QUERY{
   	private ArgumentsTurnQuery arguments;
   	private String command;

 	public ArgumentsTurnQuery getArguments(){
		return this.arguments;
	}
	public void setArguments(ArgumentsTurnQuery arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
