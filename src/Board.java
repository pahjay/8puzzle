import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by ryan on 2/17/17.
 */
public class Board {
    Board[] maxPQ;
    int[][] board = new int[0][];
    int N; // size of the array

    public Board(int[][] blocks)
    { // construct a board from an n-by-n array of blocks
        board = copy(blocks);
        N = board.length;
    }

    public int dimension()
    { // board dimension n
        return N;
    }

    /**
     * iterate over board, ignoring 0 value
     * if value is not consistent with solved puzzle, increase hamming score
     **/
    public int hamming() // number of blocks that are out of place
    {
        int val = 0, h = 0;
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                val++;
                if (board[i][j] == 0) continue;
                else if (board[i][j] != val)
                {
                    h++;
                }
            }
        }
        return h;
    }

    /**
     * iterate through board values
     * check value at i,j and calculate M-score
     * |yv-yx| + |xv -xx|
     * add individual M-score to total
     **/
    public int manhatten()
    { // sum of Manhatten distances between blocks and goal
        int mScore = 0;

        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                if(board[i][j] == 0) continue;
                int temp = (N*N) - board[i][j];
                int x, y = N-1;

                while(temp >= N)
                {
                    temp = temp - N;
                    y--;
                }
                x = N - temp - 1;
                mScore = mScore + (Math.abs((x - j)) + Math.abs(y - i));
            }
        }
        return mScore;
    }

    public boolean isGoal()
    { // is this board the goal board?
        return hamming() == 0;
    }


    public Board twin()
    { // a board that is obtained by exchanging any pair of blocks
        int[][] boardCopy = copy(board);
        int i = 0, j = 0;

        while(boardCopy[i][j] == 0 || boardCopy[i][j+1] == 0)
        {
            j++;
            if(j > boardCopy.length - 1)
            {
                j = 0;
                i++;
            }

        }

        exchange(boardCopy, i , j, i , j + 1);
        Board twinBoard = new Board(boardCopy);

        return twinBoard;
    }


    public boolean equals(Object y) // does this board equal y?
    {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;

        for(int i = 0; i < this.board.length; i++)
        {
            for (int j = 0; j < this.board.length; j++)
            {
                if(this.board[i][j] != that.board[i][j]) return false;
            }
        }
        return true;

    }


    public Iterable<Board> neighbors() // all neighboring boards
    {
       Stack<Board> boardStack = new Stack<>();
       boolean isFound = false;
       int i = 0, j = 0;

       for (i = 0; i < board.length - 1; i++)
       {
           for (j = 0; j < board.length - 1; j++)
           {
               if (board[i][j] == 0) isFound = true;
           }
       }

        if (isGoal())
        {

            // boardStack.push(new Board(temp));
            return boardStack;
        }

       // right
       if (j + 1 <= board.length)
       {
           int[][] temp = copy(board);
           exchange(temp, i, j, i, j + 1);
           boardStack.push(new Board(temp));
       }

        // left
        if (j - 1 >= 0)
        {
            int[][] temp = copy(board);
            exchange(temp, i , j , i , j - 1);
            boardStack.push(new Board(temp));
        }

        // down
        if (i + 1 <= board.length)
        {
            int[][] temp = copy(board);
            exchange(temp, i , j , i + 1, j);
            boardStack.push(new Board(temp));
        }

        // up
        if (i - 1 >= 0)
        {
            int[][] temp = copy(board);
            exchange(temp, i , j , i - 1, j);
            boardStack.push(new Board(temp));
        }

        return boardStack;

    }


    public String toString() // string representation of this board (in the output format specified below
    {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                s.append(String.format("%2d", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    private static boolean less(int a, int b)
    {
        return a - b < 0;
    }

    private static int[][] copy(int[][] toBeCopied)
    {
        int[][] copy = new int[toBeCopied.length][];

        for (int i = 0; i < toBeCopied.length; i++)
        {
            copy[i] = toBeCopied[i].clone();
        }
        return copy;
    }

    private void exchange(int[][] copy, int i , int j, int ii, int jj)
    {
        int temp = copy[i-1][j-1];
        copy[i-1][j-1] = copy[ii-1][jj-1];
        copy[ii-1][jj-1] = temp;
    }


    public static void main(String[] args)
    {
        int[][] unsolved = {{8,1,3},
                            {4,0,2},
                            {7,6,5}};

         int[][] solved = {{1,2,3},
                           {4,5,6},
                           {7,8,0}};

         int[][] partialSolved = {{1,2,3},
                                  {4,5,0},
                                  {6,7,8}};
        int[][] partialSolved1 = {{1,2,3},
                {4,5,0},
                {6,7,8}};

         int[][] test = copy(unsolved);

        Board a = new Board(unsolved);
        Board b = new Board(partialSolved1);

        Solver solverObj = new Solver(a);

    }
}
