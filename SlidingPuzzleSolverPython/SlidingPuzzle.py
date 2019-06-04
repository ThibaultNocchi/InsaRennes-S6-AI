import copy
import heapq
import typing
import argparse
import math


class SlidingPuzzle:
    """
    Is used to manipulate square sliding puzzle.

    It uses a Manhattan heuristic to solve the puzzle.
    """

    def __init__(self, *args, **kwargs):
        """
        Is used to build a sliding puzzle.

        Three mutually exclusive arguments can be given to generate a sliding puzzle:
        "sideSize" - Given an int for the side size of the puzzle, it'll generate a solved sliding puzzle with this size.
        "slidingPuzzle" - Copy an existing SlidingPuzzle into the new one.
        "puzzle" - Takes a 2D list of integers with 0 being the empty space. list[0] is the top left value, list[n-1] is the bottom right.
        
        :raises ValueError: Error raised when given puzzle isn't a square or when the given values don't match up to make a puzzle.
        """

        if ("sideSize" in kwargs): # Creating a solved puzzle.
            self.__sideSize: int = kwargs["sideSize"]
            self.__puzzle: typing.List[typing.List[int]] = [[x * kwargs["sideSize"] + y
                              + 1 for y in range(kwargs["sideSize"])] for x in range(kwargs["sideSize"])]
            self.__puzzle[kwargs["sideSize"] - 1][kwargs["sideSize"] - 1] = 0

        elif ("slidingPuzzle" in kwargs): # Copying a given SlidingPuzzle.
            size: int = kwargs["slidingPuzzle"].getSideSize()
            self.__sideSize: int = size
            self.__puzzle: typing.List[typing.List[int]] = [
                [0 for y in range(size)] for x in range(size)]
            for x in range(size):
                for y in range(size):
                    self.__puzzle[x][y] = kwargs["slidingPuzzle"].getPuzzle()[x][y]

        elif("puzzle" in kwargs): # Building a sliding puzzle from a 2D list.
            listValues: typing.List[int] = []
            self.__puzzle: typing.List[typing.List[int]] = [[0 for y in range(len(kwargs["puzzle"]))]
                             for x in range(len(kwargs["puzzle"]))]
            self.__sideSize: int = len(kwargs["puzzle"])
            for x in range(len(kwargs["puzzle"])):
                if(len(kwargs["puzzle"][x]) != self.getSideSize()):
                    raise ValueError("Puzzle not a square.")
                for y in range(len(kwargs["puzzle"][x])):
                    if(not(kwargs["puzzle"][x][y] in listValues or kwargs["puzzle"][x][y] < 0 or kwargs["puzzle"][x][y] >= self.getSideSize() * self.getSideSize())):
                        self.__puzzle[x][y] = kwargs["puzzle"][x][y]
                        listValues.append(kwargs["puzzle"][x][y])
                    else:
                        raise ValueError("Puzzle given isn't an effective puzzle.")

        else:
            raise ValueError("Argument missing.")

        self.updateManhattan() # Updating the heuristic.

    def getPuzzle(self) -> typing.List[typing.List[int]]:
        """
        Returns the 2D list of the puzzle.
        
        :return: 2D list representing the puzzle.
        :rtype: List[List[int]]
        """
        return self.__puzzle

    def getSideSize(self) -> int:
        """
        Returns the side size of the squared puzzle.
        
        :return: Side size.
        :rtype: int
        """
        return self.__sideSize

    def getEmptyCell(self) -> typing.Tuple[int, int]:
        """
        Tuple representing the X and Y position of the empty cell in the puzzle.
        
        :return: X and Y of the empty cell.
        :rtype: Tuple[int, int]
        """
        for x in range(self.getSideSize()):
            for y in range(self.getSideSize()):
                if(self.__puzzle[x][y] == 0):
                    return (x, y)

    def moveUp(self, edit: bool = True) -> typing.List[typing.List[int]]:
        """
        Moves up the position of the empty cell, effectively moving down the cell above it.
        
        :param edit: Whether to edit the current 2D list puzzle, defaults to True
        :type edit: bool, optional
        :raises ValueError: When the empty cell can't be moved.
        :return: 2D list of the newly moved puzzle.
        :rtype: List[List[int]]
        """
        newPuzzle: typing.List[typing.List[int]] = copy.deepcopy(self.__puzzle)
        emptyCell: typing.Tuple[int, int] = self.getEmptyCell()
        if(emptyCell[0] > 0):
            newPuzzle[emptyCell[0] - 1][emptyCell[1]], newPuzzle[emptyCell[0]][emptyCell[1]
                                                                               ] = newPuzzle[emptyCell[0]][emptyCell[1]], newPuzzle[emptyCell[0] - 1][emptyCell[1]]
            if(edit):
                self.__puzzle: typing.List[typing.List[int]] = newPuzzle
                self.updateManhattan()
            return newPuzzle
        else:
            raise ValueError("Can't move up.")

    def moveDown(self, edit: bool = True) -> typing.List[typing.List[int]]:
        """
        Moves down the position of the empty cell, effectively moving up the cell below it.
        
        :param edit: Whether to edit the current 2D list puzzle, defaults to True
        :type edit: bool, optional
        :raises ValueError: When the empty cell can't be moved.
        :return: 2D list of the newly moved puzzle.
        :rtype: List[List[int]]
        """
        newPuzzle: typing.List[typing.List[int]] = copy.deepcopy(self.__puzzle)
        emptyCell: typing.Tuple[int, int] = self.getEmptyCell()
        if(emptyCell[0] < self.getSideSize() - 1):
            newPuzzle[emptyCell[0] + 1][emptyCell[1]], newPuzzle[emptyCell[0]][emptyCell[1]
                                                                               ] = newPuzzle[emptyCell[0]][emptyCell[1]], newPuzzle[emptyCell[0] + 1][emptyCell[1]]
            if(edit):
                self.__puzzle: typing.List[typing.List[int]] = newPuzzle
                self.updateManhattan()
            return newPuzzle
        else:
            raise ValueError("Can't move down.")

    def moveLeft(self, edit: bool = True) -> typing.List[typing.List[int]]:
        """
        Moves left the position of the empty cell, effectively moving right the cell at its left.
        
        :param edit: Whether to edit the current 2D list puzzle, defaults to True
        :type edit: bool, optional
        :raises ValueError: When the empty cell can't be moved.
        :return: 2D list of the newly moved puzzle.
        :rtype: List[List[int]]
        """
        newPuzzle: typing.List[typing.List[int]] = copy.deepcopy(self.__puzzle)
        emptyCell: typing.Tuple[int, int] = self.getEmptyCell()
        if(emptyCell[1] > 0):
            newPuzzle[emptyCell[0]][emptyCell[1] - 1], newPuzzle[emptyCell[0]][emptyCell[1]
                                                                               ] = newPuzzle[emptyCell[0]][emptyCell[1]], newPuzzle[emptyCell[0]][emptyCell[1] - 1]
            if(edit):
                self.__puzzle: typing.List[typing.List[int]] = newPuzzle
                self.updateManhattan()
            return newPuzzle
        else:
            raise ValueError("Can't move left.")

    def moveRight(self, edit: bool = True) -> typing.List[typing.List[int]]:
        """
        Moves right the position of the empty cell, effectively moving left the cell at its right.
        
        :param edit: Whether to edit the current 2D list puzzle, defaults to True
        :type edit: bool, optional
        :raises ValueError: When the empty cell can't be moved.
        :return: 2D list of the newly moved puzzle.
        :rtype: List[List[int]]
        """
        newPuzzle: typing.List[typing.List[int]] = copy.deepcopy(self.__puzzle)
        emptyCell: typing.Tuple[int, int] = self.getEmptyCell()
        if(emptyCell[1] < self.getSideSize() - 1):
            newPuzzle[emptyCell[0]][emptyCell[1] + 1], newPuzzle[emptyCell[0]][emptyCell[1]
                                                                               ] = newPuzzle[emptyCell[0]][emptyCell[1]], newPuzzle[emptyCell[0]][emptyCell[1] + 1]
            if(edit):
                self.__puzzle: typing.List[typing.List[int]] = newPuzzle
                self.updateManhattan()
            return newPuzzle
        else:
            raise ValueError("Can't move right.")

    def isSolution(self) -> bool:
        """
        Decides if the current puzzle is solved. It checks every value to see if they are at their place.
        
        :return: Is the puzzle solved.
        :rtype: bool
        """
        for x in range(self.getSideSize()):
            for y in range(self.getSideSize()):
                if(self.__puzzle[x][y] != x * self.getSideSize() + y + 1):
                    if(not(x == self.getSideSize() - 1 and y == self.getSideSize() - 1 and self.__puzzle[x][y] == 0)):
                        return False
        return True

    def getManhattan(self) -> int:
        """
        Getter of the current Manhattan heuristic. It doesn't update it.
        
        :return: Manhattan heuristic value.
        :rtype: int
        """
        return self.__manhattan

    def updateManhattan(self):
        """
        Updates the Manhattan heuristic from the current 2D puzzle stored.
        """
        distance: int = 0
        for x in range(self.getSideSize()):
            for y in range(self.getSideSize()):
                if(self.__puzzle[x][y] != x * self.getSideSize() + y + 1 and self.__puzzle[x][y] != 0):
                    value: int = self.__puzzle[x][y]
                    supposedX: int = (value - 1) // self.getSideSize()
                    supposedY: int = value - self.getSideSize() * supposedX - 1
                    distance += abs(supposedX - x)
                    distance += abs(supposedY - y)
        self.__manhattan: int = distance

    def __repr__(self):
        """
        Gives a representation of sliding puzzle in 2D, with line returns.
        
        :return: String representing the puzzle, meant to be displayed.
        :rtype: String
        """
        pStr: str = ""
        for x in self.__puzzle:
            for y in x:
                pStr += str(y) + " "
            pStr += '\n'
        return pStr


