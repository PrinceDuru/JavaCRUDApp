package game_package;

public class Game {
	
	// Define instance variables
    private String gameId;
    private String gameTitle;
    
    // Create the constructor and initialize the variable
    public Game(String gameTitle) {
            this.gameTitle = gameTitle;
    }
    
    // Also create the default constructor
    public Game() {
    }
    
    // Create getter and setters for the instance variables
    public String getGameId() {
            return gameId;
    }
    public void setGameId(String gameId) {
            this.gameId = gameId;
    }
    public String getGameTitle() {
            return gameTitle;
    }
    public void setGameTitle(String gameTitle) {
            this.gameTitle = gameTitle;
    }
    
    // Override toString method from the object super class
    @Override
    public String toString() {
        return this.getGameTitle();
    }
    
}
