
package ComandosAgente;

import java.util.List;

public class ArgumentsStats{
   	private String clientName;
   	private String gameName;
   	private Number id;
   	private Statistics statistics;

 	public String getClientName(){
		return this.clientName;
	}
	public void setClientName(String clientName){
		this.clientName = clientName;
	}
 	public String getGameName(){
		return this.gameName;
	}
	public void setGameName(String gameName){
		this.gameName = gameName;
	}
 	public Number getId(){
		return this.id;
	}
	public void setId(Number id){
		this.id = id;
	}
 	public Statistics getStatistics(){
		return this.statistics;
	}
	public void setStatistics(Statistics statistics){
		this.statistics = statistics;
	}
}
