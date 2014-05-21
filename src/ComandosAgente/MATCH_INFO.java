
package ComandosAgente;

import java.util.List;

public class MATCH_INFO{
   	private ArgumentsMatchInfo arguments;
   	private String command;

 	public ArgumentsMatchInfo getArguments(){
		return this.arguments;
	}
	public void setArguments(ArgumentsMatchInfo arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
