# My Personal Project
### About
---
This project will be a TFT game board planner and tracker. TFT is a strategy turn-based online video game
that relies heavily on managment and cohesion. This java application will allow users to make and save
new "boards" to reference. In a board, users can place up to twelve champions in which the application
will save. For each champion, the application will also save the desired items users may want to attactch.
Users can save and name multiple boards and track a history of how each board does in matches. The end goal
is to be able to attatch new sets (new champtions), when an update rolls out in TFT.

This project is of interest to me because TFT is one of the few games ive been able to play while away from 
home at university. The strategy is fun, and I thought making an acessible playground for users to test and 
learn would be interesting.


 ### User Stories
 ---

 _Phase 1:_

 User Request 1: As a user, I want to have the ability to create multiple boards and have access to them.

 User Request 2: As a user, I want to add and remove champions (later additional accessories) onto specific locations (hexes) on my board.

 User Request 3: As a user, I want to view my list of boards. Consequently I want to view my list of Champions within each board.

 User Request 4: As a user, I want to log how many wins my board has.

 User Request 5: As a user, I want to view the the available champions in the current tft set.

 User Request 6: As a user, I want to see my board and the champions on them

_Phase 2:_

User Request 7: As a user, I want boards to autosave, but have the option to delete a board.

User Request 8: As a user, I want to have the option to load and work on previous boards from application startup.

_Phase 3:_

User Request 9: I want to see the champions stats

User Request 10: I want to be able to swap champion locations.


### Instructions for End User
1. To load up previous saves, click yes on the initial prompt pop up.

2. After dealing with the loading prompt. Users may use the add board button to create a new board.
    - Give the board a name and confirm.
3. After dealing with the loading prompt. Users may want to access previous boards.
    - Click on any board button to enter the board menu

3. Once in the board menu interface. 
    - To add a champion onto the board, click a champion box in options below to which `empty` hexagons will be highlighted.
    - Click and empty hexagon to add a champion there.
    - To move an existing champion on the board, click the hexagon with the champion (board highlights) then click another hexagon to move it.
    - To swap two existing champions on the board, click one hexagon (board highlights) and another (with champion) and they will swap.
    - To delete any champion on the board, right click.
    - To return to the main menu click the `main menu` button, or exit the window.
4. All boards auto save when exiting the window. To delete a board click `delete` in the board GUI.
5. All visual components (hexagonal board) were drawn in the class `Hex` as polygons.
    





 

 