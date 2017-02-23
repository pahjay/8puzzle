/**
 * Created by ryan on 2/22/17.
 */

/*

public class MinPQ<Key extends Comparable<Key>>
{
    private Key[] pq; // pq[i] = ith element in pq
    private int N;    // number of elements on the pq

    */
/**
     * Might need to add a constructor for a capacity parameter
     * or just use a dynamic array
     *//*

    public MinPQ(Key[] a)
    { pq = a.clone(); }

    */
/**
     * Resize if necessary
     * Insert at the end of the array, swim if necessary
     *//*

    private void insert(Key a)
    {
        if (a == null) throw new NullPointerException("insert: insertion object is null");
        N++;
        if ( N >= pq.length * (3/4))
        {
            resize( 2 * N);
        }
        pq[N] = a;
    }

    */
/**
     * delete last item in array, null out
     *//*

    private void delMax()
    {
        if (pq[N] == null) throw new NullPointerException("delMax: last item is null");

        pq[N] = null;
        N--;
    }

    */
/**
     * swap last and first item, null out last item
     * sink new first item to it's respective position in the tree
     *//*

    private void delMin()
    {
        exch(0, N);
        sink(1);
        N--;
    }

    private void swim(int k)
    {
        while (2 * k >= N)
        {
            if(less(k / 2, k))
            {
                exch(k / 2, k);
            } else
            {
                break;
            }
            if(!less(k, k / 2)) break;
            k = k / 2;
        }
    }

    */
/**
     * While the value of pq[k] is greater than pq[2k+1] or pq[2k+2]
     *
     *//*

    private void sink(int k)
    {
        while(2 * k <= N)
        {
            if (less(2 * k + 1, 2 * k + 2))
            {
                exch(k, 2 * k + 1);
            } else
            {
                exch(k, 2 * k + 2);
            }
            if(!less(k, 2 * k + 1)) break;

            k = 2 * k;
        }
    }

    public boolean isEmpty()
    { return N == 0; }

    private void exch(int i, int j)
    {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private boolean less(int a, int b)
    { return pq[a].compareTo(pq[b]) < 0; }

    private void resize(int i)
    {
        Key[] temp = (Key[]) new Comparable[2 * N];

        for (int j = 0; j < pq.length - 1; j++){
            temp[j] = pq[j];
        }
        
        pq = temp;
    }
}
*/
