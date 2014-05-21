
package ComandosAgente;

import java.util.List;

public class PING{
   	private ArgumentsPing arguments;
   	private String command;

 	public ArgumentsPing getArguments(){
		return this.arguments;
	}
	public void setArguments(ArgumentsPing arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
