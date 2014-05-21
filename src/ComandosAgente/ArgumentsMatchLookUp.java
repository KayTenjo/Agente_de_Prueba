
package ComandosAgente;

import java.util.List;

public class ArgumentsMatchLookUp{
   	private String advName;
   	private String id;
   	private String matchName;
   	private String timeout;

 	public String getAdvName(){
		return this.advName;
	}
	public void setAdvName(String advName){
		this.advName = advName;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public String getMatchName(){
		return this.matchName;
	}
	public void setMatchName(String matchName){
		this.matchName = matchName;
	}
 	public String getTimeout(){
		return this.timeout;
	}
	public void setTimeout(String timeout){
		this.timeout = timeout;
	}
}
