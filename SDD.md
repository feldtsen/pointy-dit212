# System Design Document for...

**Authors:** Anton Hildingsson, Erik Magnusson, Joachim Ørfeldt Pedersen, Mattias Oom, Simon Genne

**Version:**

**Date:**

## 1. Introduction

Pointy [temporary name] is a topdown 2D game. The player is a simple geometrical shape which navigates a hostile, equally geometrical, world. In this world, the player is attacked by various hostiles which shoot different kinds of projectiles at the player. The player itself has no weapon,but a set of abilities which (with some creativity) can be used to defeat the enemies. Some of these abilities are reflection (reflecting enemy projectiles), shockwave (pushing enemies away), time manipulation (slowing down time around the player) and dash (making the player invulnerable and very fast for a short period of time).

The map contains different neutral elements, such as walls, spikes and traps, which can both be dangerous for the player, or cleverly used to aid the player in their mission.

The goal of the player is to defeat all the elements, reach the map exit, or perform a specific task.

### 1.1. Definitions, acronyms, abbreviations

>(Definitions etc. probably same as in RAD)

- 2D - Two dimensional
- Topdown game - A game that is viewed from above.

## 2. System architecture
>The most overall, top level description of your application. If your application uses
multiple components (such as servers, databases, etc.), describe their responsibilities
here and show how they are dependent on each other and how they communicate
(which protocols etc.)
You will to describe the ‘flow’ of the application at a high level. What happens if
the application is started (and later stopped) and what the normal flow of operation
is. Relate this to the different components (if any) in your application.

## 3. System Design

>Draw an UML package diagram for the top level for all components that you have
identified above (which can be just one if you develop a standalone application). Describe the interfaces and dependencies between the packages. Describe how you have
implemented the MVC design pattern.

>Create an UML class diagram for every package.

>One of the packages will contain the model of your application. This will be the design model of your application,
describe in detail the relation between your domain model and your design model.
There should be a clear and logical relation between the two. Make sure that these
models stay in ‘sync’ during the development of your application.

<img src=https://github.com/feldtsen/pointy-dit212/blob/master/models-and-sketches/design-model.png width=100%>


>Describe which (if any) design patterns you have used.
The above describes the static design of your application. It may sometimes be
necessary to describe the dynamic design of your application as well. You can use an
UML sequence diagram to show the different parts of your application communicate
an in what order

## 4. Persistent data management

The application currently makes use of JSON to handle level data. The level files contain JSON objects pertaining
to information of the level and its entities, i.e. their type (player, enemy, obstacle), variants (e.g. type of enemy) 
as well as instance variables not handled by the factory. Levels are loaded through the static class "LevelLoader"
which parses the JSON file corresponding to a certain level ID, creates an object of type "Level" and returns this
object to be used by the "Game" class. Each level is contained within a separate file and is only loaded when needed
to save memory resources. The parsing is done using the GSON library. 

Future possible functionality includes storing and parsing level data as ascii to enable easier level development, 
saving player progress made up to that point, as well as keeping top scores.  

## 5. Quality

> - Describe how you test your application  and where to find these tests. If applicable, give a link to your continuous integration.

The application is tested using unit tests with the framework JUnit. These tests can be found under: project/src/main/test.

> - List all known issues.
> -  Run analytical tools on your software and show the results. Use for example:
>    - Dependencies: STAN or similar.
>     - Quality tool reports, like PMD.

> NOTE: Each Java, XML, etc. file should have a header comment: Author, responsibility, used by ..., uses ..., etc.



### 5.1. Access control and security

> If you applications has some kind of access control, for example a login, of has different user roles (admin, standard, etc.), then explain how you application manages
this

## 6. References
- JavaFX - https://openjfx.io/
- Maven - https://maven.apache.org/
- Lucidchart - https://www.lucidchart.com/
- JUnit - https://junit.org/
- Git - https://git-scm.com/
- Trello - https://www.trello.com/
