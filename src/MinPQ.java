/**
 * Created by ryan on 2/22/17.
 */
public class MinPQ<Key extends Comparable<Key>>
{
    private Key[] pq; // pq[i] = ith element in pq
    private int N;    // number of elements on the pq

    /**
     * Might need to add a constructor for a capacity parameter
     */

    public MinPQ(Key[] a)
    {
        pq = a;
    }
}
