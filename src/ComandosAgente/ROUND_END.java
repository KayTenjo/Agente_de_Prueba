
package ComandosAgente;
import java.util.List;

public class ROUND_END{
   
   	private String command;
        private String cause;
        private String xPos;
        private String yPos;
        private String nextGame;
        
 	public String getCommand(){
	return this.command;
	}
	public void setCommand(String command){
	this.command = command;
	}
        
        public void setCause(String cause){
        
        this.cause = cause;
        }
        public String getCause(){
        
        return this.cause;
        }
        public void setxPos(String xPos){
        
        this.xPos = xPos;
        }
        public String getxPos(){
        
        return this.xPos;
        }
        public void set(String yPos){
        
        this.yPos = yPos;
        }
        public String getyPos(){
        
        return this.yPos;
        }
        public void setNextGame(String nextGame){
        
        this.nextGame = nextGame;
        }
        public String getNextGame(){
        
        return this.nextGame;
        }
        
}
