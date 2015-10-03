package cba.primaldev.org;

import java.util.ArrayList;

public class CalculateCba  {
	ArrayList<PlayersScore> playerScoreList;

    
    public static final double P1 = 0.43;
    public static final double P2 = 0.35;
    public static final double P3 = 0.35;
    public static final double P4 = 0.41;
    
    public static final double F4neg = -4.36;
    public static final double F3neg = -3.56;
    public static final double F2neg = -2.76;
    public static final double F1neg = -1.96;
    public static final double F1pos = -3.5;
    
    public static final int A4neg = 1;
    public static final int A3neg = 1;
    public static final int A2neg = 1;
    public static final int A1neg = 1;
    public static final int A1pos = 0;
    

	public CalculateCba(ArrayList<PlayersScore> playscore) {
		super();
		this.playerScoreList = playscore;
	}
	
	public CalculateCba() {
		super();		
		this.playerScoreList = new ArrayList<PlayersScore>();
	}
	
	
	private int calcCBA(){
		
	    int n1=0;
	    int n2=0;
	    int n3=0;
	    int n4=0;
	    int nc=0;
	    
	    int nbz1=0;
	    int nbz2=0;
	    int nbz3=0;
	    int nbz4=0;
	    int nbzc=0;
	    
	    double c4neg = 0;
	    double c3neg = 0;
	    double c2neg = 0;
	    double c1neg = 0;
	    double c1pos = 0;
	    
	    double pc=0;
	    double E;
	    double V;
	 
	    
	     for (PlayersScore pscore :this.playerScoreList) {
	    	 
			 if (pscore.getHadicap() <= 4.4) {  //Cat 1
				 n1++;
				 if (pscore.getScore() >= 35 ) {
					 nbz1++;
				 }	 
			
			 }
			 
			 if ((pscore.getHadicap() >= 4.5) && (pscore.getHadicap() <= 11.4)) {  //Cat 2
				 n2++;
				 if (pscore.getScore() >= 34 ) {
					 nbz2++;
				 }
			 }
			 
			 if ((pscore.getHadicap() >= 11.5) && (pscore.getHadicap() <= 18.4)) {  //Cat 3
				 n3++;
				 if (pscore.getScore() >= 33 ) {
					 nbz3++;
				 }
				 
			 }
			 
			 if ((pscore.getHadicap() >= 18.5) && (pscore.getHadicap() <= 26.4)) {  //Cat 4
				 n4++;
				 if (pscore.getScore() >= 32 ) {
					 nbz4++;
				 }
			 }
 
			 
		 }
		 
		 nc= n1 +n2 +n3 +n4;
		 nbzc=nbz1 + nbz2 + nbz3 + nbz4;	 
		 pc=P1*(n1/nc) + P2*(n2/nc) + P3*(n3/nc) + P4*(n4/nc);
		 E=pc*nc;
		 V=Math.sqrt(nc*pc*(1-pc));
		 
		 //this could be taken up in a loop, it would look cooler, but less helpful.
		 //so let's keep it simple,stupid
		 
		 c4neg = Math.max(E + F4neg*V + A4neg, 0);
		 c3neg = Math.max(E + F3neg*V + A3neg, c4neg + 1);
		 c2neg = Math.max(E + F2neg*V + A2neg, c3neg + 1);
		 c1neg = Math.max(E + F1neg*V + A1neg, c2neg + 1);
		 c1pos = E + F1pos*V + A1pos;
		 
		 
		 if (nbzc >= 0 && nbzc <=c4neg) {
			 return -4;
		 }
		 
		 if (nbzc > c4neg  && nbzc <= c3neg) {
			 return -3;
		 }
		 
		 if (nbzc > c3neg  && nbzc <= c2neg) {
			 return -2;
		 }
		 
		 if (nbzc > c2neg  && nbzc <= c1neg) {
			 return -1;
		 }
		 
		 if (nbzc > c1neg  && nbzc < c1pos) {
			 return 0;
		 }
		 
		 if (nbzc >= c1pos  && nbzc <= nc) {
			 return 1;
		 }
		 
		//in case we made a boo boo 
		return 10;
	}
	
	public int getCBA() {
		
		
		if (this.playerScoreList != null && this.playerScoreList.size() > 9 ) {
			return calcCBA();
		} else {
			return 9;
		}
	
		
	}
	


	
	public void addPlayer(PlayersScore playerScore){
		this.playerScoreList.add(playerScore);
	}
	
	public void addPlayer(float handyCap, int score, int playerNum){		
		this.playerScoreList.add(new PlayersScore(handyCap,score,playerNum));		
	}
	
	public void setPlayer(PlayersScore playerScore){
		this.playerScoreList.set(playerScore.playerNum - 1, playerScore);
	}
	
	public void setPlayer(float handyCap, int score, int playerNum){
		this.playerScoreList.set(playerNum - 1, new PlayersScore(handyCap,score,playerNum));
	}
	
	public void removePlayer(int playerNum){
		this.playerScoreList.remove(playerNum);
	}
	
	
	public ArrayList<PlayersScore> getPlayscore() {
		return playerScoreList;
	}

	public void setPlayscore(ArrayList<PlayersScore> playscore) {
		this.playerScoreList = playscore;
	}
	
	
	
	
	
	
}
