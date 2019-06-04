# Sliding Puzzle Solver - Python
This tool can be used to solve a given square sliding puzzle, with an A* algorithm and a Manhattan heuristic.

![Sliding Puzzle 3x3](https://upload.wikimedia.org/wikipedia/commons/a/a5/Batgirl.gif)

Here you can see an example of what a sliding puzzle is, with a 3x3 configuration. You have cells which are shuffled, and an empty one. You need to remake the pattern by only moving cells into the empty one (or by moving the empty cell to another position).
## Getting started
You can use this script with Python3. You can print the help with `python3 SlidingPuzzle.py -h`.  
The only way to interact with it in the CLI is to give it the values of a puzzle, from top left to bottom right. A 0 value represents the empty space, and integers from 1 to n the solved position of the puzzle, top left to bottom right.

Ex.: `python3 SlidingPuzzle.py 7 6 4 2 8 1 3 5 0` will solve this puzzle:  
|  |  |  |
|:-:|:-:|:-:|
| 7 | 6 | 4 |
| 2 | 8 | 1 |
| 3 | 5 | 0 |

## How does it work
A SlidingPuzzle implementation class is used to represent a puzzle and interact with it (moving the empty cell for example).  
The SolvingTree class stores differents nodes of the solving tree, and moves across it to find the solution.  
A PuzzleNode class stores a SlidingPuzzle, various informations related to the node and relevant to the solving tree. Such as level of the node in the tree, optimistic cost of nodes below it (current real cost of the node + its estimated heuristic).

To find the solution, [A*](https://en.wikipedia.org/wiki/A*_search_algorithm) and [Manhattan heuristic](https://en.wikipedia.org/wiki/Taxicab_geometry) are used.

A tree is made this way: each node represents a puzzle, and its children are the puzzles we can reach by moving a single cell in it.  
To summarize it, the solver compares the total estimated distance of each nodes and develops those which are closer to the solution.  
The total estimated distance of a node is the current real cost (the number of moves required from the root to this state) + the heuristic (an **always optimistic** value of the cost to the solution from this state). This way, the number of moves (real cost) of a solution beneath the current node will always be greater than the estimation we did for the current node.  
So when we found a solution with a cost of X, what we can do when browsing the tree is when we find a node with an estimated distance of nodes below it greater than X, all solutions beneath it will cost greater than X. This way, we drop this branch of the tree and go on.

## Main functions and classes

```python
puzzle = [[7, 6, 4], [2, 8, 1], [3 5 0]] # Creates a 2D list representing the root puzzle.

""" You can create a SlidingPuzzle with one of these 3 ways: """
sp = SlidingPuzzle(puzzle = puzzle) # Creates the SlidingPuzzle to solve.
sp = SlidingPuzzle(sideSize = 3) # Creates a solved 3x3 puzzle.
sp = SlidingPuzzle(slidingPuzzle = previousSlidingPuzzle) # Copies another SlidingPuzzle.
""" End of the creation of a sliding puzzle. """

tree = SolvingTree(sp) # Creates the SolvingTree with our puzzle as a root.
tree.solve() # Solves the tree, it may not end as it may loop if no solution exists to our puzzle.
solution = tree.getSolutionPath() # Retrieves the solution, it is a list of puzzles with each being a different move.

for puzzleStep in solution: # Prints the solution.
    print(puzzleStep)

print("Number of moves required to solve: " + str(len(solution) - 1)) # Prints the number of moves required to reach the solution.
```

## Documentation
The code is documented with docstrings. Be free to ask me any question you could have about it.
