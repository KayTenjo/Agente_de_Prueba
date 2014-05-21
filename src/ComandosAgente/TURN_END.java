
package ComandosAgente;

import java.util.List;

public class TURN_END{
   	private ArgumentsTurnEnd arguments;
   	private String command;

 	public ArgumentsTurnEnd getArguments(){
		return this.arguments;
	}
	public void setArguments(ArgumentsTurnEnd arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
