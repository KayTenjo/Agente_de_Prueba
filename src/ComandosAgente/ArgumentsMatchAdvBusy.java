
package ComandosAgente;

import java.util.List;

public class ArgumentsMatchAdvBusy{
   	private String alreadyPlaying;
   	private String rejected;
   	private String waitingOtherAdv;

 	public String getAlreadyPlaying(){
		return this.alreadyPlaying;
	}
	public void setAlreadyPlaying(String alreadyPlaying){
		this.alreadyPlaying = alreadyPlaying;
	}
 	public String getRejected(){
		return this.rejected;
	}
	public void setRejected(String rejected){
		this.rejected = rejected;
	}
 	public String getWaitingOtherAdv(){
		return this.waitingOtherAdv;
	}
	public void setWaitingOtherAdv(String waitingOtherAdv){
		this.waitingOtherAdv = waitingOtherAdv;
	}
}
