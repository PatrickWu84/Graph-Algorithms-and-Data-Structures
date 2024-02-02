import org.junit.Test;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class GraphTest {

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidN() {
        Graph g = new Graph(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidHasEdge() {
        Graph g = new Graph(2);
        g.hasEdge(3, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidWeightNoVertex() {
        Graph g = new Graph(2);
        g.getWeight(3, -1);
    }

    @Test(expected = NoSuchElementException.class)
    public void testInvalidWeightNoEdge() {
        Graph g = new Graph(2);
        g.getWeight(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAddEdgeVertex() {
        Graph g = new Graph(2);
        g.addEdge(3, -1, 1);
    }

    @Test
    public void testFalseAddEdgeExists() {
        Graph g = new Graph(2);
        g.addEdge(0, 1, 1);
        assertFalse(g.addEdge(0,1, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidOutNeighborsNoVertex() {
        Graph g = new Graph(2);
        g.outNeighbors(3);
    }
}
