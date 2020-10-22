# Requirements and Analysis Document for Pointy 

**Authors:** Anton Hildingsson, Erik Magnusson, Joachim Ørfeldt Pedersen, Mattias Oom, Simon Genne

**Version:** 1.0

**Date:** 2020-10-23

# 1. Introduction
Jerk Evert is a topdown 2D game. The player is a simple geometrical shape that navigates a hostile, equally geometrical, world. In this world, the player is attacked by various enemies that shoot different kinds of projectiles at the player. The player itself has no weapon, but instead a set of abilities which (with some creativity) can be used to defeat the enemies. A few of these abilities are "reflection" (reflecting enemy projectiles), "shockwave" (pushing enemies away) and "dash" (making the player invulnerable and very fast for a short period).

The map contains different neutral elements, such as walls and moving walls, which can trap the player, but also be used as cover.

The goal of the player is to defeat all enemies. The game contains multiple levels, and by defeating one level, the player can progress to the next.

## 1.1. Definitions, acronyms, and abbreviations
- 2D - Two dimensional
- Topdown game - A game that is viewed from above.
- Projectile - Object fired by some entity towards some other entity. Can cause harm to the player or enemies.
- Obstacle - Neutral object placed on the level. Some obstacles may hinder the movement of the player, enemies, and projectiles in the game, while others may cause harm towards the player or the enemies.  
- Ability - Can be used by the player or enemies to impact the flow of the game in some way. This can, for example, be by adding a projectile to the game, or by directly impacting the surrounding entities in some way.
- Player - Entity controlled by the user of the Game. 
- Enemies - Opponents of the player. Will use their abilities to try to defeat the player. 
	   

# 2. Requirements

## 2.1. User Stories

**Story Identifier:** STK001 (FINISHED)

**Story Name:** Basic Player

**Time estimate:** 3 days

**Description:** As a user, I want a basic player character which I can maneuver using WASD or the arrow keys.

**Confirmation:** 

- **Functional:**
    
    - Can I move the player using keyboard keys?

- **Non-functional:**
    
    Responsiveness:
    - Does the player respond in a predictable way?

    Availability:
    - Does the player always respond?

    Visibility:
    - Is there a visual indication of the players movement?

---
**Story Identifier:** STK002 (FINISHED)

**Story Name:** Basic enemy

**Time estimate:** 2 days

**Description:** As a user, I want a enemy to compete against when playing a game, in order to have a fun gaming experience.

**Confirmation:**

- **Functional:**

    - Does the enemy respond to user actions?

    - Is the enemy a real danger?

- **Non-functional:** 

    Responsiveness:
    - Do the enemies respond in a reasonable way?

    Visibility:
    - Is the enemy actions visible to the user?

---
**Story Identifier:** STK003 (FINISHED)

**Story Name:** Basic map

**Time estimate:** 2 days

**Description:** As a user, I want a game to have a basic map in which I can navigate and explore.

**Confirmation:**

- **Functional:**

    - There is a map with four walls at the edges, which cannot be crossed.

- **Non-functional:**
    
    Responsiveness:
    - The walls of the map functions in a predictable way (cannot be crossed).
    
    Visibility:
    - The size and layout of the map is clearly visible to the user.

---
**Story Identifier:** STK004 (FINISHED)

**Story Name:** Ability reflect

**Time estimate:** 1 day

**Description:** As a player I want the ability to reflect projectiles, to protect myself and hurt hostile elements. If the ability has a cooldown, it would force me to use it strategically and introduce an interesting gameplay element.

**Confirmation:**

- **Functional:**

    - Can I click a key to activate this ability?
    - Is the ability unusable during the time of the cooldown?
    
- **Non-functional:**
    
    Gameplay:
    - Is there a clear benefit to using the ability?
    - Does the ability enable me to hurt hostile elements by reflecting their projectiles?
    
    Availability:
    - Is there a clear indiciation to when the ability is available?
---
**Story Identifier:** STK005 (FINISHED)

**Story Name:** Different levels

**Time estimate:** 5 days

**Dessription:** As a user, I want a variety of levels, so that the gameplay doesn't become to similair.

**Confirmation:**

- **Functional:**
    
    - Can I play on maps that have different structures/enemies/obstacles?

