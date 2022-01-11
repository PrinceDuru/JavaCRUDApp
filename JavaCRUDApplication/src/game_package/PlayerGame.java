package game_package;

public class PlayerGame {
	
		// Define instance variables
        private String playerGameId;
        private String gameId;
        private String playerId;
        private String playingDate;
        private String score;
        
        // Create the constructor and initialize the variables
        public PlayerGame(String playerGameId, String gameId, String playerId, String playingDate, String score) {
                this.playerGameId = playerGameId;
        		this.gameId = gameId;
                this.playerId = playerId;
                this.playingDate = playingDate;
                this.score = score;
        }
        
        // Also create the default constructor
        public PlayerGame() {
        }
        
        // Overload the constructor with a different number of parameters --- 4
		public PlayerGame(String gameId, String playerId, String playingDate, String score) {
			// TODO Auto-generated constructor stub
			this.gameId = gameId;
            this.playerId = playerId;
            this.playingDate = playingDate;
            this.score = score;
		}
		
		// Create getter and setters for the instance variables
		public String getPlayerGameId() {
                return playerGameId;
        }
        public void setPlayerGameId(String playerGameId) {
                this.playerGameId = playerGameId;
        }
        public String getGameId() {
                return gameId;
        }
        public void setGameId(String gameId) {
                this.gameId = gameId;
        }
        public String getPlayerId() {
                return playerId;
        }
        public void setPlayerId(String playerId) {
                this.playerId = playerId;
        }
        public String getPlayingDate() {
                return playingDate;
        }
        public void setPlayingDate(String playingDate) {
                this.playingDate = playingDate;
        }
        public String getScore() {
                return score;
        }
        public void setScore(String score) {
                this.score = score;
        }
        
        
}