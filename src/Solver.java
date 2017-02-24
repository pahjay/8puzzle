import edu.princeton.cs.algs4.MinPQ;

import java.util.Stack;

/**
 * Created by ryan on 2/17/17.
 */

public class Solver {
    MinPQ<SearchNode> pq = new MinPQ<>();
    int moves;


    private class SearchNode implements Comparable<SearchNode>
    {
        private Board board;
        private SearchNode previousNode;

        public SearchNode(Board board, SearchNode previous)
        {
            this.board = board;
            this.previousNode = previous;
        }

        private int priority()
        {
            return this.board.manhatten() + this.board.hamming();
        }

        public SearchNode previous(){
            return previousNode;
        }

        @Override
        public int compareTo(SearchNode that)
        {
            if (this.priority() > that.priority()) return 1;
            else if (this.priority() == that.priority()) return 0;
            else return -1;
        }
    }

    public Solver(Board initial) { // find a solution to the initial board (using the A* algorithm)
        SearchNode prev = new SearchNode(initial, null);
        moves = initial.manhatten();
        pq.insert(prev);
        SearchNode peak = pq.delMin();
        Iterable<Board> neighbors = peak.board.neighbors();
        int i = 0;

        for (Board b : neighbors)
        {
            if (b.equals(prev.board)) continue; // skip if same as previous board
            SearchNode s = new SearchNode(b, prev);
            pq.insert(s);
            prev = s;
        }

        // while SearchNode s is not solved
        // set initial to lowest priority
        // redefine neighbors
        // check for reversal
        // repeat


        while (!peak.board.isGoal())
        {
            peak = pq.delMin();
            neighbors = peak.board.neighbors();
            for(Board b : neighbors)
            {
                if (b.equals(prev.board)) continue; // skip if same as previous board
                SearchNode s = new SearchNode(b, prev);
                pq.insert(s);
                prev = s;
            }
            i++;
            System.out.println("peak " + peak.board.toString());
        }

    }

    public boolean isSolvable() { // is the initial board solvable?
        return false;
    }

    public int moves() { // min number of moves to solve the initial board; -1 is unsolvable
        return moves;
    }

    public Iterable<Board> solution(){ // sequence of boards in a shortest solution; null if unsolvable
        Stack<Board> solutionStack = new Stack<>();
        for (SearchNode b : pq){
            solutionStack.push(b.board);
        }

        return solutionStack;
    }

    public static void main(String[] args){
        int[][] u = {{1,2,3},
                     {4,5,6},
                     {7,8,0}};

        int[][] p = {{8,1,3},
                     {4,0,2},
                     {7,6,5}};

        Board unsolvable = new Board(p);
        Solver slv = new Solver(unsolvable);
        Iterable<Board> itr = slv.solution();

        for (Board a : itr){
            System.out.println(a.toString());
            System.out.println((a.manhatten() + a.hamming()));
        }
    }
}