
package ComandosAgente;

import java.util.List;

public class TURN{

   	private String command;
        private String yourTurn;
        private String move;
        private String xPos;
        private String yPos;
   	
   	

 	public String getCommand(){
		return this.command;
	}
	public void setCommand(String command){
		this.command = command;
	}
        public void setYourTurn(String yourTurn){
            
            this.yourTurn = yourTurn;
        }
        public String getYourTurn(){
        
            return this.yourTurn;
        
        }
        public void setxPos(String x){
        
        this.xPos=x;
        
        }
        public void setMove(String move){
        
            this.move = move;
        }
        
        public String getMove(){
        
            return this.move;
        }
        
        public String getxPos(){
        
        return this.xPos;
        
        }
        public void setyPos(String yPos){
        
            this.yPos = yPos;
        }
        public String getyPos(){
        
            return this.yPos;
        }
        
}
