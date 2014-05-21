
package ComandosAgente;

import java.util.List;

public class CLOCK_REQ{
   	private ArgumentsClockReq arguments;
   	private String command;

 	public ArgumentsClockReq getArguments(){
		return this.arguments;
	}
	public void setArguments(ArgumentsClockReq arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
