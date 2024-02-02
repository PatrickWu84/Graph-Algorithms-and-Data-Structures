import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DijkstraTest {

    @Test
    public void testUndirectedCorrectSolution() {
        Graph g = new Graph(4);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 0 , 1);
        g.addEdge(1, 2, 2);
        g.addEdge(2, 1, 2);
        g.addEdge(0, 3, 3);
        g.addEdge(3, 0, 3);
        g.addEdge(3, 2, 2);
        g.addEdge(2, 3, 2);

        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(1);
        expected.add(2);

        assertEquals(expected, Dijkstra.getShortestPath(g, 0, 2));
    }
    @Test
    public void testUndirectedSolution() {
        Graph g = new Graph(5);
        g.addEdge(4, 3, 1);
        g.addEdge(3, 4 , 1);
        g.addEdge(4, 1, 1);
        g.addEdge(1, 4, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 2, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 1, 1);
        g.addEdge(3, 0 , 1);
        g.addEdge(0, 3, 1);

        List<Integer> expected = new ArrayList<>();
        expected.add(4);
        expected.add(3);
        expected.add(0);

        assertEquals(expected, Dijkstra.getShortestPath(g, 4, 0));
    }

    @Test
    public void testNoSolution() {
        Graph g = new Graph(2);
        List<Coordinate> path = new ArrayList<>();
        assertEquals(path, Dijkstra.getShortestPath(g, 0, 1));
    }

    @Test
    public void testUndirectedNoSolution() {
        Graph g = new Graph(4);


        g.addEdge(1, 2, 2);
        g.addEdge(2, 1, 2);


        g.addEdge(3, 2, 2);
        g.addEdge(2, 3, 2);

        List<Integer> expected = new ArrayList<>();

        assertEquals(expected, Dijkstra.getShortestPath(g, 0, 2));

    }
}