- **Non-functional:**
    
    - Is there an increasing difficulty level as the game progresses?
---
**Story Identifier:** STK006 (FINISHED)

**Story Name:** Level transition

**Time estimate:** 4 days

**Description:** As a player, I'd like to have a way to transition from one section of the game to another, to get a sense of progression.

**Confirmation:**

- **Functional:**

    - Can I transition to another part of the game, when one part is done?
    
- **Non-functional:**

    Interactivity:
    - ~~Is the transition interactive, for example, can I move to another part of the map to enter the next game section?~~
---
**Story Identifier:** STK007 (FINISHED)

**Story Name:** Start menu

**Time estimate:** 4 days

**Description:** As a user, I want a start menu so I can decide when to start the game.

**Confirmation**

- **Functional:**

    - Can I click a button to start the game?

    - Can I click a button to quit the game?

    - Is it possible to pause the game?

- **Non-functional:**

    Availability:
    - Is the menu always accessible when the game is started?
    
    Usability:
    - Is the menu intuitive and easy to use?

---
**Story Identifier:** STK008 (FINISHED)

**Story Name:** Ability dash

**Time estimate:** 1 day

**Description:** As a player, I'd like an ability to dash, to avoid dangerous elements. If the ability has a cooldown, it would make the gameplay more interesting, and force me to be more strategic and conservative with the use of this ability.

**Confirmation:**

- **Functional:**
    
    - Can I click a key to activate this ability?
    - Is the ability unusable during the time of the cooldown?

- **Non-functional:**
    
    Gameplay:
    -  Is there a clear benefit to using the ability?
    
    Availability:
    -  Is there a clear indiciation to when the ability is available?

---
**Story Identifier:** STK009 (FINISHED)

**Story Name:** Ability shockwave

**Time estimate:** 4 days

**Description:** As a player I need an ability to perform a shockwave so that I can push enemies away from me.

**Confirmation:**

- **Functional:**
    
    - Can I push a button and have the ability function reliably?
    - Are the enemies pushed away from me when I use the ability?

- **Non-functional:**
    
    Availability:
    - Can I see when my ability is available and when it's not?
    -  Can I easily figure out how my to access my ability?
    
    Usability:
    - Is my ability which easily accessible?
    - Can I easily figure out how my ability works?
    - Can I easily understand the range and limits of my ability?
---
**Story Identifier:** STK010 (FINISHED)

**Story Name:** Bullet enemy

**Time estimate:** 5 days

**Description:** As a player, I want to fight against enemies which can shoot bullets for me to avoid, to increase the difficulty of the game and make it more enjoyable to play.

**Confirmation:**

- **Functional:**
    
    - Can the enemy shoot bullets at me, the player?
    -  Is there a consequence to being hit by the bullets?

- **Non-functional:**
    
    Responsiveness:
    - Does the enemies aim at me?
    
    Difficulty:
    - Is the rate of fire and speed of the bullets reasonable?

    Visibility:
    - Are the bullets visible to me?
---
**Story Identifier:** STK011 (FINISHED)

**Story Name:** Missile enemy

**Time estimate:** 5 days

**Description:** As a player, I want to fight against enemies which can shoot homing missiles which target me as the player. This would increase the difficulty and propose a different game mechanic, making the game more fun to play.

**Confirmation:**

- **Functional:**
    
    - Can the enemy shoot missiles targeted at me, the player?
    - Is there a consequence to being hit by the missiles?

- **Non-functional:**
    
    Responsiveness:
    - Does the enemies aim at me?
    - Does the missiles react to my movements?
    
    Difficulty:
    - Are the missiles reasonably possilbe to avoid?
    
    Visibility:
    - Are the missiles visible to me?
----
**Story Identifier:** STK012 (FINISHED)

**Story Name:** Obstacles

**Time estimate:** 2 days

**Description:** As a player, I'd like a set of obstacles to navigate around, to make the gameplay more varied and strategic.

**Confirmation:**

- **Functional:**
    
    - Can I collide with obstacles?
    -  Can other enteties collide with obstacles?

- **Non-functional:**
    
    Gameplay:
    - Can I use the obstacles to avoid dangerous elements?
   
    Visibility:
    - Can I see and navigate around the obstacles?

---    
**Story Identifier:** STK013 (FINISHED)

**Story Name:** Spikes

