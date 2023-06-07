package chat.gpt.domain.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;

import chat.gpt.domain.table.Table;

public class Search {
// AStart algorithm from AIMA(Artificial Inteligence A Modern Approach) 3ed
// Chapter 3. Solving Problems by Searching.
// Using manhattan heuristic

// Follow algorithm(UCS + heuristic = A*):
// function UNIFORM-COST-SEARCH( problem) returns a solution, or failure

// node ←a node with STATE = problem.INITIAL-STATE, P ATH -COST = 0
// frontier ←a priority queue ordered by PATH -COST, with node as the only element
// explored ←an empty set

// loop do
//     if EMPTY?( frontier ) then return failure
//     node ←POP( frontier ) /* chooses the lowest-cost node in frontier */
//     if problem.GOAL-TEST(node.STATE) then return SOLUTION(node)
//     add node.STATE to explored
//     for each action in problem.ACTIONS(node.STATE) do
//         child ←CHILD-NODE( problem, node, action)
//         if child .STATE is not in explored or frontier then
//             frontier ←INSERT(child , frontier )
//         else if child .STATE is in frontier with higher PATH -COST then
//             replace that frontier node with child

// function CHILD-NODE( problem, parent , action) returns a node
//     return a node with
//     STATE = problem.RESULT(parent .STATE, action),
//     PARENT = parent , A CTION = action,
//     PATH -COST = parent .PATH -COST + problem.STEP-COST(parent .STATE, action)

    private class Node implements Comparable<Node> {
        public Node parent = null;
        public Integer pathCost = 0;
        public Integer heuristicValue = 0;
        public Table table = null; // table state
        public Integer[] move = new Integer[2];

        public Node(Node parent, Table table) {
            this.parent = parent;
            this.table = table;
        }

        public Node(Table table) {
            this.table = table;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Node)) {
                return false;
            }

            var otherNode = (Node) other;
            var otherCells = otherNode.table.getCells();
            var thisCells = table.getCells();

            Integer counter = thisCells.size();
            for (Integer i = 0; i < thisCells.size(); ++i) {
                var x = thisCells.get(i).getValue();
                var y = otherCells.get(i).getValue();

                if (x.equals(y)) {
                    counter -= 1;
                }
            }

            return counter == 0;
        }

        @Override
        public int compareTo(Node other) {
            return (this.pathCost + this.heuristicValue) - (other.pathCost + other.heuristicValue);
        }
    }

    static final private Integer[][] allowedMoves = {
            { 0, -1 },
            { 0, 1 },
            { 1, 0 },
            { -1, 0 }
    };

    final static private Integer[][] goalByIndex = {
            { 2, 2 }, // 0
            { 0, 0 }, // 1
            { 0, 1 }, // 2
            { 0, 2 }, // 3
            { 1, 0 }, // 4
            { 1, 1 }, // 5
            { 1, 2 }, // 6
            { 2, 0 }, // 7
            { 2, 1 } // 8
    };

    private PriorityQueue<Node> frontier;
    private HashSet<Node> explored;

    private Integer[] findEmptyCoordenates(Table table) {
        Integer index = 0;
        for (var cell : table.getCells()) {
            if (cell.isEmpty()) {
                break;
            }
            ++index;
        }
        return getCoordenatesByIndex(index);
    }

    private ArrayList<Integer[]> getValidsMoves(Integer[] emptyCellCoord) {

        ArrayList<Integer[]> moves = new ArrayList<>();

        for (var mov : allowedMoves) {
            Integer dx = mov[0] + emptyCellCoord[0];
            Integer dy = mov[1] + emptyCellCoord[1];

            if ((0 <= dx && dx <= 2) && (0 <= dy && dy <= 2)) {
                moves.add(new Integer[] { dx, dy });
            }
        }
        return moves;
    }

    private ArrayList<Node> getChildrens(Node node) {
        var emptyCoord = findEmptyCoordenates(node.table);

        ArrayList<Node> childs = new ArrayList<>();
        var aux = getValidsMoves(emptyCoord);
        Node childNode = null;
        for (var mov : allowedMoves) {
            Integer dx = mov[0] + emptyCoord[0];
            Integer dy = mov[1] + emptyCoord[1];

            if (!((0 <= dx && dx <= 2) && (0 <= dy && dy <= 2))) {
                // moves.add(new Integer[] { dx, dy });
                continue;
            }

            Table childTable = new Table(node.table);
            childTable.swap(emptyCoord[0], emptyCoord[1], dx, dy);

            childNode = new Node(node, childTable);
            childNode.move = mov;

            childs.add(childNode);
        }

        return childs;
    }

    private Integer[] getCoordenatesByIndex(Integer index) {
        // computes coordenates
        Integer dx = (index) / 3;
        Integer dy = (index) % 3;

        return new Integer[] { dx, dy };
    }

    private boolean isGoalState(Node node) {
        return node.table.jogoConcluido();
    }

    private Integer heuristic(Node node) {

        var cells = node.table.getCells();
        Integer sum = 0;


        for (Integer i = 0; i < cells.size(); ++i) {
            var cell = cells.get(i);
            if (cell.getValue() == 0) {
                continue;
            }
            Integer[] actualPosition = getCoordenatesByIndex(i);
            Integer[] goalPosition = goalByIndex[cell.getValue()];

            Integer distance = manhattanDistance(goalPosition, actualPosition);
            sum += distance;
        }

        return sum;
    }

    private Integer manhattanDistance(Integer[] px, Integer[] py) {
        return Math.abs(px[0] - py[0]) + Math.abs(px[1] - py[1]);
    }


    private ArrayList<Integer[]> backTrackNode(Node node){
        ArrayList<Integer[]> steps = new ArrayList<>();
        while (node.parent != null){
            steps.add(node.move);
            node = node.parent;
        }

        Collections.reverse(steps);

        return steps;
    }

    public ArrayList<Integer[]> findPath(Table table) {

        frontier = new PriorityQueue<>();
        explored = new HashSet<>();

        Node node = new Node(table);

        node.pathCost = 0;
        node.heuristicValue = heuristic(node);

        frontier.add(node);

        while (!frontier.isEmpty()) {

            node = frontier.poll();

            explored.add(node);

            if (isGoalState(node)) {
                break; // goal state;
            }

            for (var childNode : getChildrens(node)) {

                childNode.pathCost = node.pathCost + 1;
                childNode.heuristicValue = heuristic(childNode);

                if (!frontier.contains(childNode) && !explored.contains(childNode)) {
                    frontier.add(childNode);
                } else {
                    var opFrontierNode = frontier.stream().filter(x -> x.equals(childNode)).findFirst();
                    if (!opFrontierNode.isEmpty()) {
                        var frontierNode = opFrontierNode.get();

                        if (childNode.pathCost < frontierNode.pathCost) {
                            frontier.remove(frontierNode);
                            frontier.add(childNode);
                        } else {

                            var opExploredNode = explored.stream().filter(x -> x.equals(childNode)).findFirst();

                            if (!opExploredNode.isEmpty()) {
                                var exploredNode = opExploredNode.get();
                                if (childNode.pathCost < exploredNode.pathCost) {
                                    explored.remove(exploredNode);
                                    explored.add(childNode);
                                }
                            }
                        }

                    }
                }
            }
        }

        return backTrackNode(node);
    }
}