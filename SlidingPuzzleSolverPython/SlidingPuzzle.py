import copy
import heapq
import typing


class SlidingPuzzle:

    def __init__(self, *args, **kwargs):

        if ("sideSize" in kwargs):
            self.__sideSize: int = kwargs["sideSize"]
            self.__puzzle: typing.List[typing.List[int]] = [[x * kwargs["sideSize"] + y
                              + 1 for y in range(kwargs["sideSize"])] for x in range(kwargs["sideSize"])]
            self.__puzzle[kwargs["sideSize"] - 1][kwargs["sideSize"] - 1] = 0

        elif ("slidingPuzzle" in kwargs):
            size: int = kwargs["slidingPuzzle"].getSideSize()
            self.__sideSize: int = size
            self.__puzzle: typing.List[typing.List[int]] = [
                [0 for y in range(size)] for x in range(size)]
            for x in range(size):
                for y in range(size):
                    self.__puzzle[x][y] = kwargs["slidingPuzzle"].getPuzzle()[x][y]

        elif("puzzle" in kwargs):
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

        self.updateManhattan()

    def getPuzzle(self) -> typing.List[typing.List[int]]:
        return self.__puzzle

    def getSideSize(self) -> int:
        return self.__sideSize

    def getEmptyCell(self) -> typing.Tuple[int, int]:
        for x in range(self.getSideSize()):
            for y in range(self.getSideSize()):
                if(self.__puzzle[x][y] == 0):
                    return (x, y)

    def moveUp(self, edit: bool = True) -> typing.List[typing.List[int]]:
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
        for x in range(self.getSideSize()):
            for y in range(self.getSideSize()):
                if(self.__puzzle[x][y] != x * self.getSideSize() + y + 1):
                    if(not(x == self.getSideSize() - 1 and y == self.getSideSize() - 1 and self.__puzzle[x][y] == 0)):
                        return False
        return True

    def getManhattan(self) -> int:
        return self.__manhattan

    def updateManhattan(self):
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
        pStr: str = ""
        for x in self.__puzzle:
            for y in x:
                pStr += str(y) + " "
            pStr += '\n'
        return pStr


class PuzzleNode:

    def __init__(self, slidingPuzzle: SlidingPuzzle, parent: PuzzleNode = None):
        self.__slidingPuzzle: SlidingPuzzle = slidingPuzzle
        self.__parent: PuzzleNode = parent
        if(self.__parent is not None):
            self.__level = self.__parent.getLevel() + 1
        else:
            self.__level = 0
        self.__totalDistance = self.__level + self.__slidingPuzzle.getManhattan()

    def developNode(self):

        newNodes = []

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

    def getLevel(self):
        return self.__level

    def getTotalDistance(self):
        return self.__totalDistance

    def getSlidingPuzzle(self):
        return self.__slidingPuzzle

    def getParent(self):
        return self.__parent

    def isSolution(self):
        return self.__slidingPuzzle.isSolution()

    def __repr__(self):
        pStr = ""
        pStr += "Level " + str(self.getTotalDistance()) + "\n" + str(self.__slidingPuzzle)
        return pStr

    def __lt__(self, other):
        return self.getTotalDistance() < other.getTotalDistance()


class SolvingTree:

    def __init__(self, slidingPuzzle):

        self.__heap = []
        firstNode = PuzzleNode(slidingPuzzle)
        heapq.heappush(self.__heap, firstNode)

        self.__visited = [slidingPuzzle.getPuzzle]
        self.__solved = None

    def sort(self):
        self.__nodes = sorted(
            self.__nodes, key=lambda node: node.getTotalDistance())

    def solve(self):

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

    def getNodes(self):
        return self.__nodes

    def getSolutionPath(self):

        solution = []
        node = self.__solved
        while(node is not None):
            solution.append(node.getSlidingPuzzle())
            node = node.getParent()
        solution.reverse()
        return solution


sp = SlidingPuzzle(puzzle=[[1, 2, 7], [6, 5, 8], [4, 3, 0]])
tree = SolvingTree(sp)

tree.solve()
solution = tree.getSolutionPath()

for puzzle in solution:
    print(puzzle)

print("Number of moves: " + str(len(solution) - 1))
