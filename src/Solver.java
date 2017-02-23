import edu.princeton.cs.algs4.MinPQ;

/**
 * Created by ryan on 2/17/17.
 */

public class Solver {
    int priority;
    MinPQ<SearchNode> pq = new MinPQ<>();

    private class SearchNode implements Comparable<SearchNode>
    {
        private Board board;
        private SearchNode previousNode;

        public SearchNode(Board board, )

        private int priority()
        {
            return this.board.manhatten() + this.board.hamming();
        }

        @Override
        public int compareTo(SearchNode that){
            if(this.priority() > that.priority())        return 1;
            else if (this.priority() == that.priority()) return 0;
            else                                         return -1;
        }
    }

    public Solver(Board initial) { // find a solution to the initial board (using the A* algorithm)
        Board i = initial;
        pq.insert(i);

        while(!i.isGoal()){
            pq.insert(i.twin());
            System.out.println(i.twin());
        }
    }

 /*   public boolean isSolvable() { // is the initial board solvable?

    }

    public int moves() { // min number of moves to solve the initial board; -1 is unsolvable

    }

    public Iterable<Board> solution(){

    }*/

    public static void main(String[] args){}
}

