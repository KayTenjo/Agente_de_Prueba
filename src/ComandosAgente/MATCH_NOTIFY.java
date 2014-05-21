
package ComandosAgente;

import java.util.List;

public class MATCH_NOTIFY{

   	private String command;
   	private String advId;
   	private String advName;
        private String matchName;
   	private String id;


 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
        
        public String getAdvId(){
		return this.advId;
	}
	public void setAdvId(String advId){
		this.advId = advId;
	}
 	public String getAdvName(){
		return this.advName;
	}
	public void setAdvName(String advName){
		this.advName = advName;
	}
        public void setMatchName(String matchName){
                this.matchName = matchName;
        }
        public String getMatchName(){
                return this.matchName;
        }
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
}
