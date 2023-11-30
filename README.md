# Mancala Game
![img_1.png](/screenshots/img_1.png)
![img.png](/screenshots/img.png)

## How to play

### Board Setup
Each of the two players has his six pits in front of him. 
To the right of the six pits, each player has a larger pit. 
At the start of the game, there are six stones in each
of the six round pits.
### Rules
#### Game Play
The player who begins with the first move picks up all the stones in any of his own six pits, 
and sows the stones on to the right, one in each of the following pits,
including his own big pit. No stones are put in the opponents' big pit. 
If the player's last stone lands in his own big pit, he gets another turn. This can be repeated several times before it's the other player's turn.

#### Capturing Stones
During the game the pits are emptied on both sides. Always when the last stone
lands in an own empty pit, the player captures his own stone and all stones in the
opposite pit (the other player’s pit) and puts them in his own (big or little?) pit.

#### The Game Ends
The game is over as soon as one of the sides runs out of stones. The player who
still has stones in his pits keeps them and puts them in his big pit. The winner of
the game is the player who has the most stones in his big pit.
You can also find some visual explanations of the game rules by running a
Google Search for Mancala or Kalaha game.



## How to run
1. Clone the repository
2. Create the Docker images using the following command from the root directory
```bash
docker build -t mancala-api:latest ./mancala-api
docker build -t mancala-ui:latest ./mancala-ui
```
3. Run the API container using the following command
```bash
docker run --rm --name mancala-api -p 8080:8080 mancala-api
```
4. Run the UI container using the following command
```bash
docker run --rm --name mancala-ui -p 8000:80 mancala-ui
```
5. Open the browser and navigate to http://localhost:8000 to play the game

## Development
### API
#### Prerequisites
1. Java 17
2. Maven 3.9.5

#### Running the development server
1. Navigate to the API directory `/mancala-api`
```bash
mvn clean install
mvn spring-boot:run
```

### UI
#### Prerequisites
1. Node 20
2. Angular CLI 17

#### Running the development server
1. Navigate to the UI directory `/mancala-ui`
```bash
npm install
ng serve
```
