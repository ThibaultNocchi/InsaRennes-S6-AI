# Sliding Puzzle Oracle Maker and Solver - Java
This tool can be used to generate all possible sliding puzzles (oracle) from a given set with the number of moves to reach them.  
With a given oracle, it can solve the path to its solution from any possible set present in the oracle.

The oracle is mainly used to get all possible sets from the solution of the sliding puzzle game, to then recreate the path to it from any set of puzzle.

![Sliding Puzzle 3x3](https://upload.wikimedia.org/wikipedia/commons/a/a5/Batgirl.gif)

Here you can see an example of what a sliding puzzle is, with a 3x3 configuration. You have cells which are shuffled, and an empty one. You need to remake the pattern by only moving cells into the empty one (or by moving the empty cell to another position).
## Getting started
Download the sources and build them with Java 8 (at least that's what I used, but nothing too fancy was used so any recent version should work). In the main class, you can customize the filepath to the oracle, the size of the sliding puzzle and the sliding puzzle to test against the oracle.

## How does it work
A SlidingPuzzle implementation class is used to represent a puzzle and interact with it (moving the empty cell for example).
The OracleMaker class generates all possible moves and their distance from a given puzzle.
The OracleSolver class loads an oracle and gets the path to the solution from a given puzzle.

To generate the oracle and be sure that every solution with its distance is the shortest from the root, we explore the differents moves with a breadth first search algorithm.
![Breadth First Search](https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Breadth-first-tree.svg/2560px-Breadth-first-tree.svg.png)

Each node represents a puzzle, and its children are the puzzles we can reach by moving a single cell in it.
We explore and generate the solutions for the first node, then the second, the third, etc. With this algorithm, and by adding only once a given puzzle to the oracle, we can be sure to converge and have the shortest distance to a given puzzle from the root.

## Main functions and classes

```java
SlidingPuzzleInt spDefault = new SlidingPuzzleInt(2); // Creates a new default 3x3 puzzle. Defaults are the solution of the game for the given size.
SlidingPuzzleInt spToSolve = new SlidingPuzzleInt("6 0 3 2 1"); // Creates a new puzzle with a distance of 6 from its root (only useful if you need it, but the oracle uses it intensely, and it needs to be here) and with 0 (empty cell) 3 2 1 as values from top left to bottom right.

OracleMaker om = new OracleMaker(spDefault); // Setup an oracle maker with a default 2x2 puzzle as root.
om.solve("/path/to/oracle.txt"); // Saves the previously generated oracle to the file.

OracleSolver os = new OracleSolver("/path/to/oracle.txt"); // Setup an oracle solver with an oracle file.
ArrayList<SlidingPuzzle> solutions = os.solve(spToSolve); // List of puzzles to go through to reach the solution of the oracle.
```

## Documentation
You can find the Javadoc [here](https://tiwenty.github.io/InsaRennes-S6-AI/SlidingPuzzle/doc/).