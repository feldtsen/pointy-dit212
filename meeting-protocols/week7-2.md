# Meeting Agenda

Group: Pointy
Date: 2020-10-15
Chair: Erik
Participants: Erik, Mattias, Joachim, Simon

## Reports (15 min) from previous meeting

- Everyone has done pull request.
- Joachim has updated the RAD and SDD, rendering for obstacles, abstracted rendering with helper functions, drawing shapes, fixed audio handling and general clean up.
- Simon has updated the RAD and SDD, tried to set up Stan4j, added game over message and written tests.
- Erik has implemented next level functionality, written tests for level loader and obstacles, implemented obstacles into game functionality, implemented a gameWin conditions and made more levels.
- Mattias has worked on test class for dash, reworked tests, worked on shockwave visualization and reworked reflect ability and testing, created event handling for actions.
- Anton has written more comments, reworked reflect ability, worked on shockwwave ability, created event handling for actions.



## Objectives (5 min) 

- Create a game state panel which can be used for states such as game over, game win and level transitions.
- Rework the scoring system.
- Level select.
- Collision handling.
- Make more levels.
- Dependency analysis.
- Set up Travis.


## Discussion items (20 min)

- Game state panel. We decided to abstract the current gameOver panel to work with game state instead. 
- Scoring system will only reflect time with the time saved for each level instead of each player. In infinite mode the time survived is the score.
- Implement collision handling for obstacles.


## Outcomes and assignments (10 min)

- Everyone will make more levels.
- Joachim will look into Travis
- Simon and Erik will look at handling collisions.
- Mattias will work on getting the visualization for reflect.
- Joachim och Mattias will work on sound effects.
- Erik and Joachim will work on game state panel. 
- Anton will write more comments and assist other tasks. 


## Wrap up

- Next meeting: 2020-10-19