class PuzzleNode:
    """
    Is used to represent a node of the solving tree. It has a parent, a level in a tree, a total distance (its heuristic + the real current cost) and a SlidingPuzzle assigned.
    The SlidingPuzzle associated to it isn't supposed to be edited, unless you also edit all the attributes of the PuzzleNode, like the total distance, parent, etc.
    """

    def __init__(self, slidingPuzzle: SlidingPuzzle, parent: "PuzzleNode" = None):
        """
        Initializes the node.
        
        :param slidingPuzzle: SlidingPuzzle object to assign to the node.
        :type slidingPuzzle: SlidingPuzzle
        :param parent: Parent of this PuzzleNode, can be None if it hasn't one, defaults to None
        :type parent: PuzzleNode, optional
        """
        self.__slidingPuzzle: SlidingPuzzle = slidingPuzzle
        self.__parent: PuzzleNode = parent
        if(self.__parent is not None):
            self.__level = self.__parent.getLevel() + 1
        else:
            self.__level = 0
        self.__totalDistance = self.__level + self.__slidingPuzzle.getManhattan()

    def developNode(self) -> typing.List[SlidingPuzzle]:
        """
        Calculates and returns a list of SlidingPuzzle representing the possible moves from this node.
        
        :return: List of future SlidingPuzzle from this node state.
        :rtype: List[SlidingPuzzle]
        """

        newNodes: typing.List[SlidingPuzzle] = []

        try:
            left = self.__slidingPuzzle.moveLeft(False)
            leftPuzzle = SlidingPuzzle(puzzle=left)
            newNodes.append(PuzzleNode(leftPuzzle, self))
        except ValueError:
            pass

        try:
            right = self.__slidingPuzzle.moveRight(False)
            rightPuzzle = SlidingPuzzle(puzzle=right)
            newNodes.append(PuzzleNode(rightPuzzle, self))
        except ValueError:
            pass

        try:
            up = self.__slidingPuzzle.moveUp(False)
            upPuzzle = SlidingPuzzle(puzzle=up)
            newNodes.append(PuzzleNode(upPuzzle, self))
        except ValueError:
            pass

        try:
            down = self.__slidingPuzzle.moveDown(False)
            downPuzzle = SlidingPuzzle(puzzle=down)
            newNodes.append(PuzzleNode(downPuzzle, self))
        except ValueError:
            pass

        return newNodes

    def getLevel(self) -> int:
        return self.__level

    def getTotalDistance(self) -> int:
        return self.__totalDistance

    def getSlidingPuzzle(self) -> SlidingPuzzle:
        return self.__slidingPuzzle

    def getParent(self) -> "PuzzleNode":
        return self.__parent

    def isSolution(self) -> bool:
        """
        Whether the SlidingPuzzle associated to this node is a solved puzzle. A shortcut to the method of the SlidingPuzzle calculating it.
        
        :return: If the puzzle is solved.
        :rtype: bool
        """
        return self.__slidingPuzzle.isSolution()

    def __repr__(self):
        """
        String meant to be displayed, with current node's level and sliding puzzle associated to it.
        
        :return: String to be displayed.
        :rtype: String
        """
        pStr = ""
        pStr += "Level " + str(self.getLevel()) + "\n" + str(self.__slidingPuzzle)
        return pStr

    def __lt__(self, other: "PuzzleNode") -> bool:
        """
        Compares two nodes based on their total distance to the solution.
        Total distance is the real cost of the current node from its root position + the estimated heuristic to solve from this position.
        
        :param other: Other node to compare to.
        :type other: PuzzleNode
        :return: True if self is closer to the solution than the other node, else it is false.
        :rtype: bool
        """
        return self.getTotalDistance() < other.getTotalDistance()


