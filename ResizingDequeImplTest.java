import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ResizingDequeImplTest {

    @Test
    public void testGetArray() {
        ResizingDequeImpl<Integer> q = new ResizingDequeImpl<Integer>();
        Integer[] expected = new Integer[2];
        assertArrayEquals(expected, q.getArray());
    }

    @Test
    public void testAdd() {
        ResizingDequeImpl<Integer> q = new ResizingDequeImpl<Integer>();
        Integer[] e = new Integer[8];
        e[0] = 0;
        e[1] = 1;
        e[2] = 2;
        e[3] = 3;
        e[4] = 4;
        e[5] = 5;
        e[6] = 6;
        e[7] = 7;

        q.addLast(1);
        q.addLast(2);
        q.addFirst(0);
        q.addLast(3);
        q.addLast(4);
        q.addLast(5);
        q.addLast(6);
        q.addLast(7);

        assertArrayEquals(e, q.getArray());
    }

    @Test(expected = NoSuchElementException.class)
    public void testInvalidPeekFirst() {
        ResizingDequeImpl<Integer> q = new ResizingDequeImpl<Integer>();
        q.peekFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void testInvalidPeekLast() {
        ResizingDequeImpl<Integer> q = new ResizingDequeImpl<Integer>();
        q.peekLast();
    }

    @Test(expected = NoSuchElementException.class)
    public void testInvalidPollFirst() {
        ResizingDequeImpl<Integer> q = new ResizingDequeImpl<Integer>();
        q.pollFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void testInvalidPollLast() {
        ResizingDequeImpl<Integer> q = new ResizingDequeImpl<Integer>();
        q.pollLast();
    }

    @Test
    public void testPoll() {
        ResizingDequeImpl<Integer> q = new ResizingDequeImpl<Integer>();
        Integer[] e = new Integer[2];
        e[0] = 2;
        e[1] = 3;

        q.addLast(3);
        q.addLast(4);
        q.addFirst(2);
        q.addFirst(1);
        q.addLast(5);
        q.pollLast();
        q.pollLast();
        q.pollFirst();

        assertArrayEquals(e, q.getArray());
    }

    @Test
    public void testPollAgain() {
        ResizingDequeImpl<Integer> q = new ResizingDequeImpl<Integer>();
        Integer[] e = new Integer[2];
        e[0] = 1;
        e[1] = 2;

        q.addLast(3);
        q.addLast(4);
        q.addFirst(2);
        q.addFirst(1);
        q.pollLast();
        q.pollLast();

        assertArrayEquals(e, q.getArray());
    }

    @Test
    public void testPollAgainAgain() {
        ResizingDequeImpl<Integer> q = new ResizingDequeImpl<Integer>();
        Integer[] e = new Integer[2];
        e[0] = 3;
        e[1] = 4;

        q.addLast(3);
        q.addLast(4);
        q.addFirst(2);
        q.addFirst(1);
        q.pollFirst();
        q.pollFirst();

        assertArrayEquals(e, q.getArray());
    }

    @Test
    public void testIterator() {
        ResizingDequeImpl<Integer> q = new ResizingDequeImpl<Integer>();
        Integer[] e = new Integer[2];
        e[0] = 2;
        e[1] = 3;

        q.addLast(4);
        q.addFirst(3);
        q.addFirst(2);
        q.addFirst(1);
        q.pollLast();
        q.pollFirst();

        assertEquals(2, q.size());
        assertEquals((Integer) 2, q.peekFirst());
        assertEquals((Integer) 3, q.peekLast());

        Iterator<Integer> it = q.iterator();
        assertTrue(it.hasNext());
        assertEquals((Integer) 2, it.next());
        assertEquals((Integer) 3, it.next());
        assertFalse(it.hasNext());
    }

}
