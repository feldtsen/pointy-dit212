# JERK EVERT (Group Pointy)
Jerk Evert is a topdown 2D game. The player is a simple geometrical shape that navigates a hostile, equally geometrical, world. In this world, the player is attacked by various enemies that shoot different kinds of projectiles at the player. The player itself has no weapon, but instead a set of abilities which (with some creativity) can be used to defeat the enemies. A few of these abilities are "reflection" (reflecting enemy projectiles), "shockwave" (pushing enemies away) and "dash" (making the player invulnerable and very fast for a short period).

The map contains different neutral elements, such as walls and moving walls, which can trap the player, but also be used as cover.

The goal of the player is to defeat all enemies. The game contains multiple levels, and by defeating one level, the player can progress to the next.

# Dependencies
* Apache Maven 3.6.3 
* java-11 (java-11-openjdk) 
* javafx-15 (automatically imported by maven)
* junit-4.11 (automatically imported by maven)
* gson-2.8.5 

# Project setup
## Intellij 
1. Clone this repository
2. Open the *project* folder in intellij idea
3. traverse the project view and mark the *java* package as Sources root, *resources* as Resources root and *tests* as Test sources root (right click -> Mark Directory as)
4. pick java version 11 (File -> Project Structure -> Project Settings -> Project)
5. Press *Add configuration* and chose *Template*, then *Application*. Under *Main class* write *game.App* and under *Working directory* input the path to the project folder. In the *Use classpath of module* dropdown, pick *project*. Do not forget to actually create the configuration (the top left plus sign).
6. Build and run the application

## Eclipse 
1. Clone this repository
2. Move the *project* folder into one of your eclipse workspaces (or create a new one).
3. Open the *project* folder in eclipse.
4. Build and run the application.

# Links
Trello (SCRUM board):
* https://trello.com/invite/b/DJp7eGR9/bf5deeea868862ddfec292dd16bb8952/pointy-board

Slack (internal communication):
* https://join.slack.com/t/pointy-dit212/shared_invite/zt-h621a081-F2Fco67GP6jt~zsK6zGgew

Design model (live document, continuously updated):
https://app.lucidchart.com/lucidchart/4a9f77ed-1032-4afe-aa50-b162c2b9fbcb/edit?page=sKHtDXfCDpsw#?folder_id=home&browser=icon

