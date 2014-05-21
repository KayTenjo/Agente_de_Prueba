
package ComandosAgente;

import java.util.List;

public class MatchEficiencyLimit{
   	private String efficiencyLimit;
   	private String enabled;
   	private String minRounds;

 	public String getEfficiencyLimit(){
		return this.efficiencyLimit;
	}
	public void setEfficiencyLimit(String efficiencyLimit){
		this.efficiencyLimit = efficiencyLimit;
	}
 	public String getEnabled(){
		return this.enabled;
	}
	public void setEnabled(String enabled){
		this.enabled = enabled;
	}
 	public String getMinRounds(){
		return this.minRounds;
	}
	public void setMinRounds(String minRounds){
		this.minRounds = minRounds;
	}
}
