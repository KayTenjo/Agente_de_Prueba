
package ComandosAgente;

import java.util.List;

public class BOARD{
   	private ArgumentsBoard arguments;
   	private String command;

 	public ArgumentsBoard getArgumentsBoard(){
		return this.arguments;
	}
	public void setArgumentsBoard(ArgumentsBoard arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
