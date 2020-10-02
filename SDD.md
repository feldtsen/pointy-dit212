# System Design Document for Pointy

**Authors:** Anton Hildingsson, Erik Magnusson, Joachim Ørfeldt Pedersen, Mattias Oom, Simon Genne

**Version:** 1.0

**Date:** 2/10/2020

## 1. Introduction

Pointy is a topdown 2D game. The player is a simple geometrical shape which navigates a hostile, equally geometrical, world. In this world, the player is attacked by various enemies which shoot different kinds of projectiles at the player. The player itself has no weapon, but instead a set of abilities which (with some creativity) can be used to defeat the enemies. A few of these abilities are "reflection" (reflecting enemy projectiles), "shockwave" (pushing enemies away), "time manipulation" (slowing down time around the player) and "dash" (making the player invulnerable and very fast for a short period of time).

The map contains different neutral elements, such as walls, spikes and traps, which can both be dangerous for the player, or cleverly used to aid the player in their mission.

The goal of the player is to defeat all the elements, reach the map exit, or perform a specific task. The game contains multiple levels, and by defeating one level, the player can progress to the next.

### 1.1. Definitions, acronyms, abbreviations

- 2D - Two dimensional
- Topdown game - A game that is viewed from above.

## 2. System architecture
The general architecture of the application is rather simple. No external servers or databases is used -- the game is all run locally on the machine of the user. 

OpenJFX is used for the graphical end of the game, reading keyboard input, and handling sound. OpenJFX also manages the runnable application itself, which means a javaFX `Application` class is created to launch the game.

Persistent data storage is all handled locally by an external JSON-parser. More can be read below (4. Persistent Data Storage). 

When the application is started, the JavaFX Application loads a game window controller using an FXML loader. This game window controller then creates a new `Game` object, a game loop, and launches the game. At this point, all stored levels, user progress, and score, is read from disk, enabling the player to keep playing from where they last left of.

The player is prompted by a menu which controls the level settings, the starting and stopping of the game itself, and displaying score and other progress indicators. When the player starts the game, it will run until they stop it themselves, or until the game is finished.

When the player exits the application, all progress is stored locally. The same process is reapplied when the game is opened anew.

## 3. System Design
<img src="https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/package-diagrams/toplevel.png" width=100%>

The controller package interacts with the view by letting `GameWindowController` store a `Renderer` object that can be used to draw to the screen. It also has an `IGame` attribute, that will refer to the instance of `Game` used to run the game, through which it can interact with the gameplay. `GameWindowController` also creates a game loop, in which the renderer will be used to draw the current state of the game to the screen. In the loop, a call will be made to the model telling it to update its state.

Both the `Model` and `View` packages make use of the functions implemented in the util package to affect vectors.

As of now, the MVC implementation is not typical. The controller (in this case, the `GameWindowController`) has access to both the view and the model. However, there's no clear relationship between the view and the model themselves. Instead, the controller passes part of the model as a attribute to the draw method in the view (Renderer class). For the moment, we see no reason to have the view use polling or another design pattern to query the model for data, since all the required data in our case easily can be passed by the `GameWindowController`. Because of this, we think our implementation of MVC is perfect for our purposes. 

Here follows a set of diagrams over all our packages. We have decided to leave the fields containing lists of objects in the package diagrams, since the package diagrams otherwise cannot show the relationship between packages. However, we have left out these fields in the design model. Instead, these fields are represented by multiplicities. 

<img src="https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/package-diagrams/controller.png" width=100%>
<img src="https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/package-diagrams/gameloop.png" width=100%>
<img src="https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/package-diagrams/model.png" width=100%>
<img src="https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/package-diagrams/ability.png" width=100%>
<img src="https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/package-diagrams/action.png" width=100%>
<img src="https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/package-diagrams/behavior.png" width=100%>
<img src="https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/package-diagrams/behavior-ability.png" width=100%>
<img src="https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/package-diagrams/movement.png" width=100%>
<img src="https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/package-diagrams/entitiy.png" width=100%>
<img src="https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/package-diagrams/enemy.png" width=100%>
<img src="https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/package-diagrams/movable.png" width=100%>
<img src="https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/package-diagrams/obstacle.png" width=100%>
<img src="https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/package-diagrams/player.png" width=100%>
<img src="https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/package-diagrams/projectile.png" width=100%>
<img src="https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/package-diagrams/level.png" width=100%>
<img src="https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/package-diagrams/shape2d.png" width=100%>
<img src="https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/package-diagrams/services.png" width=100%>
<img src="https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/package-diagrams/util.png" width=100%>
<img src="https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/package-diagrams/view.png" width=100%>

**Design model:**

<img src=https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/design-model.png width=100%>

**Domain model:**

<img src="https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/domain-model.png" width=100%>

### 3.1 Relation between domain model and design model

In the domain model, The `Game` class is said to run the `Level` which contains a player, enemies, obstacles, and projectiles. This is reflected in the design model, where `Game` has a reference to an `ILevel` (currentLevel) and a list of `ILevels` (levels). `Level` holds references to the enemies, obstacles, player, and projectiles, that are to be shown while on the level that is represented by that object. `Game` will, during gameplay, access these and update them according to the state of the game and input from the user. 

The domain model shows `Enemy` to have two behaviours. In the design model, this is the case since `Enemy` has a reference to an `IAbilityBehaviour` and an `IMovementBehaviour`. These will dictate what actions are carried out by the enemy.

Both the `Player` and `Behaviour` in the domain model have references to `Ability`. In the design model, `Player` has  a reference to one to three `IAbilties`. These abilities will be used by the player during the gameplay to affect the environment/state of the player in some way. The multiplicity of behaviours relation to `Ability` is 0..* in the domain model. In the design model, some behaviours will have no knowledge of abilities (movementBehaviours), while some will be able to hold many (abilityBehaviours). 

The `Ability` in the domain model creates 0..* projectiles. In the design model, some concrete ability classes have references to projectiles. These abilities are supposed to create projectiles and add them to the level. Other abilities have no knowledge of projectiles at all.

### 3.2 Implemented design patterns

* MVC (Model View Controller) for separating game logic, user input and graphical interface.
* the singleton pattern, for the keyboard input controller.
* the factory (method) pattern, for simplifying the creation of game entities such as players and enemies
* the command pattern, which is used for executing actions when a key is pressed.
* the composite pattern, allowing players and enemies to have different abilities and behaviors. The construction of these entities is simplified using the factory pattern.
* the template method pattern, by letting Ability implement a method that is dependent on an abstract method implemented by subclasses.

## 4. Persistent data management

The application currently makes use of JSON to handle level data. The level files contain JSON objects pertaining to information of the level and its entities, i.e. their type (player, enemy, obstacle), variants (e.g. type of enemy) as well as instance variables not handled by the factory. Levels are loaded through the static class `LevelLoader` which parses the JSON file corresponding to a certain level ID, creates an object of type `Level` and returns this object to be used by the `Game` class. Each level is contained within a separate file and is only loaded when needed to save memory resources. The parsing is done using the GSON library. 

Future possible functionality includes storing and parsing level data as ascii to enable easier level development, saving player progress made up to that point, as well as keeping top scores.  


## 5. Quality

The application is tested using unit tests with the framework JUnit. These tests can be found under: project/src/main/test.

## 6. References
- JavaFX - https://openjfx.io/
- Maven - https://maven.apache.org/
- Lucidchart - https://www.lucidchart.com/
- JUnit - https://junit.org/
- Git - https://git-scm.com/
- Trello - https://www.trello.com/
