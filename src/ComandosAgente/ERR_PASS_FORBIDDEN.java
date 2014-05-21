
package ComandosAgente;

import java.util.List;

public class ERR_PASS_FORBIDDEN{
   	private ArgumentsErrPassForbidden arguments;
   	private String command;

 	public ArgumentsErrPassForbidden getArguments(){
		return this.arguments;
	}
	public void setArguments(ArgumentsErrPassForbidden arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
