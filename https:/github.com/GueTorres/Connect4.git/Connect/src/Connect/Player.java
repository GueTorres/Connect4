package Connect;

public class Player implements Comparable<Player>{

	private String playerName;
	private String playerSymbol;
	private int numGames;
	private int numWins;
	private int numLosses;

	public Player(){
		playerName = "PlayDoe";
		playerSymbol = " * ";
		numGames = 0;
		numWins = 0;
		numLosses = 0;
	}
	public Player(String name, String symbol){
		//		this(); //can call the default constructor of the same class instead of assigning the 0 values... same result
		numGames = 0;
		numWins = 0;
		numLosses = 0;
		playerName = name;
		playerSymbol = symbol;
	}
	//no setter for numWins, numLosses, numGames
	//getNumWins,getNumLosses,getNumGames
	//getPercentageOfWins, getPercentageOfLosses
	public void addNumWins(){
		numWins++;
		numGames++;
	}
	public void addNumLosses(){
		numLosses++;
		numGames++;
	}
	public void addDraw(){
		numGames++;//not a win or a loss.. but a game was played
	}
	public int getNumWins(){
		return numWins;
	}
	public int getNumLosses(){
		return numLosses;
	}
	public int getNumGames(){
		return numGames;
	}
	public String getSymbol(){
		return playerSymbol;
	}
	public String getName(){
		return playerName;
	}
	//think about whether you want to allow a setter for name and symbol
	
	public boolean equals(Object o){
		if(o instanceof Player){
			Player otherPlayer = (Player)o;
			if(this.playerName.equalsIgnoreCase(otherPlayer.playerName)){
				if(this.playerSymbol.equalsIgnoreCase(otherPlayer.playerSymbol)){
					if(this.numGames == otherPlayer.numGames){
						if(this.numLosses == otherPlayer.numLosses){
							if(this.numWins == otherPlayer.numWins){
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public String toString(){
		String s = "Player [ name= " + playerName + ", " + 
				" symbol= " + playerSymbol + ", " +
				" wins= " + numWins + ", " + 
				" losses= " + numLosses + ", " + 
				" total games= " + numGames + " ]";
		return s;
	}
	
	@Override
	public int compareTo(Player otherP){
		if(this.numWins > otherP.numWins){
			return 1;
		}
		else if(this.numWins < otherP.numWins){
			return -1;
		}
		else{//not less or greater ... equal 
			return 0;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
