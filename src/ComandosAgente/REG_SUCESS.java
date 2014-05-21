
package ComandosAgente;

import java.util.List;

public class REG_SUCESS{
   
   	private String command;
   	private String clientName;
   	private String id;
   	private Policies policies;
        
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
        public String getClientName(){
		return this.clientName;
	}
	public void setClientName(String clientName){
		this.clientName = clientName;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public Policies getPolicies(){
		return this.policies;
	}
	public void setPolicies(Policies policies){
		this.policies = policies;
	}
  
}
