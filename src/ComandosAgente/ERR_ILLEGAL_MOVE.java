
package ComandosAgente;

import java.util.List;

public class ERR_ILLEGAL_MOVE{
   	private ArgumentsErrIllegalMove arguments;
   	private String command;

 	public ArgumentsErrIllegalMove getArguments(){
		return this.arguments;
	}
	public void setArguments(ArgumentsErrIllegalMove arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
