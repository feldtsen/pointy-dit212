# Requirements and Analysis Document for ... 

**Authors:** Anton Hildingsson, Erik Magnusson, Joachim Ørfeldt Pedersen, Mattias Oom, Simon Genne

**Version:** 

**Date:** 

# 1. Introduction
Pointy [temporary name] is a topdown 2D game. The player is a simple geometrical shape which navigates a hostile, equally geometrical, world. In this world, the player is attacked by various hostiles which shoot different kinds of projectiles at the player. The player itself has no weapon,but a set of abilities which (with some creativity) can be used to defeat the enemies. Some of these abilities are reflection (reflecting enemy projectiles), chockwave (pushing enemies away), time manipulation (slowing down time around the player) and dash (making the player invulnerable and very fast for a short period of time).

The map contains different neutral elements, such as walls, spikes and traps, which can both be dangerous for the player, or cleverly used to aid the player in their mission.

The goal of the player is to defeat all the elements, reach the map exit, or perform a specific task.

## 1.1. Definitions, acronyms, and abbreviations
- 2D - Two dimensional
- Topdown game - A game that is viewed from above.


# 2. Requirements

## 2.1. User Stories


**Story Identifier:** STK001

**Story Name:** Basic Player

**Description:** As a user, I want a basic player character which I can maneuver using WASD or the arrow keys.

**Confirmation:** 

- **Functional:**
    
    - Can I move the player using keyboard keys?

- **Non-functional:**

    - Does the player respond in a predictable way?

    - Does the player always respond?

    - Is there a visual indication of the players movement?

---
**Story Identifier:** STK002

**Story Name:** Basic enemy

**Description:** As a user, I want a enemy to compete against when playing a game, in order to have a fun gaming experience.

**Confirmation:**

- **Functional:**

    - Does the enemy respond to user actions?

    - Is the enemy a real danger?

- **Non-functional:** 

    - Does the enemies respond in a reasonable way?

    - Is the enemy actions visible to the user?

---
**Story Identifier:** STK003

**Story Name:** Basic map

**Description:** As a user, I want a game to have a basic map in which I can navigate and explore.

**Confirmation:**

- **Functional:**

    - There is a map with four walls at the edges, which cannot be crossed.

- **Non-functional:**

    - The walls of the map functions in a predictable way (cannot be crossed).

    - The size and layout of the map is clearly visible to the user.

---
**Story Identifier:** STK004

**Story Name:** Start menu

**Description:** As a user, I want a start menu so I can decide when to start the game.

**Confirmation**

- **Functional:**
    - Can I click a button to start the game?

    - Can I click a button to quit the game?

    - Is it possible to pause the game?

-- **Non-functional**
    - Is the menu always accessible when the game is started?

    - Is the menu intuitive and easy to use?

---
## 2.2. Definition of done
For a user story to be considered to be done, the following criteria have to be fullfilled:

- All acceptence crirteria of that user story are satisfied.

- All tests have been passed.

## 2.3. User Interface

(Include sketches, drawings and explanations of the application’s user interface. Describe the navigation between the different views.)
**GUI sketch**

<img src=https://github.com/feldtsen/pointy-dit212/blob/master/domain-model/gui-sketch.jpg height=600>

### Image 1
A player (circle) with a small ring around. The ring indicates the state of one of the player's abilities.

Three squares represent enemies opposing the players. Each enemy is shooting some kind of projectile. 

The small dots represent simple bullets, while the arrows represent some kind of homing missiles.

The rectangle represent a wall, an obstacle for both players and enemies.

The spikes represent a dangerous element which can hurt both players and enemies.

### Image 2
This image represent when the player moves from one level to the next. When the player has completed their objective, a gate is opened at the center of the map. This gate can be used to enter the next level.



# 3. Domain Model

(Give a high level view overview of the application using a UML diagram.)

**UML of domain model**

<img src=https://github.com/feldtsen/pointy-dit212/blob/master/domain-model/domain-model-diagram.png width=100%>


## 3.1. Class responsibilities

(Explanation of responsibilities of classes in diagram)

# 4. References

(List all references to external tools, platforms, libraries, papers, etc.)

- JavaFX - https://openjfx.io/
- Maven - https://maven.apache.org/
- Lucidchart - https://www.lucidchart.com/
- JUnit - https://junit.org/
- Git - https://git-scm.com/


