
package ComandosAgente;

import java.util.List;

public class TURN_QUERY_RESP{
   	private ArgumentsTurnQueryResp arguments;
   	private String command;

 	public ArgumentsTurnQueryResp getArguments(){
		return this.arguments;
	}
	public void setArguments(ArgumentsTurnQueryResp arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
