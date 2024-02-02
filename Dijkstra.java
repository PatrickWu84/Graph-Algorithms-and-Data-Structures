import java.util.*;

/**
 * Provides access to Dijkstra's algorithm for a weighted graph.
 */
final public class Dijkstra {
    private Dijkstra() {
    }

    /**
     * Computes the shortest path between two nodes in a weighted graph.
     * Input graph is guaranteed to be valid and have no negative-weighted edges.
     *
     * @param g   the weighted graph to compute the shortest path on
     * @param src the source node
     * @param tgt the target node
     * @return an empty list if there is no path from {@param src} to {@param tgt}, otherwise an
     * ordered list of vertices in the shortest path from {@param src} to {@param tgt},
     * with the first element being {@param src} and the last element being {@param tgt}.
     */
    public static List<Integer> getShortestPath(Graph g, int src, int tgt) {
        int n = g.getSize();
        int[] distance = new int[n];
        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }

        BinaryMinHeapImpl<Integer, Integer> q = new BinaryMinHeapImpl<>();

        distance[src] = 0;
        for (int i = 0; i < n; i++) {
            q.add(distance[i], i);
        }

        while (!q.isEmpty()) {
            BinaryMinHeap.Entry<Integer, Integer> e = q.extractMin();
            int u = e.value;
            int d = e.key;
            Set<Integer> adj = g.outNeighbors(u);
            Iterator<Integer> adjIt = adj.iterator();
            while (adjIt.hasNext()) {
                int v = adjIt.next();
                if (q.containsValue(v) && distance[v] > d + g.getWeight(u, v)) {
                    distance[v] = d + g.getWeight(u, v);
                    q.decreaseKey(v,(d + g.getWeight(u, v)));
                    parent[v] = u;
                }
            }
        }
        List<Integer> reversepath = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        if (src == tgt) {
            path.add(src);
            return path;
        }

        if (parent[tgt] == -1) {
            return path;
        }

        reversepath = getPath(reversepath, parent, src, tgt);
        for (int i = reversepath.size() - 1; i >= 0; i--) {
            path.add(reversepath.get(i));
        }
        return path;
    }
    static List<Integer> getPath(List<Integer> path, int[] parent, int s, int c) {
        if (parent[c] == -1) {
            return new ArrayList<>();
        }
        if (parent[c] == s) {
            path.add(c);
            path.add(s);
            return path;
        } else {
            path.add(c);
            return getPath(path, parent, s, parent[c]);
        }
    }
}
