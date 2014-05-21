
package ComandosAgente;

import java.util.List;

public class MATCH_REQ_INFO{
   	private ArgumentsMatchReqInfo arguments;
   	private String command;

 	public ArgumentsMatchReqInfo getArguments(){
		return this.arguments;
	}
	public void setArguments(ArgumentsMatchReqInfo arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
