
package ComandosAgente;

import java.util.List;

public class Game{
   	private String illegalMoveLose;
   	private String maxPasses;
   	private String noConnect3;
   	private String noPass;
   	private List penalizeIllegalMoves;
   	private String roundsPerMatch;
   	private String timeoutLose;
   	private String timeoutsForLose;
   	private String tournament;
   	private String wrongPosLose;

 	public String getIllegalMoveLose(){
		return this.illegalMoveLose;
	}
	public void setIllegalMoveLose(String illegalMoveLose){
		this.illegalMoveLose = illegalMoveLose;
	}
 	public String getMaxPasses(){
		return this.maxPasses;
	}
	public void setMaxPasses(String maxPasses){
		this.maxPasses = maxPasses;
	}
 	public String getNoConnect3(){
		return this.noConnect3;
	}
	public void setNoConnect3(String noConnect3){
		this.noConnect3 = noConnect3;
	}
 	public String getNoPass(){
		return this.noPass;
	}
	public void setNoPass(String noPass){
		this.noPass = noPass;
	}
 	public List getPenalizeIllegalMoves(){
		return this.penalizeIllegalMoves;
	}
	public void setPenalizeIllegalMoves(List penalizeIllegalMoves){
		this.penalizeIllegalMoves = penalizeIllegalMoves;
	}
 	public String getRoundsPerMatch(){
		return this.roundsPerMatch;
	}
	public void setRoundsPerMatch(String roundsPerMatch){
		this.roundsPerMatch = roundsPerMatch;
	}
 	public String getTimeoutLose(){
		return this.timeoutLose;
	}
	public void setTimeoutLose(String timeoutLose){
		this.timeoutLose = timeoutLose;
	}
 	public String getTimeoutsForLose(){
		return this.timeoutsForLose;
	}
	public void setTimeoutsForLose(String timeoutsForLose){
		this.timeoutsForLose = timeoutsForLose;
	}
 	public String getTournament(){
		return this.tournament;
	}
	public void setTournament(String tournament){
		this.tournament = tournament;
	}
 	public String getWrongPosLose(){
		return this.wrongPosLose;
	}
	public void setWrongPosLose(String wrongPosLose){
		this.wrongPosLose = wrongPosLose;
	}
}
