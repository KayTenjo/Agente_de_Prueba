
package ComandosAgente;

import java.util.List;

public class STATS{
   	private ArgumentsStats arguments;
   	private String command;

 	public ArgumentsStats getArguments(){
		return this.arguments;
	}
	public void setArguments(ArgumentsStats arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
