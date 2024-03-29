# CS151 Group Project "BattleShip Game"
![alt text](img/gameplay_screenshot.png)

## About
Classic battleship game. [rules for Battleship Game](https://en.wikipedia.org/wiki/Battleship_(game)) with Player VS Artificial intelligence
## Instruction
* For Windows, Mac or Linux users to play Battleship, just go to [this link](https://github.com/Lee-Taeho/BattleShip/blob/fixed2/Battleship.jar) and click download, then run the executable "Battleship.jar".

## Design Environments
* IntelliJ IDEA
* Java Version 11

## Features implemented in this project:
* Player Manual Ship Placing
* Player and AI Random Ship Placing
* Basic Graphics and drag and drop placing system
* Basic AI firing strategy

## Designed the project using:
* Used Object Oriented approach, added entities such as Player, Board, Fleet of Ships, Score.
* Implemented Model-View-ViewModel design pattern to separate parts between the View, the Model and the Logic.
* Applied MVC patterns and multiple Object-Oriented design methods such as Pipeline, Adapter. 

## Data Structures:
* The Board is a two-dimensional array which elements can be accessed by their x and y coordinates.
* Both AI and player have their own arraylist of ships.
* The ship model has its own arraylist of positions, and has its own health as parameter.

## Improvements to be done:
* Need a smarter AI firing strategy. Current development is just a random fire.
* Add a one time used only skill for both player, which called airforce recon to reveal random four unseen grids.
* Debug the problem where for Windows user the grid line is not showing.


## Contributor
* [Taeho Lee](https://github.com/Lee-Taeho)
* [Jiajun Dai](https://github.com/DJJ547)
* [Nikola Klier](https://github.com/nklier38)
* [Kevin Lopez](https://github.com/KevinLopezSJSU)
