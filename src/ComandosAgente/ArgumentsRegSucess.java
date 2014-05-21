
package ComandosAgente;

import java.util.List;

public class ArgumentsRegSucess{
   	private String clientName;
   	private String id;
   	private Policies policies;

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
