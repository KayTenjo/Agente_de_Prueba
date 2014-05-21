
package ComandosAgente;



public class ACCEPT_ACK{
   	private ArgumentsAcceptAck arguments;
   	private String command;

 	public ArgumentsAcceptAck getArgumentsAcceptAck(){
		return this.arguments;
	}
	public void setArguments(ArgumentsAcceptAck arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
