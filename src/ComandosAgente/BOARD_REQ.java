
package ComandosAgente;

import java.util.List;

public class BOARD_REQ{
   	private ArgumentsBoardReq arguments;
   	private String command;

 	public ArgumentsBoardReq getArgumentsBoardReq(){
		return this.arguments;
	}
	public void setArgumentsBoardReq(ArgumentsBoardReq arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