class SolvingTree:
    """
    The tree of nodes with methods to interact with it, and actually solve it.
    """

    def __init__(self, slidingPuzzle: SlidingPuzzle):
        """
        Initializes the tree with a heap queue (priority queue) to sort the nodes.
        It helps doing the work a lot faster, as you can only get the "smaller" node, which is the one closer to the solution.
        When putting a new node in, it also updates itself to reflect the smalled node at the top of the heap.
        Really interesting structure, makes the algorithm work.
        
        :param slidingPuzzle: Sliding puzzle to start the solver from.
        :type slidingPuzzle: SlidingPuzzle
        """

        self.__heap: typing.List[PuzzleNode] = []
        firstNode = PuzzleNode(slidingPuzzle)
        heapq.heappush(self.__heap, firstNode)

        self.__visited = [slidingPuzzle.getPuzzle] # Stores an array of already visited puzzles (represented as 2D lists) to avoid falling in a loop.
        self.__solved = None

    def solve(self) -> None:
        """
        Processes the tree to find the solution. Ends when a solution is found, so if no solution exists for the given puzzle, it can loop for a long time (CTRL + C may help you).
        
        :return: None when finished.
        :rtype: None
        """

        while len(self.__heap) != 0:
            bestNode = heapq.heappop(self.__heap)
            newNodes = bestNode.developNode()
            for node in newNodes:
                if(node.isSolution()):
                    self.__solved = node
                    return None
                elif(not(node.getSlidingPuzzle().getPuzzle() in self.__visited)):
                    heapq.heappush(self.__heap, node)
                    self.__visited.append(node.getSlidingPuzzle().getPuzzle())

    def getSolutionPath(self) -> typing.List[SlidingPuzzle]:
        """
        List of SlidingPuzzle representing the path to the solution, from the root to the solution.
        
        :return: List of SlidingPuzzle being the path to the solution.
        :rtype: List[SlidingPuzzle]
        """

        solution: typing.List[SlidingPuzzle] = []
        node = self.__solved
        while(node is not None):
            solution.append(node.getSlidingPuzzle())
            node = node.getParent()
        solution.reverse()
        return solution


