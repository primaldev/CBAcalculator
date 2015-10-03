package cba.primaldev.org;

public class PlayersScore {
	
	public double hadicap;
	public int score;
	public int playerNum;
	private int handicapCat;
	private String plusHandiCap;
		
	public PlayersScore() {
		super();
	}


	public PlayersScore(double hadicap, int score,int playerNum) {
		super();
		this.hadicap = hadicap;
		this.score = score;
		this.playerNum = playerNum;
	}
	
	
	public double getHadicap() {
		return hadicap;
	}
	public void setHadicap(double hadicap) {
		this.hadicap = hadicap;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	
	public int getPlayerNum() {
		return playerNum;
	}


	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}

		

	public int getHandicapCat() {
		return handicapCat;
	}


	public void setHandicapCat(int handicapCat) {
		this.handicapCat = handicapCat;
	}



	@Override
    public String toString() {
		if (this.hadicap < 0) {
			this.plusHandiCap = "+";
			this.hadicap = -this.hadicap;
		}else{
			this.plusHandiCap = "";
		}
		
		
        return this.playerNum + ": Handicap: " + plusHandiCap + this.hadicap + "  " + "Score: " + this.score;
    }

	
	
	

}
