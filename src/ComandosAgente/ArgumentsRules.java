
package ComandosAgente;

import java.util.List;

public class ArgumentsRules{
   	private BoardData board;
   	private Game game;
   	private String gameName;
   	private String id;
   	private MatchEficiencyLimit matchEficiencyLimit;
   	private Time time;

 	public BoardData getBoard(){
		return this.board;
	}
	public void setBoard(BoardData board){
		this.board = board;
	}
 	public Game getGame(){
		return this.game;
	}
	public void setGame(Game game){
		this.game = game;
	}
 	public String getGameName(){
		return this.gameName;
	}
	public void setGameName(String gameName){
		this.gameName = gameName;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public MatchEficiencyLimit getMatchEficiencyLimit(){
		return this.matchEficiencyLimit;
	}
	public void setMatchEficiencyLimit(MatchEficiencyLimit matchEficiencyLimit){
		this.matchEficiencyLimit = matchEficiencyLimit;
	}
 	public Time getTime(){
		return this.time;
	}
	public void setTime(Time time){
		this.time = time;
	}
}
