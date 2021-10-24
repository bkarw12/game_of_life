# Conway's Game of Life

This project implements [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) using [this guide](https://robertheaton.com/2018/07/20/project-2-game-of-life/).

### Compiling, testing and running the program
This program is easiest to build and run using [Gradle](https://gradle.org/). It was originally developed using the 7.2 version.

- To build and test the program:
`./gradlew build` in the main project directory.
- To run the program:
`./gradlew run` or `java -jar build/libs/game_of_life-1.0.jar`.

The program asks for input: Game's width and height parameters.
Then a new terminal windows is opened with Game of Life running.
