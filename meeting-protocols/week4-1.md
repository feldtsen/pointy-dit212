# Meeting Agenda
Group: Pointy
Date: 2020-09-21
Chair: Anton
Participants: Everyone

## Reports (10 min) from previous meeting
- Joachim has fixed a menu with pause ability.
- Erik has implemented a basic map and started on LevelLoader class.
- Simon has reworked the collision method.
- Anton and Mattias has made entities generic (have them remember the type of shape they are).
- Simon has created a rectangle2D class.

## Objectives (5 min) 
- Create design model -- detailed description of model, view, services, etc...
- Create SDD
- Proper input control
- Basic entity collision handling, to ensure entities doesn't leave map.

## Discussion items (20 min)
- SDD
- Design model
- Level vs Game class
- Collision handling
- Dangerous enemy
- Priorities, rework user stories

## Outcomes and assignments (10 min)
- SDD
    - create document
    - communicate clearly the scope and purpose of the project!
    - Include model, controller, view, etc.
    - Include updated design model.
    * Simon will work on the SDD, create basic template
    * Everyone will discuss the contents of the SDD together.

- Design model
    * Erik will work on design model and include in SDD

- Level vs Game class
    - level will hold all level data
    - game will handle game logic and update entities, and keep track of score!
    - we will wait with level loader implementation! Level loader is more of a service 
    * Erik will create a basic level and game class to initialize it, which we can then play.

- Collision handling
    - game class will handle collision
    - create basic first level with walls which the player and enemy cannot penetrate
    * Anton will create basic collision handling

- Dangerous enemy
    * Mattias will create a basic "Killontouch" ability to ensure the enemy is a serious threat.

- Priorities, rework user stories
    - We might need to create new user stories, move low priority ones to separate column, etc!
    * Anton and Erik will rework user stories.

* Joachim and Anton will implement observer-observable pattern for user input.

## Wrap up
- Next meeting 2020-09-25
