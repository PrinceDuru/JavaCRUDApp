package game_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameUserInterface extends Application {

        @Override
        public void start(Stage stage) {
                stage.setTitle("GameApp");

                // Create GridPane to contain button for various functionalities
                GridPane gridPane = new GridPane();
                gridPane.setAlignment(Pos.CENTER);
                gridPane.setHgap(10);
                gridPane.setVgap(10);
                gridPane.setPadding(new Insets(25, 25, 25, 25));

                // Create buttons that will implement various tasks
                Button insertPlayerButton = new Button("INSERT PLAYER");
                Button insertGameButton = new Button("INSERT GAME");
                Button mapPlayerGameButton = new Button("MAP PLAYER & GAME");
                Button updatePlayerInfoButton = new Button("UPDATE PLAYER");
                Button displayReportButton = new Button("DISPLAY REPORT");

                // Add buttons to the GridPane
                gridPane.add(insertPlayerButton, 0, 0);
                gridPane.add(insertGameButton, 0, 1);
                gridPane.add(mapPlayerGameButton, 0, 2);
                gridPane.add(updatePlayerInfoButton, 0, 3);
                gridPane.add(displayReportButton, 0, 4);   
           
                // Create event handle to handle (at the click of the button) the task of populating 
                // player information and inserting them to the database
                insertPlayerButton.setOnAction(actionEvent -> {
                	
                		// At the click of the button, create another gridpane 
                		// and add the following labels and textfields
                        GridPane grid = new GridPane();
                        grid.setAlignment(Pos.CENTER);
                        grid.setHgap(10);
                        grid.setVgap(10);
                        grid.setPadding(new Insets(25, 25, 25, 25));

                        Label playerIdLabel = new Label("Player ID:");
                        grid.add(playerIdLabel, 0, 0);
                        
                        TextField playerIdField = new TextField();
                        grid.add(playerIdField, 1, 0);
                        
                        Label firstNameLabel = new Label("First Name:");
                        grid.add(firstNameLabel, 0, 1);

                        TextField firstNameField = new TextField();
                        grid.add(firstNameField, 1, 1);

                        Label lastNameLabel = new Label("Last Name:");
                        grid.add(lastNameLabel, 0, 2);

                        TextField lastNameField = new TextField();
                        grid.add(lastNameField, 1, 2);

                        Label addressLabel = new Label("Address:");
                        grid.add(addressLabel, 0, 3);

                        TextField addressField = new TextField();
                        grid.add(addressField, 1, 3);

                        Label postalCodeLabel = new Label("Postal Code:");
                        grid.add(postalCodeLabel, 0, 4);

                        TextField postalCodeField = new TextField();
                        grid.add(postalCodeField, 1, 4);

                        Label provinceLabel = new Label("Province:");
                        grid.add(provinceLabel, 0, 5);

                        TextField provinceField = new TextField();
                        grid.add(provinceField, 1, 5);

                        Label phoneNumberLabel = new Label("Phone Number:");
                        grid.add(phoneNumberLabel, 0, 6);

                        TextField phoneNumberField = new TextField();
                        grid.add(phoneNumberField, 1, 6);

                        Button insertPlayer = new Button("INSERT PLAYER");
                        grid.add(insertPlayer, 1, 7);

                        Scene insertPlayerScene = new Scene(grid, 600, 600);
                        Stage insertPlayerWindow = new Stage();
                        insertPlayerWindow.setScene(insertPlayerScene);
                        insertPlayerWindow.setTitle("Insert Player");
                        insertPlayerWindow.show();
                        
                        // Create even handler to insert the inputed Player information to the database
                        insertPlayer.setOnAction(ae -> {
                        		String playerId = playerIdField.getText();
                                String firstName = firstNameField.getText();
                                String lastName = lastNameField.getText();
                                String address = addressField.getText();
                                String postalCode = postalCodeField.getText();
                                String province = provinceField.getText();
                                String phoneNumber = phoneNumberField.getText();
                                
                                Player player = new Player(playerId, firstName, lastName, address, postalCode, province, phoneNumber);
                                
                                // Call the insertPlayerInfo method to insert player object to the DB
                                insertPlayerInfo(player);
                                insertPlayerWindow.close();
                        });
                });
                
                // Create event handler to insert new game info
                insertGameButton.setOnAction(actionEvent -> {
                        GridPane grid = new GridPane();
                        grid.setAlignment(Pos.CENTER);
                        grid.setHgap(10);
                        grid.setVgap(10);
                        grid.setPadding(new Insets(25, 25, 25, 25));

                        Label gameTitleLabel = new Label("Game Title");
                        grid.add(gameTitleLabel, 0, 0);

                        TextField gameTitleField = new TextField();
                        grid.add(gameTitleField, 1, 0);

                        Button insertGame = new Button("INSERT GAME");
                        grid.add(insertGame, 1, 1);

                        Scene insertGameScene = new Scene(grid, 400, 300);
                        Stage insertGameWindow = new Stage();
                        insertGameWindow.setScene(insertGameScene);
                        insertGameWindow.setTitle("Insert Game");
                        insertGameWindow.show();
                        
                        // Another event handler to implement database insert upon click of the insertGame button
                        insertGame.setOnAction(ae -> {
                                String gameTitle = gameTitleField.getText();
                                Game game = new Game(gameTitle);
                                
                                // Call the insertGameInfo method to insert game object to the DB
                                insertGameInfo(game);
                                insertGameWindow.close();
                        });

                });
                
                // Create event handler for mapPlayerGameButton
                mapPlayerGameButton.setOnAction(actionEvent -> {
                        GridPane grid = new GridPane();
                        grid.setAlignment(Pos.CENTER);
                        grid.setHgap(10);
                        grid.setVgap(10);
                        grid.setPadding(new Insets(25, 25, 25, 25));

                        Label selectPlayerLabel = new Label("Select a player");
                        grid.add(selectPlayerLabel, 0, 0);

                        List<Player> players = getAllPlayersInfo();

                        ComboBox<Player> playerCb = new ComboBox<Player>();

                        for (Player player: players) {
                                playerCb.getItems().add(player);
                        }
                        
                        grid.add(playerCb, 1, 0);

                        Label selectGameLabel = new Label("Select a game");
                        grid.add(selectGameLabel, 0, 1);

                        List<Game> games = getAllGames();

                        ComboBox<Game> gameCb = new ComboBox<Game>();

                        for (Game game: games) {
                                gameCb.getItems().add(game);
                        }
                        grid.add(gameCb, 1, 1);

                        Label gamePlayedOnLabel = new Label("Game Played On");
                        grid.add(gamePlayedOnLabel, 0, 2);

                        TextField playedDate = new TextField();
                        grid.add(playedDate, 1, 2);

                        Label scoreLabel = new Label("Score");
                        grid.add(scoreLabel, 0, 3);

                        TextField scoreField = new TextField();
                        grid.add(scoreField, 1, 3);

                        Button mapPlayerGame = new Button("MAP PLAYER & GAME");
                        grid.add(mapPlayerGame, 1, 4);

                        Scene insertGameScene = new Scene(grid, 600, 600);
                        Stage insertGameWindow = new Stage();
                        insertGameWindow.setScene(insertGameScene);
                        insertGameWindow.setTitle("Insert Game");
                        insertGameWindow.show();

                        // Create event handler to insert the new playerGame object to the PLAYER_AND_GAME DB table
                        mapPlayerGame.setOnAction(ae -> {
                        		TextField gameTitleField = new TextField();
                                String gameTitle = gameTitleField.getText();
                                Game game = new Game(gameTitle);
                                insertGameInfo(game);
                                
                                Player selectedPlayer = playerCb.getSelectionModel().getSelectedItem();
                                Game selectedGame = gameCb.getSelectionModel().getSelectedItem();
                                
                                String playedOn = playedDate.getText();
                                String score = scoreField.getText();

                                PlayerGame playerGame = new PlayerGame(selectedGame.getGameId(), selectedPlayer.getPlayerId(), playedOn, score);
                                
                                // Call the insertPlayerGameInfo to implement the DB insert
                                insertPlayerGameInfo(playerGame);
                                insertGameWindow.close();
                        });

                });
                
                // Create event handler to handle click event on the updatePlayerInfoButton button
                updatePlayerInfoButton.setOnAction(actionEvent -> {
                	GridPane grid = new GridPane();
                	grid.setAlignment(Pos.CENTER);
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(25, 25, 25, 25));
                    
                    Label selectPlayerLabel = new Label("Select a player");
                    grid.add(selectPlayerLabel, 0, 0);
                      
                    List<Player> players = getAllPlayersInfo();

                    ComboBox<Player> playerCb = new ComboBox<Player>();

                    for (Player player: players) {
                            playerCb.getItems().add(player);
                    }
                    
                    grid.add(playerCb, 1, 0);
                    
                    Button updatePlayer = new Button("Upate Player Info");
                    grid.add(updatePlayer, 1, 5);
                    

                    Scene updatePlayerScene = new Scene(grid, 400, 300);
                    Stage updatePlayerWindow = new Stage();
                    updatePlayerWindow.setScene(updatePlayerScene);
                    updatePlayerWindow.setTitle("Upate Player");
                    updatePlayerWindow.show();
                    
                    // This event handler creates a gridpane and populates added textAreas 
                    // with details of selected player for the purpose of editing and updating
                    updatePlayer.setOnAction(ae -> {
                    	GridPane gridUpdate = new GridPane();
                    	gridUpdate.setAlignment(Pos.CENTER);
                    	gridUpdate.setHgap(10);
                    	gridUpdate.setVgap(10);
                    	gridUpdate.setPadding(new Insets(25, 25, 25, 25));
                    	
                        Player selectedPlayer = playerCb.getSelectionModel().getSelectedItem();
                        
                        Label playerIdLabel = new Label("Player ID:");
                        gridUpdate.add(playerIdLabel, 0, 0);
                        
                        TextArea playerIdArea = new TextArea();
                        playerIdArea.setMaxHeight(20);
                        playerIdArea.setMaxWidth(300);
                        playerIdArea.setText(selectedPlayer.getPlayerId());
                        gridUpdate.add(playerIdArea, 1, 0);
                        
                        Label firstNameLabel = new Label("First Name:");
                        gridUpdate.add(firstNameLabel, 0, 1);

                        TextArea firstNameArea = new TextArea();
                        firstNameArea.setMaxHeight(20);
                        firstNameArea.setMaxWidth(300);
                        firstNameArea.setText(selectedPlayer.getFirstName());
                        gridUpdate.add(firstNameArea, 1, 1);

                        Label lastNameLabel = new Label("Last Name:");
                        gridUpdate.add(lastNameLabel, 0, 2);

                        TextArea lastNameArea = new TextArea();
                        lastNameArea.setMaxHeight(20);
                        lastNameArea.setMaxWidth(300);
                        lastNameArea.setText(selectedPlayer.getLastName());
                        gridUpdate.add(lastNameArea, 1, 2);

                        Label addressLabel = new Label("Address:");
                        gridUpdate.add(addressLabel, 0, 3);

                        TextArea addressArea = new TextArea();
                        addressArea.setMaxHeight(20);
                        addressArea.setMaxWidth(300);
                        addressArea.setText(selectedPlayer.getAddress());
                        gridUpdate.add(addressArea, 1, 3);

                        Label postalCodeLabel = new Label("Postal Code:");
                        gridUpdate.add(postalCodeLabel, 0, 4);

                        TextArea postalCodeArea = new TextArea();
                        postalCodeArea.setMaxHeight(20);
                        postalCodeArea.setMaxWidth(300);
                        postalCodeArea.setText(selectedPlayer.getPostalCode());
                        gridUpdate.add(postalCodeArea, 1, 4);

                        Label provinceLabel = new Label("Province:");
                        gridUpdate.add(provinceLabel, 0, 5);

                        TextArea provinceArea = new TextArea();
                        provinceArea.setMaxHeight(20);
                        provinceArea.setMaxWidth(300);
                        provinceArea.setText(selectedPlayer.getProvince());
                        gridUpdate.add(provinceArea, 1, 5);

                        Label phoneNumberLabel = new Label("Phone Number:");
                        gridUpdate.add(phoneNumberLabel, 0, 6);

                        TextArea phoneNumberArea = new TextArea();
                        phoneNumberArea.setMaxHeight(20);
                        phoneNumberArea.setMaxWidth(300);
                        phoneNumberArea.setText(selectedPlayer.getPhoneNumber());
                        gridUpdate.add(phoneNumberArea, 1, 6);

                        Button saveChanges = new Button("SAVE CHANGES");
                        gridUpdate.add(saveChanges, 1, 7);
                        
                        Scene editPlayerScene = new Scene(gridUpdate, 600, 600);
                        Stage editPlayerWindow = new Stage();
                        editPlayerWindow.setScene(editPlayerScene);
                        editPlayerWindow.setTitle("Save Changes");
                        editPlayerWindow.show();
                        
                        // Create event handler to make the update to the database 
                        // after editing and clicking the saveChanges button
                        saveChanges.setOnAction(e -> {
                        	String playerId = playerIdArea.getText();
                            String firstName = firstNameArea.getText();
                            String lastName = lastNameArea.getText();
                            String address = addressArea.getText();
                            String postalCode = postalCodeArea.getText();
                            String province = provinceArea.getText();
                            String phoneNumber = phoneNumberArea.getText();
                            
                            Player updatedPlayer = new Player(playerId, firstName, lastName, address, postalCode, province, phoneNumber);
                            
                            // Call the updatePlayerInfo method to implement the DB update
                            updatePlayerInfo(updatedPlayer);
                            editPlayerWindow.close();  // edit window is closed after saving changes
                        
                        });
                        
                    });
                });
                
                // Event handler to handle click event on displayReportButton 
                // which will display player and games played report
                displayReportButton.setOnAction(actionEvent -> {
                                     
                  TableView<PlayerGame> table = new TableView<PlayerGame>();
                  
                  TableColumn<PlayerGame, String> column1 = new TableColumn<PlayerGame, String>("player_game_id");
                  column1.setMaxWidth(100);
                  column1.setCellValueFactory(new PropertyValueFactory<PlayerGame, String>("playerGameId"));
                  
                  TableColumn<PlayerGame, String> column2 = new TableColumn<PlayerGame, String>("game_id");
                  column2.setMinWidth(100);
                  column2.setCellValueFactory(new PropertyValueFactory<PlayerGame, String>("gameId"));
                  
                  TableColumn<PlayerGame, String> column3 = new TableColumn<PlayerGame, String>("player_id");
                  column3.setMinWidth(100);
                  column3.setCellValueFactory(new PropertyValueFactory<PlayerGame, String>("playerId"));
                  
                  TableColumn<PlayerGame, String> column4 = new TableColumn<PlayerGame, String>("playing_date");
                  column4.setMinWidth(200);
                  column4.setCellValueFactory(new PropertyValueFactory<PlayerGame, String>("playingDate"));
                  
                  TableColumn<PlayerGame, String> column5 = new TableColumn<PlayerGame, String>("score");
                  column5.setMinWidth(100);
                  column5.setCellValueFactory(new PropertyValueFactory<PlayerGame, String>("score"));

                  ObservableList<PlayerGame> playerAndGames = getAllPlayerAndGamesInfo();
                  table.setItems(playerAndGames);
                  table.getColumns().addAll(column1, column2, column3, column4, column5);
                  table.setTableMenuButtonVisible(true);
                  
                  VBox vBox = new VBox();
                  vBox.getChildren().add(table);
                  
                  Scene reportScene = new Scene(vBox, 800, 600);
                  Stage reportrWindow = new Stage();
                  reportrWindow.setScene(reportScene);
                  reportrWindow.setTitle("Display Reports");
                  reportrWindow.show();
                  
                  });
                
                                
                Scene scene = new Scene(gridPane, 400, 500);
                stage.setScene(scene);

                stage.show();
        }

        // Create method to insert playerGame object details to the DB
        private void insertPlayerGameInfo(PlayerGame playerGame) {
                Connection connection = null;
                PreparedStatement statement = null;
                try {
                        connection = Database.getDBConnection();
                        connection.setAutoCommit(false);
                        String query = "INSERT INTO PLAYER_AND_GAME VALUES (?, ?, ?, ?, ?)";
                        statement = connection.prepareStatement(query);
                        statement.setInt(1, getNextPlayerGameId());
                        statement.setString(2, playerGame.getGameId());
                        statement.setString(3, playerGame.getPlayerId());
                        statement.setString(4, playerGame.getPlayingDate());
                        statement.setString(5, playerGame.getScore());
                        statement.executeUpdate();
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        if (null != statement) {
                                try {
                                        statement.close();
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                        }
                        if (null != connection) {
                                try {
                                        connection.close();
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                        }
                }
        }
        
        // Create method to generate getNextPlayerGameId
        private int getNextPlayerGameId() {
                int nextGameId = 0;
                Connection connection = null;
                PreparedStatement statement = null;
                try {
                        connection = Database.getDBConnection();
                        connection.setAutoCommit(false);
                        String query = "SELECT MAX(PLAYER_GAME_ID) FROM PLAYER_AND_GAME";
                        statement = connection.prepareStatement(query);
                        ResultSet rs = statement.executeQuery();
                        while (rs.next()) {
                                nextGameId = rs.getInt(1) + 1;
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        if (null != statement) {
                                try {
                                        statement.close();
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                        }
                        if (null != connection) {
                                try {
                                        connection.close();
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                        }
                }
                return nextGameId;

        }

        // Extract details of all records in the GAME table and store them in a list
        private List<Game> getAllGames() {
                List<Game> games = new ArrayList<Game>();
                Connection connection = null;
                PreparedStatement statement = null;
                try {
                        connection = Database.getDBConnection();
                        connection.setAutoCommit(false);
                        String query = "SELECT * FROM GAME";
                        statement = connection.prepareStatement(query);
                        ResultSet rs = statement.executeQuery();
                        Game game = null;
                        while (rs.next()) {
                                game = new Game();
                                game.setGameId(rs.getString(1));
                                game.setGameTitle(rs.getString(2));
                                games.add(game);
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        if (null != statement) {
                                try {
                                        statement.close();
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                        }
                        if (null != connection) {
                                try {
                                        connection.close();
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                        }
                }
                return games;
        }

     // Extract details of all records in the PLAYER table and store them in a list
        private List<Player> getAllPlayersInfo() {
                List<Player> players = new ArrayList<Player>();
                Connection connection = null;
                PreparedStatement statement = null;
                try {
                        connection = Database.getDBConnection();
                        connection.setAutoCommit(false);
                        String query = "SELECT * FROM PLAYER";
                        statement = connection.prepareStatement(query);
                        ResultSet rs = statement.executeQuery();
                        Player player = null;
                        while (rs.next()) {
                                player = new Player(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                                players.add(player);
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        if (null != statement) {
                                try {
                                        statement.close();
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                        }
                        if (null != connection) {
                                try {
                                        connection.close();
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                        }
                }
                return players;
        }

        // Extract details of all records in the PLAYER table and store them in an ObservableList
        private ObservableList<PlayerGame> getAllPlayerAndGamesInfo() {
        	ObservableList<PlayerGame> playerAndGames = FXCollections.observableArrayList();
            Connection connection = null;
            PreparedStatement statement = null;
            try {
                    connection = Database.getDBConnection();
                    connection.setAutoCommit(false);
                    String query = "SELECT * FROM PLAYER_AND_GAME";
                    statement = connection.prepareStatement(query);
                    ResultSet rs = statement.executeQuery();
                    PlayerGame playerAndGame = null;
                    while (rs.next()) {
                    	playerAndGame = new PlayerGame(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                    	playerAndGames.add(playerAndGame);
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    if (null != statement) {
                            try {
                                    statement.close();
                            } catch (SQLException e) {
                                    e.printStackTrace();
                            }
                    }
                    if (null != connection) {
                            try {
                                    connection.close();
                            } catch (SQLException e) {
                                    e.printStackTrace();
                            }
                    }
            }
            return playerAndGames;
    }
        
        // Create method to implement DB insert of new game objects into the GAME table
        private void insertGameInfo(Game game) {
                Connection connection = null;
                PreparedStatement statement = null;
                try {
                        connection = Database.getDBConnection();
                        connection.setAutoCommit(false);
                        String query = "INSERT INTO GAME VALUES (?, ?)";
                        statement = connection.prepareStatement(query);
                        statement.setInt(1, getNextGameId());
                        statement.setString(2, game.getGameTitle());
                        int count = statement.executeUpdate();
                        if (count == 1) {
                                this.alert("Success", "Game Information inserted to DB successfully", AlertType.INFORMATION);
                        } else {
                                this.alert("Failure", "Some error while adding Game Information", AlertType.ERROR);
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        if (null != statement) {
                                try {
                                        statement.close();
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                        }
                        if (null != connection) {
                                try {
                                        connection.close();
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                        }
                }
        }

        // Create method to generate getNextGameId
        private int getNextGameId() {
                int nextGameId = 0;
                Connection connection = null;
                PreparedStatement statement = null;
                try {
                        connection = Database.getDBConnection();
                        connection.setAutoCommit(false);
                        String query = "SELECT MAX(GAME_ID) FROM GAME";
                        statement = connection.prepareStatement(query);
                        ResultSet rs = statement.executeQuery();
                        while (rs.next()) {
                                nextGameId = rs.getInt(1) + 1;
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        if (null != statement) {
                                try {
                                        statement.close();
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                        }
                        if (null != connection) {
                                try {
                                        connection.close();
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                        }
                }
                return nextGameId;

        }
        
        // Create method to implement DB insert of new player objects into the PLAYER table
        private void insertPlayerInfo(Player player) {
                Connection connection = null;
                PreparedStatement statement = null;
                try {
                        connection = Database.getDBConnection();
                        connection.setAutoCommit(false);
                        String query = "INSERT INTO PLAYER VALUES (?, ?, ?, ?, ?, ?, ?)";
                        statement = connection.prepareStatement(query);
                        statement.setString(1, player.getPlayerId());
                        statement.setString(2, player.getFirstName());
                        statement.setString(3, player.getLastName());
                        statement.setString(4, player.getAddress());
                        statement.setString(5, player.getPostalCode());
                        statement.setString(6, player.getProvince());
                        statement.setString(7, player.getPhoneNumber());
                        int count = statement.executeUpdate();
                        if (count == 1) {
                                this.alert("Success", "Player Information inserted to DB successfully", AlertType.INFORMATION);
                        } else {
                                this.alert("Failure", "Some error while adding Player Information", AlertType.ERROR);
                        }

                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        if (null != statement) {
                                try {
                                        statement.close();
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                        }
                        if (null != connection) {
                                try {
                                        connection.close();
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                        }
                }
        }

        
        // Create method to implement DB update of already existing player info in the PLAYER table
        private void updatePlayerInfo(Player player) {
            Connection connection = null;
            PreparedStatement statement = null;
            try {
                    connection = Database.getDBConnection();
                    connection.setAutoCommit(false);
                    String updateQuery = "update PLAYER set player_id=?, first_name=?, last_name=?, address=?, postal_code=?, province=?, phone_number=? where player_id='"+player.getPlayerId()+"' ";
                    statement = connection.prepareStatement(updateQuery);
                    statement.setString(1, player.getPlayerId());
                    statement.setString(2, player.getFirstName());
                    statement.setString(3, player.getLastName());
                    statement.setString(4, player.getAddress());
                    statement.setString(5, player.getPostalCode());
                    statement.setString(6, player.getProvince());
                    statement.setString(7, player.getPhoneNumber());
                    int count = statement.executeUpdate();
                    if (count == 1) {
                            this.alert("Success", "Player Information updated on the DB successfully", AlertType.INFORMATION);
                    } else {
                            this.alert("Failure", "Some error while updating Player Information", AlertType.ERROR);
                    }

            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    if (null != statement) {
                            try {
                                    statement.close();
                            } catch (SQLException e) {
                                    e.printStackTrace();
                            }
                    }
                    if (null != connection) {
                            try {
                                    connection.close();
                            } catch (SQLException e) {
                                    e.printStackTrace();
                            }
                    }
            }
    }
        

        // Method to show alert messages
        public void alert(String title, String message, AlertType alertType) {
                Alert alert = new Alert(alertType);
                alert.setTitle(title);
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.showAndWait();
        }

        // Execution starts from here
        public static void main(String[] args) {
                launch(args);
        }
}