if __name__ == "__main__":

    parser = argparse.ArgumentParser()
    parser.add_argument("puzzle", help = "Specify each number of the puzzle to solve, from top left to bottom right (0 for empty space). Ex.:\n" +
        "'python3 " + __file__ + " 1 2 7 6 5 8 4 3 0' to solve a 3x3 puzzle with lines being (1 2 7), (6 5 8) and (4 3 0) from top to bottom.", nargs = "+", type = int)
    args = parser.parse_args()
    
    if(math.floor(math.sqrt(len(args.puzzle)))**2 != len(args.puzzle)):
        print("Given puzzle isn't a square. Call 'python3 " + __file__ + " -h' to see the correct format.")

    side:int = math.floor(math.sqrt(len(args.puzzle)))
    
    puzzle = []
    for i in range(side):
        newLine = []
        for j in range(side):
            newLine.append(args.puzzle[i*side + j])
        puzzle.append(newLine)
    
    sp = SlidingPuzzle(puzzle=puzzle) # Creating the SlidingPuzzle to solve.
    tree = SolvingTree(sp) # Creating the tree with our SlidingPuzzle as root.

    tree.solve() # Solves the problem.
    solution = tree.getSolutionPath() # Retrives the solution.

    for puzzleStep in solution: # Prints the solution.
        print(puzzleStep)

    print("Number of moves required to solve: " + str(len(solution) - 1)) # Prints the number of moves required to reach the solution.
