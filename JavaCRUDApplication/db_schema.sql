CREATE TABLE GAME
(game_id NUMBER(4) PRIMARY KEY,
game_title VARCHAR2(20),
CONSTRAINT game_game_title_unique UNIQUE (game_title));

CREATE TABLE Player
(player_id VARCHAR2(5) PRIMARY KEY,
first_name VARCHAR2(15),
last_name VARCHAR2(15),
address VARCHAR2(30),
postal_code VARCHAR2(10),
province VARCHAR2(20),
phone_number VARCHAR2(15));

CREATE TABLE PLAYER_AND_GAME
(player_game_id NUMBER(10) PRIMARY KEY,
game_id NUMBER(4),
player_id VARCHAR2(5),
playing_date VARCHAR2(15),
score VARCHAR2(15),
CONSTRAINT PLAYER_AND_GAME_game_id_fk FOREIGN KEY (game_id) REFERENCES Game(game_id),
CONSTRAINT PLAYER_AND_GAME_player_id_fk FOREIGN KEY (player_id) REFERENCES Player(player_id));