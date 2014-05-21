
package ComandosAgente;

import java.util.List;

public class ArgumentsRegister{
   	private String clientName;
   	private String clientPass;
   	private String clientType;

 	public String getClientName(){
		return this.clientName;
	}
	public void setClientName(String clientName){
		this.clientName = clientName;
	}
 	public String getClientPass(){
		return this.clientPass;
	}
	public void setClientPass(String clientPass){
		this.clientPass = clientPass;
	}
 	public String getClientType(){
		return this.clientType;
	}
	public void setClientType(String clientType){
		this.clientType = clientType;
	}
}
