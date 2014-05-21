
package ComandosAgente;

import java.util.List;

public class STATS_QUERY{
   	private ArgumentsStatsQuery arguments;
   	private String command;

 	public ArgumentsStatsQuery getArguments(){
		return this.arguments;
	}
	public void setArguments(ArgumentsStatsQuery arguments){
		this.arguments = arguments;
	}
 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
}
