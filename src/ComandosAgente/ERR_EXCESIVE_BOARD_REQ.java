
package ComandosAgente;

import java.util.List;

public class ERR_EXCESIVE_BOARD_REQ{
   	private ArgumentsErrExcesiveBoardReq arguments;
   	private String command;

 	public ArgumentsErrExcesiveBoardReq getArguments(){
		return this.arguments;
	}
	public void setArguments(ArgumentsErrExcesiveBoardReq arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
