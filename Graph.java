import java.util.*;

public class Graph {
    HashMap<Integer, Integer>[] vertices;

    public Graph(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        vertices = new HashMap[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new HashMap<>();
        }
    }

    public int getSize() {
        return vertices.length;
    }

    public boolean hasEdge(int u, int v) {
        int n = getSize();
        if (u >= n || u < 0 || v >= n || v < 0) {
            throw new IllegalArgumentException();
        }
        return vertices[u].containsKey(v);
    }

    public int getWeight(int u, int v) {
        int n = getSize();
        if (u >= n || u < 0 || v >= n || v < 0) {
            throw new IllegalArgumentException();
        }
        if (!hasEdge(u, v)) {
            throw new NoSuchElementException();
        }
        return vertices[u].get(v);
    }

    public boolean addEdge(int u, int v, int weight) {
        int n = getSize();
        if (u >= n || u < 0 || v >= n || v < 0 || u == v) {
            throw new IllegalArgumentException();
        }
        if (hasEdge(u, v)) {
            return false;
        }
        vertices[u].put(v, weight);
        return true;
    }

    public Set<Integer> outNeighbors(int v) {
        Set<Integer> neighbors = new HashSet<Integer>();
        int n = getSize();
        if (v >= n || v < 0) {
            throw new IllegalArgumentException();
        }
        return vertices[v].keySet();
    }
}