**Time estimate:** 2 days

**Description:** As a player, I want there to exist "spikes", or dangerous, static game elements, for me to avoid. This would create more interesting gameplay, especially since also enemies could be hurt by spikes.

**Confirmation:**

- **Functional:**
    
    - Can I, and enemies, collide with spikes?
    - Does the spikes hurt me, or the enemies, when hit?
    
- **Non-functional:**
    
    Gameplay:
    - Can I use the spikes to my advantage?
    
    Visibility:
    - Can I see the spikes, to know how to navigate around them?
---
**Story Identifier:** STK014 (IN PROGRESS)

**Story Name:** Scoring system

**Time estimate:** 6 days

**Description:** As a user, I want a way to keep track of my score, so that I get motivated to improve at the game.

**Confirmation:**

- **Functional:**
    
    - ~~Can I get points by performing certain actions throughout the game?~~
    - Can I see my score while playing?
    - Can I keep track of how many points I've had in previous playthroughs?

- **Non-functional:**
    
    - ~~Does the difficulty of an action affect how many points that action is worth?~~

---
**Story Identifier:** STK015 (FINISHED)

**Story Name:** Music

**Time estimate:** 14 days

**Description:** As a user, I want background music, so that I get a more immersive gameplay experience.

**Confirmation:**

- **Functional:**
    
    - Can I hear music as I am playing the game?

---
**Story Identifier:** STK016 (FINISHED)

**Story Name:** Sound effects

**Time estimate:** 14 days

**Desscription:** As a user I want sound effects so that I get a more immersive gameplay experience.

**Confirmation:**

- **Functional:**
    
    - Are there sound effects that reflect the players actions?
    - ~~Are there sound effects that reflect the actions of the enemies?~~

- **Non-functional:**
    
    - Do sound effects indicate which abilities have been used?
    
---
**Story Identifier:** STK017

**Story Name:** Slowmotion

**Time estimate:** 4 days


**Description:** As a player, I need an ability that lets me slow the environment around me so that I can escape dangers more easily.

**Confirmation**

List all acceptance criteria; you should be able to test/confirm these.

- **Functional**

* Can I push a button and have the ability function reliably?
* Is everything around me slowed down when using the ability?

- **Non-functional**

    Gameplay
    * Is there a clear benefit to using the ability?

    Availability
    * Can I see when my ability is available and when it's not?
    * Can I easily figure out how my to access my ability?

    Usability
    * Is my ability easily accessible?
    * Can I easily figure out how my ability works? 
---
**Story Identifier:** STK018 (FINISHED)

**Story Name:** Moving obstacles

**Time estimate:** 1 days

**Description:** As a player, I want there to exist moving game elements which would make for a more dynamic gameplay.

**Confirmation**

- **Functional**

* Do obstacles move?
* Do obtacles stop entities only when they are in that exact position?

- **Non-functional**

    Gameplay
    * Can I use the moving obstacles to my advantage?
    
    Visibility
    * Can I understand how the obstacle moves?
---
## 2.2. Definition of done
For a user story to be considered to be done, the following criteria have to be fullfilled:

- All acceptence criteria of that user story are satisfied.

- All tests have been passed.

- All visual features of the user story have been added to the GUI. 

## 2.3. User Interface


**GUI sketch**

<img src=https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/gui-sketch.jpg height=600>

### Image 1
A player (circle) with a small ring around. The ring indicates the state of one of the player's abilities.

Three squares represent enemies opposing the players. Each enemy is shooting some kind of projectile. 

The small dots represent simple bullets, while the arrows represent some kind of homing missiles.

The rectangle represent a wall, an obstacle for both players and enemies.

The spikes represent a dangerous element which can hurt both players and enemies.


### Image 2
This image represent when the player moves from one level to the next. When the player has completed their objective, a gate is opened at the center of the map. This gate can be used to enter the next level.

### Start screen
<img src=https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/gamemenu.jpg height=600>

Displayed when the game is launched. 



# 3. Diagrams


## 3.1 Domain Model
**UML of domain model**

<img src=https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/domain-model.png width=100%>



## 3.1.1 Class responsibilities

(Explanation of responsibilities of classes in diagram)

## 3.2 Design Model
**UML of design model**

<img src=https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/design-model.png width=100%>



